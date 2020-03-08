package com.hz.world.core.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hz.world.account.domain.dto.UserBaseInfoDTO;
import com.hz.world.account.service.UserBaseInfoService;
import com.hz.world.common.dto.ResultCodeEnum;
import com.hz.world.common.dto.ResultDTO;
import com.hz.world.common.enums.CoinChangeType;
import com.hz.world.common.enums.ElementAdd;
import com.hz.world.common.ids.IDGenerator;
import com.hz.world.common.util.DateUtil;
import com.hz.world.core.dao.impl.UserElementManagerDaoImpl;
import com.hz.world.core.dao.impl.UserRequireCoinLogDaoImpl;
import com.hz.world.core.dao.impl.UserSendCoinLogDaoImpl;
import com.hz.world.core.dao.model.UserElementManager;
import com.hz.world.core.dao.model.UserRequireCoinLog;
import com.hz.world.core.dao.model.UserSendCoinLog;
import com.hz.world.core.domain.dto.BossDTO;
import com.hz.world.core.domain.dto.ManagerDTO;
import com.hz.world.core.domain.dto.UserElementDTO;
import com.hz.world.core.service.FactoryService;
import com.hz.world.core.service.UserCoinService;
import com.hz.world.core.service.UserElementService;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: Description: author linyanchun date Feb 22, 2020
 */
@Slf4j
@Service
public class FactoryServiceImpl implements FactoryService {


	@Autowired
	private UserElementManagerDaoImpl userElementManagerDao;
	@Autowired
	private UserElementService userElementService;
	@Autowired
	private UserBaseInfoService userBaseInfoService;
	@Autowired
	private UserRequireCoinLogDaoImpl userRequireCoinLogDao;
	@Autowired
	private UserSendCoinLogDaoImpl userSendCoinLogDao;
	@Autowired
	private UserCoinService userCoinService;

	@Override
	public ResultDTO<String> inviteUser(Long userId, Integer element, Long toUserId){
		ResultDTO<String> resultDTO = new ResultDTO<String>();
		if (userElementManagerDao.isLimited(toUserId)) {
			resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "同一时间只能给一个好友打工~");
			return resultDTO;
		}
		UserElementManager manager = userElementManagerDao.findUserElementManager(userId, element);
		if (manager != null) {
			resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "厂长已经有人了");
			return resultDTO;
		}
		UserBaseInfoDTO user = userBaseInfoService.getByUserId(toUserId);
		double incomeRate = 0;
		if (user.getActive()== 1) {
			incomeRate = 4;
		}
		manager = new UserElementManager();
		manager.setUserId(userId);
		manager.setElement(element);
		manager.setManager(toUserId);
		manager.setIncomerate(incomeRate);
		userElementManagerDao.insert(manager);
		//增加收益
		if (incomeRate > 0) {
			userElementService.addElementAdd(toUserId, element, ElementAdd.FACTORY.getCode(), incomeRate+"");
			
		}
		
		resultDTO.set(ResultCodeEnum.SUCCESS, "ok");
		return resultDTO;
	}

	@Override
	public boolean fireManager(Long userId, Integer element, Long toUserId) {
		UserElementManager manager = userElementManagerDao.findUserElementManager(userId, element);
		if (manager == null) {
			return true;
		}
		boolean result = userElementManagerDao.delete(userId, element);
		if (result) {
			userElementService.addElementAdd(toUserId, element, ElementAdd.FACTORY.getCode(), -manager.getIncomerate()+"");
		}
		return result;
	}

	@Override
	public List<ManagerDTO> getElementManagerList(Long userId){
		List<ManagerDTO> result = new ArrayList<ManagerDTO>();
		List<UserElementManager> managers = userElementManagerDao.getElementManagerList(userId);
		if (managers != null && managers.size() > 0) {
			for (UserElementManager userElementManager : managers) {
				ManagerDTO manager = new ManagerDTO();
				UserBaseInfoDTO user = userBaseInfoService.getByUserId(userElementManager.getManager());
				manager.setUserId(userElementManager.getManager());
				manager.setHeadImg(user.getHeadImg());
				manager.setActive(user.getActive());
				manager.setNickname(user.getNickname());
				manager.setElement(userElementManager.getElement());
				manager.setIncomeRate(userElementManager.getIncomerate());
				int weight = 0;
				UserElementDTO element = userElementService.getUserElement(userId, 1);
				if (element != null) {
					weight = element.getLevel();
				}
				manager.setWeight(weight);
				manager.setSend(userSendCoinLogDao.isSenddToday(userId, userElementManager.getManager()));
				result.add(manager);
			}
		}
		return result;
	}

	@Override
	public List<BossDTO> workingList(Long userId){
		List<BossDTO> result = new ArrayList<BossDTO>();
		List<UserElementManager> bossList = userElementManagerDao.getBossList(userId); 
		if (bossList != null && bossList.size() > 0) {
			for (UserElementManager userElementManager : bossList) {
				BossDTO boss = new BossDTO();
				UserBaseInfoDTO user = userBaseInfoService.getByUserId(userElementManager.getUserId());
				boss.setUserId(userElementManager.getManager());
				boss.setHeadImg(user.getHeadImg());
				boss.setNickname(user.getNickname());
				boss.setSend(userRequireCoinLogDao.isRequiredToday(userId, userElementManager.getUserId()));
				result.add(boss);
			}
		}
		return result;
	}

	@Override
	public boolean quit(Long userId, Long bossId) {
		UserElementManager manager = userElementManagerDao.findBossManager(userId, bossId);
		if (manager == null) {
			return true;
		}
		boolean result = userElementManagerDao.delete(userId, manager.getElement());
		if (result) {
			userElementService.addElementAdd(bossId, manager.getElement(), ElementAdd.FACTORY.getCode(), -manager.getIncomerate()+"");
		}
		return result;
	}

	@Override
	public ResultDTO<String> requireCoin(Long userId, Long bossId){
		ResultDTO<String> resultDTO = new ResultDTO<String>();
		if (userRequireCoinLogDao.isRequiredToday(userId, bossId)) {
			resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "您今天已催发过奖金");
			return resultDTO;
		}
		UserElementManager manager = userElementManagerDao.findBossManager(userId, bossId);
		if (manager ==  null) {
			resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "不是老板");
			return resultDTO;
		}
		UserRequireCoinLog record = new UserRequireCoinLog();
		record.setId(IDGenerator.getUniqueId());
		record.setUserId(userId);
		record.setToUserId(bossId);
		record.setDate(DateUtil.getTodayDate());
		userRequireCoinLogDao.insert(record);
		resultDTO.set(ResultCodeEnum.SUCCESS, "ok");
		return resultDTO;
	}

	@Override
	public ResultDTO<String> sendCoin(Long userId, Long managerId){
		ResultDTO<String> resultDTO = new ResultDTO<String>();
		if (userSendCoinLogDao.isSenddToday(userId, managerId)) {
			resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "您今天已经发过奖金");
			return resultDTO;
		}
		UserElementManager manager = userElementManagerDao.findBossManager(managerId, userId);
		if (manager ==  null) {
			resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "无法发送");
			return resultDTO;
		}
		//赠送一小时收益
		UserElementDTO elementDTO = userElementService.getUserElement(managerId, manager.getElement());
		BigDecimal coin = new BigDecimal(0);
		if (elementDTO != null) {
			BigDecimal b1 = new BigDecimal(elementDTO.getOutput()) ;
			coin = b1.multiply(BigDecimal.valueOf(3600));
			userCoinService.changeUserCoin(userId, coin.toString(), CoinChangeType.FACTORY.getCode(), 1);
		}
		UserSendCoinLog record = new UserSendCoinLog();
		record.setId(IDGenerator.getUniqueId());
		record.setUserId(userId);
		record.setToUserId(managerId);
		record.setDate(DateUtil.getTodayDate());
		record.setCoin(coin.toString());
		userSendCoinLogDao.insert(record);
		resultDTO.set(ResultCodeEnum.SUCCESS, "ok");
		return resultDTO;
	}
	public UserSendCoinLog getUnReadSendLog(Long userId) {
		UserSendCoinLog log =  userSendCoinLogDao.getUnReadToday(userId);
		if (log != null) {
			log.setStatus(1);
			userSendCoinLogDao.update(log);
		}
		return log;
	}
	public UserRequireCoinLog getUnReadRequireLog(Long userId) {
		UserRequireCoinLog log =  userRequireCoinLogDao.getUnReadToday(userId);
		if (log != null) {
			log.setStatus(1);
			userRequireCoinLogDao.update(log);
		}
		return log;
	}

}
