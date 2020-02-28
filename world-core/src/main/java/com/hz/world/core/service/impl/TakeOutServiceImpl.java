package com.hz.world.core.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hz.world.common.dto.ResultCodeEnum;
import com.hz.world.common.dto.ResultDTO;
import com.hz.world.common.enums.CoinChangeType;
import com.hz.world.core.common.util.CoreCacheUtil;
import com.hz.world.core.domain.dto.TakeOutResultDTO;
import com.hz.world.core.domain.dto.UserCoinDTO;
import com.hz.world.core.service.TakeOutService;
import com.hz.world.core.service.UserCoinService;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 22, 2020 
 */
@Slf4j
@Service
@Transactional
public class TakeOutServiceImpl implements TakeOutService {

	@Autowired
	private CoreCacheUtil coreCacheUtil;
	@Autowired
	private UserCoinService userCoinService;

	@Override
	public ResultDTO<TakeOutResultDTO> getReward(Long userId){
		ResultDTO<TakeOutResultDTO> resultDTO = new ResultDTO<TakeOutResultDTO>();
		TakeOutResultDTO result = new TakeOutResultDTO();
		if (getChance(userId) <= 0) {
			resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "次数不足");
			return resultDTO;
		}
		UserCoinDTO coin = userCoinService.getUserCoin(userId);
		if (coin != null) {
			//45分钟收益
			BigDecimal rate = new BigDecimal(coin.getIcomeRate()) ;
			String income = rate.multiply(BigDecimal.valueOf(45*60)).toString();
			userCoinService.changeUserCoin(userId, income, CoinChangeType.SHOP_REWARD.getCode(), 0);
			UserCoinDTO coinDTO = userCoinService.getUserCoin(userId);
			result.setCoinReward(income);
			result.setCoin(coinDTO.getCoin());
		}
		coreCacheUtil.addTackout(userId);
		result.setChance(getChance(userId));
		resultDTO.set(ResultCodeEnum.SUCCESS, "ok",result);
		return resultDTO;
	}
	
	@Override
	public int getChance(Long userId) {
		return 10 - coreCacheUtil.getTakeOutTimes(userId);
	}
}
