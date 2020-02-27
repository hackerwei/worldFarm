package com.hz.world.core.service.impl;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hz.world.account.domain.dto.UserBaseInfoDTO;
import com.hz.world.account.service.UserBaseInfoService;
import com.hz.world.common.dto.ResultCodeEnum;
import com.hz.world.common.dto.ResultDTO;
import com.hz.world.common.enums.DiamondChangeType;
import com.hz.world.common.ids.IDGenerator;
import com.hz.world.common.util.DateUtil;
import com.hz.world.core.common.util.CoreCacheUtil;
import com.hz.world.core.dao.impl.UserFortuneLogDaoImpl;
import com.hz.world.core.dao.model.UserFortuneLog;
import com.hz.world.core.service.FortuneService;
import com.hz.world.core.service.UserDiamondService;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 22, 2020 
 */
@Slf4j
@Service
public class FortuneServiceImpl implements FortuneService {

	@Autowired
	private CoreCacheUtil coreCacheUtil;
	@Autowired
	private UserFortuneLogDaoImpl userFortuneLogDao;
	@Autowired
	private UserBaseInfoService userBaseInfoService;
	@Autowired
	private UserDiamondService userDiamondService;

	@Override
	public ResultDTO<Integer> draw(Long userId) {
		ResultDTO<Integer> resultDTO = new ResultDTO<Integer>();
		//是否已经捕捞
		if (coreCacheUtil.isFortuneToday(userId)) {
			resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "已经领过");
			return resultDTO;
		}
		int random = RandomUtils.nextInt(1, 100);
		int reward = 0;
		if (random == 1) { //随机81-100钻石奖励
			reward = RandomUtils.nextInt(81, 100);
		}
		else if (random > 1 && random <=40) { //随机51-80钻石奖励
			reward = RandomUtils.nextInt(51, 80);
		}else {
			reward = RandomUtils.nextInt(20, 50);
		}
		if (!userBaseInfoService.updateUserDiamond(userId, reward)) {
			resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "更新钻石失败");
			return resultDTO;
		}
		UserBaseInfoDTO user = userBaseInfoService.getByUserId(userId);
	
		userDiamondService.createDiamondChangeLog(userId, reward, user.getDiamond(), DiamondChangeType.FORTUNE.getCode());
		UserFortuneLog record = new UserFortuneLog();
		record.setDate(DateUtil.getTodayDate());
		record.setUserId(userId);
		record.setDiamondCount(reward);
		record.setId(IDGenerator.getUniqueId());
		userFortuneLogDao.insert(record);
		resultDTO.set(ResultCodeEnum.SUCCESS, "OK",reward);
		coreCacheUtil.setFortuneToday(userId);
		return resultDTO;
	}
	
	@Override
	public boolean canDraw(Long userId) {
		return !coreCacheUtil.isFortuneToday(userId);
	}
	
}
