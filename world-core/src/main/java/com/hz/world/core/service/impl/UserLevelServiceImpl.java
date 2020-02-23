package com.hz.world.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hz.world.account.domain.dto.UserBaseInfoDTO;
import com.hz.world.account.service.UserBaseInfoService;
import com.hz.world.core.common.util.CoreCacheUtil;
import com.hz.world.core.dao.impl.UserCoinChangeLogDaoImpl;
import com.hz.world.core.dao.impl.UserCoinDaoImpl;
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
public class UserLevelServiceImpl implements UserLevelService {

	@Autowired
	private CoreCacheUtil coreCacheUtil;
	@Autowired
	private UserCoinDaoImpl userCoinDao;
	@Autowired
	private UserCoinChangeLogDaoImpl userCoinChangeLog;
	@Autowired
	private UserBaseInfoService userBaseInfoService;


	@Override
	public boolean updateLevelByIncome(Long userId, String income) {
		int level = coreCacheUtil.getIncomeLevel(income);
		UserBaseInfoDTO user = userBaseInfoService.getByUserId(userId);
		if (user != null && user.getLevel() < level) {
			user.setLevel(level);
			userBaseInfoService.update(user);
			return true;
		}
		return false;
	}
	
}
