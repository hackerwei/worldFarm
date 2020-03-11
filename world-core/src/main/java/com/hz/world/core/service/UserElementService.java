package com.hz.world.core.service;

import java.util.List;

import com.hz.world.common.dto.ResultDTO;
import com.hz.world.core.domain.dto.FeedResultDTO;
import com.hz.world.core.domain.dto.UserElementDTO;

public interface UserElementService {
	
	
	/**
	 * 升级元素
	 * @param userId
	 * @param element
	 * @param originLevel
	 * @param newLevel
	 * @return
	 */
	public ResultDTO<List<FeedResultDTO>>  upgradeElement(Long userId, Integer element, Integer originLevel, Integer newLevel);
	

	/**
	 * 获取用户每秒产出
	 * @param userId
	 * @return
	 */
	public String getUserOutput(Long userId);
	

	/**
	 * 获取用户元素列表
	 * @param userId
	 * @return
	 */
	public List<UserElementDTO> getUserElementList(Long userId);
	

	/**
	 * 获取单个元素
	 * @param userId
	 * @param element
	 * @return
	 */
	public UserElementDTO getUserElement(Long userId, Integer element);
	
	/**
	 * 更新元素收益加成
	 * @param userId
	 * @param element
	 * @param field
	 * @param value
	 * @return
	 */
	public ResultDTO<String> addElementAdd(Long userId, Integer element, String field, String value);
	

	/**
	 * 更新总收益系数
	 * @param userId
	 * @param field
	 * @param value
	 * @return
	 */
	public ResultDTO<String> addTotalAdd(Long userId, String field, Integer value);
	
	/**
	 * 获取单系统的的总收益加成
	 * @param userId
	 * @param field
	 * @return
	 */
	public String getUserTotalAddByField(Long userId, String field);
	
	/**
	 * 清除所有体重
	 * @param userId
	 */
	public void clearElement(Long userId);
} 
