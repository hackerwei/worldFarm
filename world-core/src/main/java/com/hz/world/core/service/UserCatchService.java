package com.hz.world.core.service;

import com.hz.world.common.dto.ResultDTO;

public interface UserCatchService {


	/**
	 * 用户根据年份抽一次奖
	 * @param userId
	 * @param year
	 * @return
	 */
	public ResultDTO<Integer> doCatch(Long userId, Integer year);
	
}
