package com.hz.world.core.service;

import com.hz.world.common.dto.ResultDTO;

public interface FortuneService {


	/**
	 * 福袋抽奖，每天一次
	 * @param userId
	 * @return
	 */
	public ResultDTO<Integer> draw(Long userId);
	
	/**
	 * 是否可以抽奖
	 * @param userId
	 * @return
	 */
	boolean canDraw(Long userId);
}
