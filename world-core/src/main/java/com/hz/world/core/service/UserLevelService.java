package com.hz.world.core.service;

import com.hz.world.common.dto.ResultDTO;
import com.hz.world.core.domain.dto.UserCoinDTO;

public interface UserLevelService {



	/**
	 * 用户总收入，升级
	 * @param userId
	 * @param amount
	 */
	public boolean updateLevelByIncome(Long userId, String amount);
	
}
