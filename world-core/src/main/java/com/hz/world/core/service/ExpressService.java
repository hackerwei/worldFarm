package com.hz.world.core.service;

import com.hz.world.common.dto.ResultDTO;
import com.hz.world.core.domain.dto.ExpressInfoDTO;
import com.hz.world.core.domain.dto.ExpressResultDTO;

public interface ExpressService {



	/**
	 * 获取快递
	 * @param userId
	 * @return
	 */
	public ResultDTO<ExpressResultDTO> getReward(Long userId);
	
	/**
	 * 倒计时
	 * @param userId
	 * @return
	 */
	public ExpressInfoDTO getCountDown(Long userId);
}
