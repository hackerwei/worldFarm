package com.hz.world.core.service;

import java.util.List;

import com.hz.world.core.domain.dto.RankDTO;

public interface RankingService {


	/**
	 * 排行榜
	 * @param type：0：年份榜，1：总收益榜
	 * @return
	 */
	public List<RankDTO> getRankingList(Integer type);

	/**
	 * 获取自己的数据
	 * @param userId
	 * @return
	 */
	public RankDTO getMyrank(Long userId, Integer type);
	
}
