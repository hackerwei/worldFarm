package com.hz.world.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hz.world.common.enums.CollectType;
import com.hz.world.core.common.util.CoreCacheUtil;
import com.hz.world.core.service.AdService;
import com.hz.world.core.service.CollectService;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: Description: author linyanchun date Feb 22, 2020
 */
@Slf4j
@Service
@Transactional
public class AdServiceImpl implements AdService {

	@Autowired
	private CoreCacheUtil coreCacheUtil;
	@Autowired
	private CollectService collectService;
	
	@Override
	public void addUserAd(Long userId) {
		coreCacheUtil.addUserAd(userId);
		int num = getUserAdCount(userId);
		if (num >= 100) {
			collectService.collect(userId, CollectType.AD.getCode(), num+"");
		}
		
	}
    
    public int getUserAdCount(Long userId) {
    	return coreCacheUtil.getUserAdCount(userId);
    }
   
    public int getTodayAdCount(Long userId) {
    	return coreCacheUtil.getUserTodayAdCount(userId);
    }
  

}
