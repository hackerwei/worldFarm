package com.hz.world.core.service;

import com.hz.world.common.dto.ResultDTO;
import com.hz.world.core.domain.dto.TakeOutResultDTO;

public interface TakeOutService {



	/**
	 * 获取外卖
	 * @param userId
	 * @return
	 */
	public ResultDTO<TakeOutResultDTO> getReward(Long userId);
	
	/**
	 * 还剩多少次机会
	 * @param userId
	 * @return
	 */
	public int getChance(Long userId);
}
