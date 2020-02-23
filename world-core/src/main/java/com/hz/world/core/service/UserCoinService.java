package com.hz.world.core.service;

import com.hz.world.common.dto.ResultDTO;
import com.hz.world.core.domain.dto.UserCoinDTO;
import com.hz.world.core.domain.dto.UserTmpIncomeDTO;

public interface UserCoinService {


	/**
	 * 初始化用户金币
	 * @param userId
	 * @return
	 */
	public boolean createUserCoin(Long userId);
	
	/**
	 * 创建临时收益
	 * @param userId
	 * @return
	 */
	public UserTmpIncomeDTO createTmpUserIncome(Long userId);
	
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
	
	/**
	 * 更新用户收益
	 * @param userId
	 */
	public void updateUserIncome(Long userId);
	
	/**
	 * 获取用户临时收益
	 * @param userId
	 * @return
	 */
	public UserTmpIncomeDTO getTmpIncome(Long userId);
	
	/**
	 * 更新用户产出速率
	 * @param userId
	 * @param output
	 */
	public void updateOutput(Long userId, String output);
	
	/**
	 * 获取3小时的临时收益
	 * @param userId
	 * @return
	 */
	public ResultDTO<String> recvImpIcome(Long userId);
}
