package com.hz.world.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hz.world.core.common.util.CoreCacheUtil;
import com.hz.world.core.domain.dto.RankDTO;
import com.hz.world.core.service.RankingService;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 22, 2020 
 */
@Slf4j
@Service
public class RankingServiceImpl implements RankingService {

	@Autowired
	private CoreCacheUtil coreCacheUtil;



	@Override
	public List<RankDTO> getRankingList(Integer type) {
		return coreCacheUtil.getRankingList(type);
	}
	
}
