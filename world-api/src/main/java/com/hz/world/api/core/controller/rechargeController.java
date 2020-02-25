package com.hz.world.api.core.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hz.world.account.domain.dto.UserBaseInfoDTO;
import com.hz.world.account.service.UserBaseInfoService;
import com.hz.world.api.core.domain.dto.GeneralResultMap;
import com.hz.world.api.core.domain.dto.SysReturnCode;
import com.hz.world.api.core.domain.request.RankingRequest;
import com.hz.world.api.core.domain.request.RechargeRequest;
import com.hz.world.common.dto.ResultDTO;
import com.hz.world.core.dao.model.RechargeConfig;
import com.hz.world.core.service.RechargeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/recharge")
@Slf4j
public class rechargeController {

	protected static final String X_SECURITY = "X-Security";
	protected static final String X_UUID = "X-UUID";

	@Autowired
	private RechargeService rechargeService;
	@Autowired
	private UserBaseInfoService userBaseInfoService;

	

	@RequestMapping(value = "/config", method = { RequestMethod.POST })
	public GeneralResultMap list(@RequestHeader("uid") Long userId,@RequestBody RankingRequest request) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {
			
			List<RechargeConfig> list = rechargeService.getConfigList();
			
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("configList", list);
			
			outputMap.setResult(SysReturnCode.SUCC, data);
			
		} catch (Exception e) {
			log.error("用户{}获取充值配置失败", userId,request.getType(), e);
			outputMap.setResult(SysReturnCode.FAIL, "获取充值配置失败");
		}

		return outputMap;
		
	}

	@RequestMapping(value = "/doRecharge", method = { RequestMethod.POST })
	public GeneralResultMap doRecharge(@RequestHeader("uid") Long userId,@RequestBody RechargeRequest request) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {
			if (request == null || request.getRechargeId() == null ) {
				outputMap.setResult(SysReturnCode.LACK_PARAMS, "参数错误");
			}
			UserBaseInfoDTO user = userBaseInfoService.getByUserId(userId);
			if (user == null) {
				outputMap.setResult(SysReturnCode.UNKNOW_USER, "用户不存在");
				return outputMap;
			}
			Map<String, Object> data = new HashMap<String, Object>();
			ResultDTO<String> result = rechargeService.recharge(userId, request.getRechargeId());
			if (result.isSuccess()) {
				data.put("diamond", user.getDiamond());
				data.put("cash", user.getCash());
				outputMap.setResult(SysReturnCode.SUCC, "ok",data);
			}else {
				outputMap.setResult(SysReturnCode.FAIL, result.getErrDesc());
			}
			

			
		} catch (Exception e) {
			log.error("用户{}充值失败{}", userId,request.getRechargeId(), e);
			outputMap.setResult(SysReturnCode.FAIL, "用户充值失败");
		}

		return outputMap;
		
	}
	
	
	
}
