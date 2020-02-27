/**
 * Java Framework Auto Generator Tools
 * Copyright (c) 2015-2020 www.wifi.com
 *
 * @company wifi
 */
package com.hz.world.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hz.world.common.ids.IDGenerator;
import com.hz.world.core.dao.impl.UserRewardLogDaoImpl;
import com.hz.world.core.dao.model.UserRewardLog;
import com.hz.world.core.service.UserRewardService;



@Service
public class UserRewardServiceImpl implements UserRewardService {

	
	@Autowired
	UserRewardLogDaoImpl userRewardLogDao;
	
	
 
    
    @Override
    public boolean createUserRewardLog(Long userId, Integer rewardType, Integer relatedId, String rewardCount,String rewardMessage) {
    	UserRewardLog record = new UserRewardLog();
    	record.setId(IDGenerator.getUniqueId());
    	record.setUserId(userId);
    	record.setRewardCount(rewardCount);
    	record.setRewardMessage(rewardMessage);
    	
    	record.setRewardType(rewardType);
    	record.setRelatedId(relatedId);
    	return userRewardLogDao.insert(record);
    }
    
}
