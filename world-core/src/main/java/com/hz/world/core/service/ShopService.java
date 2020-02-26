package com.hz.world.core.service;

import com.hz.world.common.dto.ResultDTO;
import com.hz.world.core.domain.dto.ShopResultDTO;

public interface ShopService {


	/**
	 * 购买商品
	 * @param userId
	 * @param productId
	 * @return
	 */
	public ResultDTO<ShopResultDTO> buy(Long userId, Integer productId);
	
	
	
}
