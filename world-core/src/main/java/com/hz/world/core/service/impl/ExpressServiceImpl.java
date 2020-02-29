package com.hz.world.core.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hz.world.account.domain.dto.UserBaseInfoDTO;
import com.hz.world.account.service.UserBaseInfoService;
import com.hz.world.common.dto.ResultCodeEnum;
import com.hz.world.common.dto.ResultDTO;
import com.hz.world.common.enums.CoinChangeType;
import com.hz.world.common.enums.DiamondChangeType;
import com.hz.world.core.common.util.CoreCacheUtil;
import com.hz.world.core.domain.dto.ExpressInfoDTO;
import com.hz.world.core.domain.dto.ExpressResultDTO;
import com.hz.world.core.domain.dto.UserCoinDTO;
import com.hz.world.core.service.ExpressService;
import com.hz.world.core.service.UserCoinService;
import com.hz.world.core.service.UserDiamondService;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: Description: author linyanchun date Feb 22, 2020
 */
@Slf4j
@Service
@Transactional
public class ExpressServiceImpl implements ExpressService {

	@Autowired
	private CoreCacheUtil coreCacheUtil;
	@Autowired
	private UserCoinService userCoinService;
	@Autowired
	private UserBaseInfoService userBaseInfoService;
	@Autowired
	private UserDiamondService userDiamondService;

	@Override
	public ResultDTO<ExpressResultDTO> getReward(Long userId) {
		ResultDTO<ExpressResultDTO> resultDTO = new ResultDTO<ExpressResultDTO>();
		ExpressResultDTO result = new ExpressResultDTO();
		long countDown = coreCacheUtil.getExpressTime(userId);
		if (countDown > 0) {
			resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "时间未到，无法领取");
			return resultDTO;
		}
		UserCoinDTO coin = userCoinService.getUserCoin(userId);
		if (coin != null) {
			// 45分钟收益
			BigDecimal rate = new BigDecimal(coin.getIcomeRate());
			String income = rate.multiply(BigDecimal.valueOf(45 * 60)).toString();
			userCoinService.changeUserCoin(userId, income, CoinChangeType.SHOP_REWARD.getCode(), 0);
			UserCoinDTO coinDTO = userCoinService.getUserCoin(userId);
			result.setCoinReward(income);
			result.setCoin(coinDTO.getCoin());
		}

		coreCacheUtil.addExpress(userId);
		result.setCountDown(coreCacheUtil.getExpressTime(userId));
		resultDTO.set(ResultCodeEnum.SUCCESS, "ok", result);
		return resultDTO;
	}

	@Override
	public ExpressInfoDTO getCountDown(Long userId) {
		ExpressInfoDTO result = new ExpressInfoDTO();
		long countDown = coreCacheUtil.getExpressTime(userId);
		int diamondCost = 0;
		if (countDown > 0) {
			diamondCost = (int) Math.ceil(countDown * 60 / 10800);
		}
		result.setCountDown(countDown);
		result.setDiamondCost(diamondCost);
		return result;
	}

	@Override
	public ResultDTO<String> clearCountDown(Long userId) {
		ResultDTO<String> resultDTO = new ResultDTO<String>();
		long countDown = coreCacheUtil.getExpressTime(userId);
		int costDiamond = 0;
		if (countDown > 0) {
			costDiamond = (int) Math.ceil(countDown * 60 / 10800);
		}
		if (costDiamond > 0) {
			// 扣除钻石
			if (!userBaseInfoService.updateUserDiamond(userId, -costDiamond)) {
				resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "钻石不足");
				return resultDTO;
			}
			UserBaseInfoDTO user = userBaseInfoService.getByUserId(userId);
			userDiamondService.createDiamondChangeLog(userId, -costDiamond, user.getDiamond(),
					DiamondChangeType.EXPRESS.getCode());
		}
		coreCacheUtil.clearExpress(userId);
		resultDTO.set(ResultCodeEnum.SUCCESS, "ok");
		return resultDTO;
	}
}
