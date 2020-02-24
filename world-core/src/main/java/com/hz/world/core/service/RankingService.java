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
	
}
