package com.hz.world.core.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hz.world.account.domain.dto.UserBaseInfoDTO;
import com.hz.world.account.service.UserBaseInfoService;
import com.hz.world.common.dto.ResultCodeEnum;
import com.hz.world.common.dto.ResultDTO;
import com.hz.world.common.enums.CoinChangeType;
import com.hz.world.common.enums.DiamondChangeType;
import com.hz.world.common.enums.ElementAdd;
import com.hz.world.core.common.util.ConfigCacheUtil;
import com.hz.world.core.common.util.CoreCacheUtil;
import com.hz.world.core.dao.impl.UserWorshipDaoImpl;
import com.hz.world.core.dao.model.UserWorship;
import com.hz.world.core.dao.model.Worship;
import com.hz.world.core.domain.dto.ExpressInfoDTO;
import com.hz.world.core.domain.dto.OfferResultDTO;
import com.hz.world.core.domain.dto.UserCoinDTO;
import com.hz.world.core.domain.dto.WorshipDTO;
import com.hz.world.core.service.UserCoinService;
import com.hz.world.core.service.UserDiamondService;
import com.hz.world.core.service.UserElementService;
import com.hz.world.core.service.WorshipService;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: Description: author linyanchun date Feb 22, 2020
 */
@Slf4j
@Service
@Transactional
public class WorshipServiceImpl implements WorshipService {

	@Autowired
	private CoreCacheUtil coreCacheUtil;
	@Autowired
	private ConfigCacheUtil configCacheUtil;
	@Autowired
	private UserWorshipDaoImpl userWorshipDao;
	@Autowired
	private UserBaseInfoService userBaseInfoService;
	@Autowired
	private UserDiamondService userDiamondService;
	@Autowired
	private UserElementService userElementService;
	@Autowired
	private UserCoinService userCoinService;

	@Override
	public ExpressInfoDTO getCountDown(Long userId) {
		ExpressInfoDTO result = new ExpressInfoDTO();
		long countDown = coreCacheUtil.getWorshipTime(userId);
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
		long countDown = coreCacheUtil.getWorshipTime(userId);
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
					DiamondChangeType.WORSHIP.getCode());
		}
		coreCacheUtil.clearWorship(userId);
		resultDTO.set(ResultCodeEnum.SUCCESS, "ok");
		return resultDTO;
	}

	@Override
	public WorshipDTO getWorship(Long userId) {
		WorshipDTO worshipDTO = new WorshipDTO();
		BigDecimal currentOffer = new BigDecimal(0);
		// 全局收益倍数
		String totalAdd = coreCacheUtil.getUserTotalAddByField(userId, ElementAdd.OFFER.getCode());
		worshipDTO.setIncomeRate(totalAdd);
		currentOffer = new BigDecimal(totalAdd).multiply(BigDecimal.valueOf(10));
		worshipDTO.setCurrentOffer(currentOffer.toString());
		worshipDTO.setCountDown(getCountDown(userId));
		UserWorship userWorship = getUserWorship(userId);
		Worship config = configCacheUtil.getWorshipConfig(userWorship.getShipId());
		String output = userElementService.getUserOutput(userId);

		BigDecimal rate = new BigDecimal(output).divide(BigDecimal.valueOf(config.getMoney()))
				.divide(BigDecimal.valueOf(50)).add(BigDecimal.valueOf(2));

		String needCoin = new BigDecimal(output).multiply(BigDecimal.valueOf(config.getMoney())).toString();
		worshipDTO.setRate(rate.toString());
		worshipDTO.setFinishTime((new Date().getTime() - userWorship.getUpdateTime().getTime()) / 1000);
		worshipDTO.setNeedCoin(needCoin);
		// 当前金币
		UserCoinDTO coin = userCoinService.getUserCoin(userId);
		worshipDTO.setCurrentCoin(coin.getCoin());
		String getOffer = currentOffer.multiply(rate).toString();
		worshipDTO.setGetOffer(getOffer);
		worshipDTO.setOutput(userElementService.getUserOutput(userId));
		return worshipDTO;
	}

	private UserWorship getUserWorship(Long userId) {
		UserWorship result = userWorshipDao.findByUserId(userId);
		if (result == null) {
			result = new UserWorship();
			Worship config = configCacheUtil.getWorshipConfig(1);
			result.setUserId(userId);
			result.setShipId(1);
			// 当前金币参数*系数
			String output = userElementService.getUserOutput(userId);
			String coin = new BigDecimal(output).multiply(BigDecimal.valueOf(config.getMoney())).toString();
			result.setCoin(coin);
			result.setUpdateTime(new Date());
			userWorshipDao.insert(result);
		}
		return result;
	}

	@Override
	public ResultDTO<OfferResultDTO> offer(Long userId, Integer type) {
		ResultDTO<OfferResultDTO> resultDTO = new ResultDTO<OfferResultDTO>();
		WorshipDTO data = getWorship(userId);
		if (data.getCountDown() != null && data.getCountDown().getCountDown() > 0) {
			resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "未达到条件");
			return resultDTO;
		}
		BigDecimal currentCoin = new BigDecimal(data.getCurrentCoin());
		BigDecimal needCoin = new BigDecimal(data.getNeedCoin());
		if (currentCoin.compareTo(needCoin) < 0) {
			resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "未达到条件");
			return resultDTO;
		}
		// 扣除金币
		ResultDTO<String> resultDTO2 = userCoinService.changeUserCoin(userId, needCoin.toString(),
				CoinChangeType.OFFER.getCode(), 0);
		if (!resultDTO2.isSuccess()) {
			resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "扣除金币失败");
			return resultDTO;
		}
		BigDecimal rate = new BigDecimal(data.getRate());
		if (data.getFinishTime() <= 10) {
			rate = rate.divide(new BigDecimal(10 - data.getFinishTime()));
		} else {
			rate = rate.divide(new BigDecimal(10));
		}
		String totalAdd = coreCacheUtil.getUserTotalAddByField(userId, ElementAdd.OFFER.getCode());
		rate = rate.multiply(new BigDecimal(totalAdd));
		// 增加总体收益倍数
		coreCacheUtil.setUserTotalAdd(userId, ElementAdd.OFFER.getCode(), rate.toString());
		// 更新祭祀信息

		UserWorship record = userWorshipDao.findByUserId(userId);
		int shipId = record.getShipId() + 1;
		record.setShipId(shipId);
		Worship config = configCacheUtil.getWorshipConfig(1);
		// 当前金币参数*系数
		String output = userElementService.getUserOutput(userId);
		String coin = new BigDecimal(output).multiply(BigDecimal.valueOf(config.getMoney())).toString();
		record.setCoin(coin);
		record.setUpdateTime(new Date());
		userWorshipDao.update(record);
		OfferResultDTO result = new OfferResultDTO();
		result.setIncomeRate(rate.toString());
		result.setCurrentOffer(rate.multiply(new BigDecimal(50)).toString());
		coreCacheUtil.addWorship(userId);
		//清空体重
		if (type == 1) {
			userElementService.clearElement(userId);
		}
		resultDTO.set(ResultCodeEnum.SUCCESS, "ok",result);
		return resultDTO;
	}
}
