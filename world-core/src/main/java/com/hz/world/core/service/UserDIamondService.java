package com.hz.world.core.service;

public interface UserDIamondService {


	/**
	 * @param userId
	 * @param num
	 * @param afterNum
	 * @param type
	 * @return
	 */
	public boolean createDiamondChangeLog(Long userId,Integer num, Integer afterNum, Integer type);
	
	
}
