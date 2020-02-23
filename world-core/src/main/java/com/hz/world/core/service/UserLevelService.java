package com.hz.world.core.service;

public interface UserLevelService {



	/**
	 * 用户总收入，升级
	 * @param userId
	 * @param amount
	 */
	public boolean updateLevelByIncome(Long userId, String amount);
	
}
