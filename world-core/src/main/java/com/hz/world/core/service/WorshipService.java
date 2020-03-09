package com.hz.world.core.service;

import com.hz.world.common.dto.ResultDTO;
import com.hz.world.core.domain.dto.ExpressInfoDTO;
import com.hz.world.core.domain.dto.OfferResultDTO;
import com.hz.world.core.domain.dto.WorshipDTO;

public interface WorshipService {



	
	/**
	 * 倒计时
	 * @param userId
	 * @return
	 */
	public ExpressInfoDTO getCountDown(Long userId);
	
	/**
	 * 清除倒计时
	 * @param userId
	 */
	public ResultDTO<String> clearCountDown(Long userId);
	
	/**
	 * 获取祭祀信息
	 * @param userId
	 * @return
	 */
	public WorshipDTO getWorship(Long userId);
	
	/**祭祀
	 * @param userId
	 * @return
	 */
	public ResultDTO<OfferResultDTO> offer(Long userId);
}
