package com.hz.world.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hz.world.common.dto.ResultCodeEnum;
import com.hz.world.common.dto.ResultDTO;
import com.hz.world.common.enums.ElementAdd;
import com.hz.world.core.common.util.ConfigCacheUtil;
import com.hz.world.core.dao.impl.UserCollectLogDaoImpl;
import com.hz.world.core.dao.model.CollectConfig;
import com.hz.world.core.dao.model.UserCollectLog;
import com.hz.world.core.domain.dto.CollectDTO;
import com.hz.world.core.service.CollectService;
import com.hz.world.core.service.UserElementService;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: Description: author linyanchun date Feb 22, 2020
 */
@Slf4j
@Service
@Transactional
public class CollectServiceImpl implements CollectService {

	@Autowired
	private ConfigCacheUtil configCacheUtil;
	@Autowired
	private UserCollectLogDaoImpl userCollectLogDao;
	@Autowired
	private UserElementService userElementService;
	@Override
	public ResultDTO<String> collect(Long userId, Integer type,String num) {
		ResultDTO<String> resultDTO = new ResultDTO<String>();
		try {
			List<CollectConfig> configList = configCacheUtil.findByType(type,num);
			if (configList != null && configList.size() > 0) {
				List<UserCollectLog> userCollectList = userCollectLogDao.getUserFinishList(userId, type);
				for (CollectConfig collectConfig : configList) {
					if (!isInList(collectConfig.getId(), userCollectList)) {	
						//全体收益
						if (collectConfig.getRewardElement().equals(11)) {
							userElementService.addTotalAddWithoutUpdate(userId, ElementAdd.COLLECT.getCode(), collectConfig.getPower());
						}
						//单元素收益
						else {
							userElementService.addElementAddWithoutUpdate(userId, collectConfig.getRewardElement(), ElementAdd.COLLECT.getCode(), collectConfig.getPower()+"");
						
						}
						//增加记录
						UserCollectLog record = new UserCollectLog();
						record.setUserId(userId);
						record.setCollectId(collectConfig.getId());
						record.setType(collectConfig.getType());
						userCollectLogDao.insert(record);
					}
				}	
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultDTO.set(ResultCodeEnum.SUCCESS, "ok");
		return resultDTO;
	}
	public List<CollectDTO> userCollectList(Long userId){
		List<CollectDTO> resultList = new ArrayList<CollectDTO>();
		List<CollectConfig> configList = configCacheUtil.getCollectList();
		List<UserCollectLog> userCollectList = userCollectLogDao.getUserAllFinishList(userId);
		for (CollectConfig collectConfig : configList) {
			CollectDTO collectDTO = new CollectDTO();
			collectDTO.setId(collectConfig.getId());
			collectDTO.setFinish(false);
			if (isInList(collectConfig.getId(), userCollectList)) {
				collectDTO.setFinish(true);
			}
			resultList.add(collectDTO);
		}
		return resultList;
	}
	private boolean isInList(Integer configId,List<UserCollectLog> list) {
		if (list != null && list.size() > 0) {
			for (UserCollectLog userCollectLog : list) {
				if (userCollectLog.getCollectId().equals(configId)) {
					return true;
				}
			}
		}
		return false;
	}

}
