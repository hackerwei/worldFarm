package com.hz.world.core.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hz.world.common.constant.CoinConstants;
import com.hz.world.common.dto.ResultCodeEnum;
import com.hz.world.common.dto.ResultDTO;
import com.hz.world.common.enums.CoinChangeType;
import com.hz.world.common.enums.CollectType;
import com.hz.world.common.enums.ElementAdd;
import com.hz.world.common.ids.IDGenerator;
import com.hz.world.core.common.util.CoreCacheUtil;
import com.hz.world.core.dao.impl.UserCoinChangeLogDaoImpl;
import com.hz.world.core.dao.impl.UserCoinDaoImpl;
import com.hz.world.core.dao.impl.UserTotalIncomeDaoImpl;
import com.hz.world.core.dao.model.UserCoin;
import com.hz.world.core.dao.model.UserCoinChangeLog;
import com.hz.world.core.dao.model.UserTotalIncome;
import com.hz.world.core.domain.dto.UserCoinDTO;
import com.hz.world.core.domain.dto.UserTmpIncomeDTO;
import com.hz.world.core.service.CollectService;
import com.hz.world.core.service.UserCoinService;
import com.hz.world.core.service.UserLevelService;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 22, 2020 
 */
@Slf4j
@Service
public class UserCoinServiceImpl implements UserCoinService {

	@Autowired
	private CoreCacheUtil coreCacheUtil;
	@Autowired
	private UserCoinDaoImpl userCoinDao;
	@Autowired
	private UserTotalIncomeDaoImpl userTotalIncomeDao;
	@Autowired
	private UserCoinChangeLogDaoImpl userCoinChangeLog;
	@Autowired
	private UserLevelService userLevelService;
	@Autowired
	private CollectService collectService;
	
	@Override
	public boolean createUserCoin(Long userId) {
		UserCoin userCoin = new UserCoin();
		userCoin.setUserId(userId);
		userCoin.setIncomeRate("0");
		userCoin.setCoin("100");
		userCoin.setUpdateTime(new Date().getTime()/1000);
		coreCacheUtil.addUserTotalAdd(userId,ElementAdd.YEAR.getCode(),1);
		return userCoinDao.insert(userCoin);
	}
	@Override
	public UserTmpIncomeDTO createTmpUserIncome(Long userId) {
		long now = new Date().getTime()/1000;
		UserTmpIncomeDTO income = new UserTmpIncomeDTO();
		income.setCoin("0");
		income.setStartTime(now);
		income.setUpdateTime(now);
		UserCoinDTO coin = getUserCoin( userId);
		if (coin != null) {
			income.setIcomeRate(coin.getIcomeRate());
		}
		coreCacheUtil.createTmpUserIncome(userId, income);
		return income;
	}
	@Override
	public UserCoinDTO getUserCoin(Long userId) {
		return coreCacheUtil.getUserCoin(userId);
	}

	@Override
	public void updateUserCoin(Long userId) {
		try {
			UserCoinDTO coin = getUserCoin(userId);
			String income = coreCacheUtil.getUserIncome(userId);
			long now = new Date().getTime()/1000;
			if (StringUtils.isNoneEmpty(coin.getIcomeRate()) &&  coin.getIcomeRate() != "0") {
				BigDecimal b1 = new BigDecimal(coin.getCoin()) ;
				BigDecimal b2 = new BigDecimal(coin.getIcomeRate()) ;
				BigDecimal b3 = new BigDecimal(now-coin.getUpdateTime()) ;
				BigDecimal b4 = b2.multiply(b3);
				BigDecimal b5 =new BigDecimal(income) ;
				b1 = b1.add(b4);
				b5 = b5.add(b4);
				coin.setCoin(b1.toString()); 
				coin.setUpdateTime(now);
				coreCacheUtil.updateCoin(coin);
				updateTotalIncome(userId,b5.toString());
				//是否升级
				boolean upgrade = userLevelService.updateLevelByIncome(userId, b5.toString());
		
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("更新金币发生异常："+userId);
		}
		
	}
	private void updateTotalIncome(Long userId, String income) {
		//增加总收益
		coreCacheUtil.updateUserIncome(userId, income);
		UserTotalIncome total = userTotalIncomeDao.findByUserId(userId);
		if (total == null) {
			total = new UserTotalIncome();
			total.setUserId(userId);
			total.setIncome(income);
			userTotalIncomeDao.insert(total);
		}else {
			total.setIncome(income);
			userTotalIncomeDao.update(total);
			BigDecimal b1 = new BigDecimal(income) ;
			BigDecimal b2 = new BigDecimal("10000000") ;
			//总收益达到一定程度，完成收集
			if (b1.compareTo(b2) >= 0) {
		//		collectService.collect(userId, CollectType.COIN.getCode(), income);
			}
		}
	}
	@Override
	public ResultDTO<String> changeUserCoin(Long userId, String amount, Integer relatedType, int changeType) {
		ResultDTO<String> resultDTO = new ResultDTO<String> ();
		updateUserCoin(userId);
		try {
			UserCoinDTO coin = getUserCoin(userId);
			BigDecimal b1 = new BigDecimal(coin.getCoin()) ;
			BigDecimal b2 = new BigDecimal(amount) ;
			//增加
			if (changeType == 0) {
				b1 = b1.add(b2);
				updateTotalIncome(userId,b1.toString());
			}else{
				if (b1.compareTo(b2) < 0) {
					log.error("更新用户金币失败，金币不足{},{},{},{}",userId,amount,relatedType,changeType);
					resultDTO.set(ResultCodeEnum.ERROR_AUTH, "金币不足");
					return resultDTO;
				}
				b1 = b1.subtract(b2);		
			}
			coin.setCoin(b1.toString()); 
			coreCacheUtil.updateCoin(coin);
			UserCoinChangeLog record = new UserCoinChangeLog();
			record.setId(IDGenerator.getUniqueId());
			record.setUserId(userId);
			record.setRelatedType(relatedType);
			record.setNum(b2.toString());
			record.setAfterNum(b1.toString());
			userCoinChangeLog.insert(record);
			resultDTO.set(ResultCodeEnum.SUCCESS, "OK");
		} catch (Exception e) {
			e.printStackTrace();
			resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "异常");
		} 
    	return resultDTO;
	}
	

	private void updateUserTmpIncome(Long userId) {
		try {
			UserTmpIncomeDTO coin = coreCacheUtil.getTmpIncome(userId);
			if (coin == null) {
				return;
			}
			long now = new Date().getTime()/1000;
			int timeTmp = CoinConstants.COUNT_DOWN;
			if (StringUtils.isNoneEmpty(coin.getIcomeRate()) &&  coin.getIcomeRate() != "0") {
				long timeDiff  = coin.getUpdateTime()- coin.getStartTime();
				if (timeDiff >= timeTmp || timeDiff<= 0) {
					return;
				}
				if (now -coin.getStartTime() >=  timeTmp) {
					timeDiff = coin.getStartTime() + timeTmp - coin.getUpdateTime();
				}else {
					timeDiff = now - coin.getUpdateTime();
				}		
				BigDecimal b1 = new BigDecimal(coin.getCoin()) ;
				BigDecimal b2 = new BigDecimal(coin.getIcomeRate()) ;
				BigDecimal b3 = new BigDecimal(timeDiff) ;
				BigDecimal b4 = b2.multiply(b3);
				b1 = b1.add(b4);
				coin.setCoin(b1.toString()); 
				coin.setUpdateTime(now);
				coin.setStartTime(coin.getStartTime());
				coin.setIcomeRate(coin.getIcomeRate());
				coreCacheUtil.createTmpUserIncome(userId, coin);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("更新临时收益发生异常："+userId);
		}
	}
	@Override
	public void updateUserIncome(Long userId) {
		updateUserCoin(userId);
		//更新临时收益
		updateUserTmpIncome(userId);
	}
	@Override
	public UserTmpIncomeDTO getTmpIncome(Long userId) {
		UserTmpIncomeDTO income = coreCacheUtil.getTmpIncome(userId);
		if (income == null) {
			income = createTmpUserIncome(userId);
		}
		return income;
	}
	@Override
	public void updateOutput(Long userId, String output) {
		UserCoinDTO coin = coreCacheUtil.getUserCoin(userId);
		coin.setIcomeRate(output);
		coreCacheUtil.updateCoin(coin);
		UserTmpIncomeDTO incomeDTO = getTmpIncome(userId);
		incomeDTO.setIcomeRate(output);
		coreCacheUtil.createTmpUserIncome(userId, incomeDTO);
	}
	@Override
	public ResultDTO<String> recvImpIcome(Long userId) {
		ResultDTO<String> resultDTO = new ResultDTO<String> ();
		updateUserTmpIncome(userId);
		UserTmpIncomeDTO income =  getTmpIncome(userId);
		String coin = income.getCoin();
		ResultDTO<String> resultDTO2 = changeUserCoin( userId, coin, CoinChangeType.ADD.getCode(), 0);
		if (resultDTO2.isSuccess()) {
			resultDTO.set(ResultCodeEnum.SUCCESS, "OK",coin);
			createTmpUserIncome(userId);
			return resultDTO;
		}
		resultDTO.set(ResultCodeEnum.ERROR_HANDLE, resultDTO2.getErrDesc());
		
		log.error("领取3小时奖励失败，{},{}",userId,coin);
		return resultDTO;
	}
}
