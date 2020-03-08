package com.hz.world.core.service;

import java.util.List;

import com.hz.world.common.dto.ResultDTO;
import com.hz.world.core.dao.model.UserRequireCoinLog;
import com.hz.world.core.dao.model.UserSendCoinLog;
import com.hz.world.core.domain.dto.BossDTO;
import com.hz.world.core.domain.dto.ManagerDTO;

public interface FactoryService {
	
	
	/**
	 * 邀请成为厂长
	 * @param userId
	 * @param element
	 * @param toUserId
	 * @return
	 */
	public ResultDTO<String> inviteUser(Long userId, Integer element, Long toUserId);
	
	/**
	 * 解雇厂长
	 * @param userId
	 * @param element
	 * @param toUserId
	 * @return
	 */
	public boolean fireManager(Long userId, Integer element, Long toUserId);
	

	/**
	 * 厂长列表
	 * @param userId
	 * @return
	 */
	public List<ManagerDTO> getElementManagerList(Long userId);
	
	/**
	 * 我的打工列表
	 * @param userId
	 * @return
	 */
	public List<BossDTO> workingList(Long userId);
	
	/**
	 * 辞职
	 * @param userId
	 * @param bossId
	 * @return
	 */
	public boolean quit(Long userId, Long bossId);
	

	/**
	 * 要金币
	 * @param userId
	 * @param bossId
	 * @return
	 */
	public ResultDTO<String> requireCoin(Long userId, Long bossId);
	

	/**
	 * 发送金币
	 * @param userId
	 * @param managerId
	 * @return
	 */
	public ResultDTO<String> sendCoin(Long userId, Long managerId);
	
	public UserSendCoinLog getUnReadSendLog(Long userId) ;
	public UserRequireCoinLog getUnReadRequireLog(Long userId);
	
} 
