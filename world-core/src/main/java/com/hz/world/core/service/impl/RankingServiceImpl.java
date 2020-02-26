package com.hz.world.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hz.world.account.domain.dto.UserBaseInfoDTO;
import com.hz.world.account.service.UserBaseInfoService;
import com.hz.world.core.common.util.ConfigCacheUtil;
import com.hz.world.core.common.util.CoreCacheUtil;
import com.hz.world.core.dao.impl.UserCatchDaoImpl;
import com.hz.world.core.dao.impl.UserTotalIncomeDaoImpl;
import com.hz.world.core.dao.model.CatchConfig;
import com.hz.world.core.dao.model.UserCatch;
import com.hz.world.core.dao.model.UserTotalIncome;
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
	@Autowired 
	private ConfigCacheUtil configCacheUtil;
	
	@Autowired 
	private UserBaseInfoService userBaseInfoService;
	@Autowired 
	private UserCatchDaoImpl userCatchDao;
	@Autowired 
	private UserTotalIncomeDaoImpl userTotalIncomeDao;
	
	@Override
	public List<RankDTO> getRankingList(Integer type) {
		return coreCacheUtil.getRankingList(type);
	}
	@Override
	public RankDTO getMyrank(Long userId, Integer type) {
		RankDTO result = new RankDTO();
		UserBaseInfoDTO user = userBaseInfoService.getByUserId(userId);
		result.setUserId(userId);
		result.setHeadImg(user.getHeadImg());
		result.setRank(0);
		result.setYear(user.getYear());
	
		if (type== 0) {
			UserCatch data = userCatchDao.findByUserId(userId, user.getYear());
			if (data != null) {
				CatchConfig config = configCacheUtil.getCatchConfig(data.getCatchId());
				if (config != null) {
					result.setText(config.getName());
				}
			}
			
		}
		else {
			UserTotalIncome income = userTotalIncomeDao.findByUserId(userId);
			if (income != null) {
				result.setText(income.getIncome());
			}
		}
		return result;
	}
	
}
