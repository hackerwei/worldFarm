package com.hz.world.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSException;

import com.hz.world.account.service.FriendService;
import com.hz.world.account.service.UserBaseInfoService;
import com.hz.world.common.enums.CashChangeType;
import com.hz.world.core.common.util.ConfigCacheUtil;
import com.hz.world.core.dao.impl.UserUnionIncomeLogDaoImpl;
import com.hz.world.core.dao.impl.UserUnionStepDaoImpl;
import com.hz.world.core.dao.model.UnionConfig;
import com.hz.world.core.dao.model.UserUnionIncomeLog;
import com.hz.world.core.dao.model.UserUnionStep;
import com.hz.world.core.service.AdService;
import com.hz.world.core.service.UnionService;
import com.hz.world.core.service.UserCashService;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 22, 2020 
 */
@Slf4j
@Service
public class UnionServiceImpl implements UnionService {


	@Autowired
	private FriendService friendService;
	@Autowired
	private AdService adService;
	@Autowired
	private UserUnionStepDaoImpl userUnionStepDao;
	@Autowired
	private ConfigCacheUtil configCacheUtil;
	@Autowired
	private UserBaseInfoService userBaseInfoService;
	@Autowired
	private UserCashService userCashService;
	@Autowired
	private UserUnionIncomeLogDaoImpl userUnionIncomeLogDao;

	@Override
	public double getUserUnionIncome(Long userId) {
		int adCount = adService.getUserAdCount(userId);
		int friendCount = friendService.getFriendCount(userId);
		double income = (adCount*0.03 +friendCount*0.5)/10;
		return income;
	}
	
	@Override
	public double getUserTodayUnionIncome(Long userId) {
		int adCount = adService.getTodayAdCount(userId);
		int friendCount = friendService.getTodayFriendCount(userId);
		double income = (adCount*0.03 +friendCount*0.5)/10;
		return income;
	}
	@Override
	public UnionConfig getUnionStep(Long userId) {
		UserUnionStep step = userUnionStepDao.findByUserId(userId);
		if (step == null) {
			step = new UserUnionStep();
			step.setStep(1);
			step.setUserId(userId);
			userUnionStepDao.insert(step);
			return configCacheUtil.getUnionConfig(1);
		}
		else {
			return configCacheUtil.getUnionConfig(step.getStep());
		}
	}
	@Override
	public UnionConfig getStepById(Integer id) {
		return configCacheUtil.getUnionConfig(id);
	}
	@Override
	public boolean getReward(Long userId, Integer step) {
		UnionConfig config = configCacheUtil.getUnionConfig(step);
		if (!userBaseInfoService.updateUserCash(userId, config.getTarget())) {
			return false;
		}
		UserUnionStep userStep = userUnionStepDao.findByUserId(userId);
		userStep.setStep(step+1);
		userUnionStepDao.update(userStep);
		userCashService.createCashChangeLog(userId, config.getTarget(),null,1, CashChangeType.UNION.getCode(), CashChangeType.UNION.getDesc());
		return true;
	}
	@Override
	public List<UserUnionIncomeLog> getLogList(Long userId, Integer offset, Integer limit){
		return userUnionIncomeLogDao.getLogList(userId, offset, limit);
	}
	
}
