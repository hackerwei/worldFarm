package com.hz.world.core.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hz.world.account.domain.dto.UserBaseInfoDTO;
import com.hz.world.account.service.UserBaseInfoService;
import com.hz.world.common.enums.CashChangeType;
import com.hz.world.common.ids.IDGenerator;
import com.hz.world.common.util.DateUtil;
import com.hz.world.core.common.util.ConfigCacheUtil;
import com.hz.world.core.dao.impl.DateShareDaoImpl;
import com.hz.world.core.dao.impl.UserCatchDaoImpl;
import com.hz.world.core.dao.impl.UserLimitShareDaoImpl;
import com.hz.world.core.dao.model.CatchConfig;
import com.hz.world.core.dao.model.DateShare;
import com.hz.world.core.dao.model.UserLimitShare;
import com.hz.world.core.domain.dto.ForeverShareElementDTO;
import com.hz.world.core.domain.dto.OtherElementDTO;
import com.hz.world.core.domain.dto.limitShareElementDTO;
import com.hz.world.core.service.TargetService;
import com.hz.world.core.service.UserCashService;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: Description: author linyanchun date Feb 22, 2020
 */
@Slf4j
@Service
@Transactional
public class TargetServiceImpl implements TargetService {

	@Autowired
	private ConfigCacheUtil configCacheUtil;
	@Autowired
	private DateShareDaoImpl dateShareDao;
	@Autowired
	private UserLimitShareDaoImpl userLimitShareDao;
	@Autowired
	private UserCatchDaoImpl userCatchDao;
	@Autowired
	private UserBaseInfoService userBaseInfoService;
	@Autowired
	private UserCashService userCashService;
	@Override
	public Double getTodayTotalShare() {
		DateShare share = dateShareDao.findByDate(DateUtil.getTodayDate());
		if (share != null) {
			return share.getShare();
		}
		return 0d;
	}

	@Override
	public int getUserLimitShareCount(Long userId) {
		return userLimitShareDao.getCount(userId);
	}
	@Override
	public List<limitShareElementDTO> getLimitList(Long userId,Integer offset, Integer limit){
		List<limitShareElementDTO> result = new ArrayList<limitShareElementDTO>();
		List<UserLimitShare> list = userLimitShareDao.getList(userId, offset, limit);
		if (list != null && list.size() > 0) {
			for (UserLimitShare userLimitShare : list) {
				limitShareElementDTO data = changeData(userLimitShare);
				
				result.add(data);
			}
		}
		return result;
	}
	private limitShareElementDTO changeData(UserLimitShare userLimitShare ) {
		limitShareElementDTO data = new limitShareElementDTO();
		long countDomn = (userLimitShare.getFinishTime().getTime() - new Date().getTime()) / 1000;
		if (countDomn <=0) {
			countDomn = 0;
			data.setFinish(true);
		}else {
			data.setFinish(false);
		}
		
		data.setIncome(userLimitShare.getIncome());
		data.setTime(userLimitShare.getTime());
		data.setCountDown(countDomn);
		return data;
	}
	@Override
	public List<limitShareElementDTO> getUnfinishedList(Long userId) {
		List<limitShareElementDTO> result = new ArrayList<limitShareElementDTO>();
		List<UserLimitShare> list = userLimitShareDao.getUnfinishedList(userId);
		if (list != null && list.size() > 0) {
			for (UserLimitShare userLimitShare : list) {
				limitShareElementDTO data = changeData(userLimitShare);
				result.add(data);
			}
		}else {
			UserLimitShare record = userLimitShareDao.getLast(userId);
			if (record != null) {
				limitShareElementDTO data = changeData(record);
				result.add(data);
			}
		}
		return result;
	}
	@Override
	public int getForeverElementCount(Long userId) {
		return userCatchDao.userForeverCount(userId);
	}

	@Override
	public int getOtherElementCount(Long userId) {
		return userCatchDao.userOtherCount(userId);
	}
	
	@Override
	public List<OtherElementDTO> getOtherElementList(Long userId){
		List<OtherElementDTO> result = userCatchDao.getOtherList(userId);
		if (result != null && result.size() > 0) {
			for (OtherElementDTO otherElementDTO : result) {
				CatchConfig config = configCacheUtil.getCatchConfig(otherElementDTO.getCatchId());
				otherElementDTO.setName(config.getName());
				otherElementDTO.setIncomeAd(config.getAdditon() * otherElementDTO.getNum());
			}
		}
		return result;
	}
	@Override
	public List<ForeverShareElementDTO> getForeverElementList(){
		List<ForeverShareElementDTO> result = userCatchDao.getForeverList();
		if (result != null && result.size() > 0) {
			for (ForeverShareElementDTO element : result) {
				UserBaseInfoDTO user = userBaseInfoService.getByUserId(element.getUserId());
				element.setNickname(user.getNickname());
				element.setHeadImg(user.getHeadImg());
				element.setIncome(getTodayTotalShare());
			}
		}
		return result;
	}
	@Override
	public int addLimitShareElement(Long userId) {
		int count = getUserLimitShareCount(userId);
		double totalIncome = getTodayTotalShare();
		int time = 0;
		//第一次9分钟，第二次6分钟，后面的3分钟
		if (count == 0) {
			time = 9;
		}
		else if (count == 1) {
			time = 6 ;
		}
		else {
			time = 3;
		}
		Date create = new Date();
		Date finish = DateUtil.addHourMin(create, 0, (int)time);
		double income = totalIncome/10000/1440 * time;
		UserLimitShare share = new UserLimitShare();
		share.setUserId(userId);
		share.setId(IDGenerator.getUniqueId());
		share.setStatus(0);
		share.setIncome(income);
		share.setAddTime(create);
		share.setTime(time*60);
		share.setFinishTime(finish);
		userLimitShareDao.insert(share);
		return time;
	}
	@Override
	public void limitFinishedCash(Long userId) {
		List<UserLimitShare> list = userLimitShareDao.getFinishedList(userId);
		if (list != null && list.size() > 0) {
			for (UserLimitShare userLimitShare : list) {
				//增加现金收益
				if (userBaseInfoService.updateUserCash(userId, userLimitShare.getIncome())) {
					userCashService.createCashChangeLog(userId, userLimitShare.getIncome(), null, 1, CashChangeType.LIMIT_SHARE.getCode(), CashChangeType.LIMIT_SHARE.getDesc(), userLimitShare.getFinishTime());
					userLimitShare.setStatus(1);
					//更改记录状态
					userLimitShareDao.update(userLimitShare);
				}
				
				
			}
		}
	}

}
