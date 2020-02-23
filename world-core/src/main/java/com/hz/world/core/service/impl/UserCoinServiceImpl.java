package com.hz.world.core.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hz.world.common.dto.ResultCodeEnum;
import com.hz.world.common.dto.ResultDTO;
import com.hz.world.common.enums.CoinChangeType;
import com.hz.world.common.ids.IDGenerator;
import com.hz.world.core.common.util.CoreCacheUtil;
import com.hz.world.core.dao.impl.UserCoinChangeLogDaoImpl;
import com.hz.world.core.dao.impl.UserCoinDaoImpl;
import com.hz.world.core.dao.model.UserCoin;
import com.hz.world.core.dao.model.UserCoinChangeLog;
import com.hz.world.core.domain.dto.UserCoinDTO;
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
	private UserCoinChangeLogDaoImpl userCoinChangeLog;
	@Autowired
	private UserLevelService userLevelService;

	/**
	 * 微信登录接口,用户不存在进行注册，否则登录
	 *
	 * @param appId
	 * @param uuid
	 * @param userLoginDTO
	 * @return
	 */
	@Override
	public boolean createUserCoin(Long userId) {
		UserCoin userCoin = new UserCoin();
		userCoin.setUserId(userId);
		userCoin.setIncomeRate("0");
		userCoin.setCoin("100");
		userCoin.setUpdateTime(new Date().getTime()/1000);
		return userCoinDao.insert(userCoin);
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
				//增加总收益
				coreCacheUtil.updateUserIncome(userId, b5.toString());
				//是否升级
				boolean upgrade = userLevelService.updateLevelByIncome(userId, b5.toString());
				UserCoinChangeLog record = new UserCoinChangeLog();
				record.setId(IDGenerator.getUniqueId());
				record.setUserId(userId);
				record.setRelatedType(CoinChangeType.ADD.getCode());
				record.setNum(b4.toString());
				record.setAfterNum(b1.toString());
				userCoinChangeLog.insert(record);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("更新金币发生异常："+userId);
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
}
