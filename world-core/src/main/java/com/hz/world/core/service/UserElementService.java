package com.hz.world.core.service;

import java.util.List;

import com.hz.world.common.dto.ResultDTO;
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
	public ResultDTO<String> upgradeElement(Long userId, Integer element, Integer originLevel, Integer newLevel);
	

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
} 
