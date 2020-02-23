package com.hz.world.core.service;

import com.hz.world.common.dto.ResultDTO;
import com.hz.world.core.domain.dto.UserCoinDTO;

public interface UserCoinService {


	/**
	 * 初始化用户金币
	 * @param userId
	 * @return
	 */
	public boolean createUserCoin(Long userId);
	
	/**
	 * 获取用户金币
	 * @param userId
	 * @return
	 */
	public UserCoinDTO getUserCoin(Long userId);
	
	/**
	 * 刷新离线收益
	 * @param userId
	 * @return
	 */
	public void updateUserCoin(Long userId);
	
	/**
	 * 金币变动
	 * @param userId
	 * @param amount
	 * @param relatedType
	 * @param changeType 0:增加，1:减少
	 * @return
	 */
	public ResultDTO<String> changeUserCoin(Long userId, String amount, Integer relatedType, int changeType);
	
}
