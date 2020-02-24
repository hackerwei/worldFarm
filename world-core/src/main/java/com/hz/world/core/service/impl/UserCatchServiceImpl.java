package com.hz.world.core.service.impl;

import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hz.world.common.dto.ResultCodeEnum;
import com.hz.world.common.dto.ResultDTO;
import com.hz.world.common.enums.ElementAdd;
import com.hz.world.core.common.util.ConfigCacheUtil;
import com.hz.world.core.common.util.CoreCacheUtil;
import com.hz.world.core.dao.impl.UserCatchDaoImpl;
import com.hz.world.core.dao.model.CatchConfig;
import com.hz.world.core.dao.model.UserCatch;
import com.hz.world.core.service.UserCatchService;
import com.hz.world.core.service.UserElementService;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 22, 2020 
 */
@Slf4j
@Service
public class UserCatchServiceImpl implements UserCatchService {

	@Autowired
	private CoreCacheUtil coreCacheUtil;
	@Autowired
	private ConfigCacheUtil configCacheUtil;
	@Autowired
	private UserCatchDaoImpl userCatchDao;
	@Autowired
	private UserElementService userElementService;

	@Override
	public ResultDTO<Integer> doCatch(Long userId, Integer year) {
		ResultDTO<Integer> resultDTO = new ResultDTO<Integer>();
		//是否已经捕捞
		if (userCatchDao.findByUserId(userId, year) != null) {
			resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "已经捕捞过");
			return resultDTO;
		}
		Integer catchId = 0; 
		List<CatchConfig> configList = configCacheUtil.getCatchList();
		int total = 0;
		int add = 0;
		if (configList != null && configList.size() > 0) {
			for (CatchConfig catchConfig : configList) {
				total += catchConfig.getWeight();
			}
			int random = RandomUtils.nextInt(1, total);
			int tmp = 0;
			for (CatchConfig catchConfig : configList) {
				tmp += catchConfig.getWeight();
				if (tmp >= random) {
					catchId = catchConfig.getId();
					add = catchConfig.getAdditon();
					break;
				}
			}
		}
		if (catchId > 0) {
			UserCatch record = new UserCatch();
			record.setUserId(userId);
			record.setYear(year);
			record.setCatchId(catchId);
			userCatchDao.insert(record);
			//永久分红小龙虾
			if (catchId == 1) {
				coreCacheUtil.addForeverCatchSet(userId);
				double score = 100000+year*10;
				coreCacheUtil.addCatchSet(userId, score);
			}else {
				double score = year*10 + catchId ;
				//无效数据不插入
				if (coreCacheUtil.getCatchSetCount() >= 100) {
					double minScore = coreCacheUtil.getCatchMinScore();
					if (score >= minScore) {
						coreCacheUtil.addCatchSet(userId, score);
					}
				}else {
					coreCacheUtil.addCatchSet(userId, score);
				}
				
			}
			
			String value = coreCacheUtil.getUserElementValue(userId, 1, ElementAdd.CATCH.getCode());
			if (StringUtils.isNoneEmpty(value)) {
				add += Integer.parseInt(value);
			}
			//更新收益加成
			userElementService.addElementAdd(userId, 1, ElementAdd.CATCH.getCode(), add+"");
			resultDTO.set(ResultCodeEnum.SUCCESS, "OK",catchId);
		}
		return resultDTO;
	}
	
}
