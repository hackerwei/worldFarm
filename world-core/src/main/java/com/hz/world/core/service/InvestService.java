package com.hz.world.core.service;

import java.util.List;

import com.hz.world.common.dto.ResultDTO;

public interface InvestService {


	/**
	 * 批量投资
	 * @param userId
	 * @return
	 */
	public ResultDTO<String> batchInvest(Long userId);
	
	/**
	 * 用户未投资过的列表
	 * @param userId
	 * @return
	 */
	public List<Integer> getUserUnInvestList(Long userId);
	
	/**
	 * 投资单个
	 * @param userId
	 * @param investId
	 * @return
	 */
	public ResultDTO<Integer> invest(Long userId, Integer investId);
	
}
