/**
 * Java Framework Auto Generator Tools
 * Copyright (c) 2015-2020 www.wifi.com
 *
 * @company wifi
 */
package com.hz.world.core.service;

import java.util.List;

import com.hz.world.core.dao.model.UnionConfig;
import com.hz.world.core.dao.model.UserUnionIncomeLog;

/**
 * Title: Description: author linyanchun date Feb 27, 2020
 */
public interface UnionService {

	/**
	 * 用户总的收益
	 * 
	 * @param userId
	 * @return
	 */
	public double getUserUnionIncome(Long userId);

	/**
	 * 用户当日收益
	 * 
	 * @param userId
	 * @return
	 */
	public double getUserTodayUnionIncome(Long userId);

	/**
	 * 获取用户当前等级
	 * 
	 * @param userId
	 * @return
	 */
	public UnionConfig getUnionStep(Long userId);

	public UnionConfig getStepById(Integer id);

	public boolean getReward(Long userId, Integer step);

	/**
	 * 联盟收益列表
	 * @param userId
	 * @param offset
	 * @param limit
	 * @return
	 */
	public List<UserUnionIncomeLog> getLogList(Long userId, Integer offset, Integer limit);
}
