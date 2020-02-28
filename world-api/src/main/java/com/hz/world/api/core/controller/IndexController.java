package com.hz.world.api.core.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hz.world.account.domain.dto.UserBaseInfoDTO;
import com.hz.world.account.service.UserBaseInfoService;
import com.hz.world.api.account.request.UserInfoRequest;
import com.hz.world.api.common.cache.ApiCacheUtil;
import com.hz.world.api.core.domain.dto.GeneralResultMap;
import com.hz.world.api.core.domain.dto.OfflineRewardDTO;
import com.hz.world.api.core.domain.dto.SysReturnCode;
import com.hz.world.api.core.domain.dto.UserCoinOutDTO;
import com.hz.world.api.core.domain.request.IndexRequest;
import com.hz.world.common.constant.CoinConstants;
import com.hz.world.common.dto.ResultCodeEnum;
import com.hz.world.common.dto.ResultDTO;
import com.hz.world.common.enums.CoinChangeType;
import com.hz.world.common.enums.ElementAdd;
import com.hz.world.core.domain.dto.UserCoinDTO;
import com.hz.world.core.domain.dto.UserElementDTO;
import com.hz.world.core.domain.dto.UserTmpIncomeDTO;
import com.hz.world.core.service.FortuneService;
import com.hz.world.core.service.UserCoinService;
import com.hz.world.core.service.UserElementService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/home")
@Slf4j
public class IndexController {

	protected static final String X_SECURITY = "X-Security";
	protected static final String X_UUID = "X-UUID";

	@Autowired
	private UserBaseInfoService userBaseInfoService;
	
	@Autowired
	private UserCoinService userCoinService;
	@Autowired
	private UserElementService userElementService;
	@Autowired
	private FortuneService fortuneService;
	@Autowired
	private ApiCacheUtil apiCacheUtil;

	@RequestMapping(value = "/index", method = { RequestMethod.POST })
	public GeneralResultMap index(@RequestHeader("uid") Long userId,@RequestBody UserInfoRequest request) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {
			
			UserBaseInfoDTO user = userBaseInfoService.getByUserId(userId);
			if (user == null) {
				outputMap.setResult(SysReturnCode.UNKNOW_USER, "用户不存在");
				return outputMap;
			}
			List<UserElementDTO> elementList = userElementService.getUserElementList(userId);
			userCoinService.updateUserCoin(userId);
			UserCoinDTO coin = userCoinService.getUserCoin(userId);
			UserCoinOutDTO out = new UserCoinOutDTO();
			out.setUpdateTime(coin.getUpdateTime());
			out.setCoin(coin.getCoin());
			out.setOutput(coin.getIcomeRate());
			
			
			Map<String, Object> data = new HashMap<String, Object>();
			
			data.put("user", user);
			data.put("coin", out);
			data.put("elementList", elementList);
			data.put("fortune", fortuneService.canDraw(userId));
			data.put("shopAdd", userElementService.getUserTotalAddByField(userId, ElementAdd.SHOP.getCode()));
			
			outputMap.setResult(SysReturnCode.SUCC, data);
		} catch (Exception e) {
			log.error("用户{}查询主页失败", userId, e);
			outputMap.setResult(SysReturnCode.FAIL, "查询主页失败");
		}

		return outputMap;
		
	}

	/**
	 * 收取三小时双倍收益
	 * @param userId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/recvIncome", method = { RequestMethod.POST })
	public GeneralResultMap recvIncome(@RequestHeader("uid") Long userId,@RequestBody IndexRequest request) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {
			UserTmpIncomeDTO income = userCoinService.getTmpIncome(userId);
			long countDown =   income.getStartTime() + 3600*3 - new Date().getTime()/1000;
			if (countDown > 0) {
				outputMap.setResult(SysReturnCode.FAIL, "还未到时间");
				return outputMap;
			}
			ResultDTO<String> resultDTO = userCoinService.recvImpIcome(userId);
			if (!resultDTO.isSuccess()) {
				outputMap.setResult(SysReturnCode.FAIL, resultDTO.getErrDesc());
				return outputMap;
			}
			UserCoinDTO coin = userCoinService.getUserCoin(userId);
			UserCoinOutDTO out = new UserCoinOutDTO();
			out.setUpdateTime(coin.getUpdateTime());
			out.setCoin(coin.getCoin());
			out.setOutput(coin.getIcomeRate());	
		
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("coin", out);
			data.put("income", resultDTO.getResult());
			data.put("countDown", CoinConstants.COUNT_DOWN);
			outputMap.setResult(SysReturnCode.SUCC, data);
		} catch (Exception e) {
			log.error("用户{}领取三小时奖励失败", userId, e);
			outputMap.setResult(SysReturnCode.FAIL, "领取三小时奖励失败");
		}

		return outputMap;
		
	}

	/**
	 * 同步资源
	 * @param userId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/synResource", method = { RequestMethod.POST })
	public GeneralResultMap synResource(@RequestHeader("uid") Long userId,@RequestBody IndexRequest request) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {
			UserBaseInfoDTO user = userBaseInfoService.getByUserId(userId);
			if (user == null) {
				outputMap.setResult(SysReturnCode.UNKNOW_USER, "用户不存在");
				return outputMap;
			}
			Map<String, Object> data = new HashMap<String, Object>();
			userCoinService.updateUserCoin(userId);
			UserCoinDTO coin = userCoinService.getUserCoin(userId);
			UserTmpIncomeDTO income = userCoinService.getTmpIncome(userId);
			long now = new Date().getTime()/1000;
			long countDown =   income.getStartTime() + CoinConstants.COUNT_DOWN - now;
			if (countDown < 0) {
				countDown = 0;
			}
			String lastOnlimeTime = apiCacheUtil.getUserOnline(userId);
			if (StringUtils.isNoneBlank(lastOnlimeTime) ) {
				//间隔超过1小时，显示离线收益
				long diff = now - Long.parseLong(lastOnlimeTime)/1000;
				if (diff >= 1) {
					OfflineRewardDTO reward = new OfflineRewardDTO();
					reward.setOfflineTime(diff);
					if (diff >= 99*3600) {
						diff = 99*3600;
					}
					BigDecimal b1 = new BigDecimal(coin.getIcomeRate()) ;
					BigDecimal b2 = new BigDecimal(diff) ;
					reward.setIncome(b1.multiply(b2).toString());
					data.put("offlineReward", reward);
				}
			}
			
			data.put("countDown", countDown);
			data.put("coin", coin.getCoin());
			data.put("output", coin.getIcomeRate());
			data.put("level", user.getLevel());
			data.put("year", user.getYear());
			data.put("diamond", user.getDiamond());
			apiCacheUtil.setUserOnline(userId, new Date().getTime());
			outputMap.setResult(SysReturnCode.SUCC, data);
		} catch (Exception e) {
			log.error("用户{}同步资源失败", userId, e);
			outputMap.setResult(SysReturnCode.FAIL, "同步资源失败");
		}

		return outputMap;
		
	}
	/**
	 * 收取离线收益
	 * @param userId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/recvOfflineReward", method = { RequestMethod.POST })
	public GeneralResultMap recvOfflineReward(@RequestHeader("uid") Long userId,@RequestBody IndexRequest request) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {
			if (request == null || request.getReward() == null) {
				outputMap.setResult(SysReturnCode.LACK_PARAMS, "缺少参数");
				return outputMap;
			}
			String reward = apiCacheUtil.getUserOfflineReward(userId);
			if (!request.getReward().equals(reward)) {
				outputMap.setResult(SysReturnCode.FAIL, "收益不符");
				return outputMap;
			}
			ResultDTO<String> resultDTO = userCoinService.changeUserCoin( userId, reward, CoinChangeType.OFFLINE_REWARD.getCode(), 0);
			if (resultDTO.isSuccess()) {
				apiCacheUtil.clearOfflineReward(userId);
				outputMap.setResult(SysReturnCode.SUCC, "ok");
				
			}else {
				outputMap.setResult(SysReturnCode.FAIL, "领取三小时奖励失败");
	
			}
			
		} catch (Exception e) {
			log.error("用户{}领取三小时奖励失败", userId, e);
			outputMap.setResult(SysReturnCode.FAIL, "领取三小时奖励失败");
		}

		return outputMap;
		
	}

	
}
