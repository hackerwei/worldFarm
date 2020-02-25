package com.hz.world.core.service;

import java.util.List;

import com.hz.world.common.dto.ResultDTO;
import com.hz.world.core.dao.model.RechargeConfig;

public interface RechargeService {




	/**
	 * 充值
	 * @param userId
	 * @param rechargeId
	 * @return
	 */
	public ResultDTO<String> recharge(Long userId, Integer rechargeId);
	
	/**
	 * 充值配置
	 * @return
	 */
	public List<RechargeConfig> getConfigList();
	
}
