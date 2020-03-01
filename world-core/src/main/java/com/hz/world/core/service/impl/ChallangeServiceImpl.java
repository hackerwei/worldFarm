package com.hz.world.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hz.world.common.dto.ResultCodeEnum;
import com.hz.world.common.dto.ResultDTO;
import com.hz.world.core.common.util.ConfigCacheUtil;
import com.hz.world.core.dao.impl.UserChallengeLogDaoImpl;
import com.hz.world.core.service.ChallengeService;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 22, 2020 
 */
@Slf4j
@Service
@Transactional
public class ChallangeServiceImpl implements ChallengeService {

	@Autowired
	private ConfigCacheUtil configCacheUtil;
	@Autowired
	private UserChallengeLogDaoImpl userChallengeLogDao;
	

	@Override
	public ResultDTO<String> challange(Long userId, Integer element,Integer elementWeight, Integer totalWeight) {
		ResultDTO<String> resultDTO = new ResultDTO<String>();
		try {
			//根据元素和体重，config里面查找到的目前能达到的成就，
			//log里面判断该成就是否已经领取，
			//如果达到新的成就没有领取，则获取成就，增加元素的总体收益，UserElementService里的addElementAdd方法，然后记录日志
			
			//计算总的体重的成就和奖励UserElementService的addTotalAdd方法
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultDTO.set(ResultCodeEnum.SUCCESS, "ok");
		return resultDTO;
	}
	
}
