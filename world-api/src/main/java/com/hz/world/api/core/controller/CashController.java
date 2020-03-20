package com.hz.world.api.core.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hz.world.account.domain.dto.UserBaseInfoDTO;
import com.hz.world.account.service.UserBaseInfoService;
import com.hz.world.api.common.config.WithdrawlProperties;
import com.hz.world.api.core.domain.dto.GeneralResultMap;
import com.hz.world.api.core.domain.dto.SysReturnCode;
import com.hz.world.api.core.domain.request.CashRequest;
import com.hz.world.common.enums.CashChangeType;
import com.hz.world.common.util.HttpClientUtils;
import com.hz.world.common.util.MD5Utils;
import com.hz.world.core.service.UserCashService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/cash")
@Slf4j
public class CashController {

	protected static final String X_SECURITY = "X-Security";
	protected static final String X_UUID = "X-UUID";


	@Autowired
	private UserBaseInfoService userBaseInfoService;
	@Autowired
	private UserCashService userCashService;
	@Autowired
	private WithdrawlProperties withdrawlProperties;

	

	@RequestMapping(value = "/withdrawal", method = { RequestMethod.POST })
	public GeneralResultMap withdrawal(@RequestHeader("uid") Long userId,@RequestBody CashRequest request) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {
			if (request== null ) {
				outputMap.setResult(SysReturnCode.LACK_PARAMS, "参数不存在");
			}
			UserBaseInfoDTO user = userBaseInfoService.getByUserId(userId);
			if (user == null ) {
				outputMap.setResult(SysReturnCode.UNKNOW_USER, "用户不存在");
				return outputMap;
			}
			double changeMoney = (double)request.getMoney()/100;
			if (user.getCash() < request.getMoney()) {
				outputMap.setResult(SysReturnCode.FAIL, "金额不足");
				return outputMap;
			}
			if (!(userBaseInfoService.updateUserCash(userId, -changeMoney))) {
				outputMap.setResult(SysReturnCode.FAIL, "金额不足");
				return outputMap;
			}
			userCashService.createCashChangeLog(userId, changeMoney, user.getCash() -changeMoney , 0,CashChangeType.CASH.getCode(),  CashChangeType.CASH.getDesc(), new Date());
			long now = new Date().getTime()/1000;
			Map<String, Object> requestMap = new HashMap<String, Object>();
			requestMap.put("sign_type", "md5");
			requestMap.put("time_stamp", now+"");
			requestMap.put("uid", userId);
			requestMap.put("gameid", request.getGameid());
			requestMap.put("channel", request.getChannel());
			requestMap.put("typ", 6);
			requestMap.put("money", request.getMoney());
			requestMap.put("openid", request.getOpenid());
			String sign = requestMap2Str(requestMap);
			requestMap.put("sign", sign);
			String url = withdrawlProperties.getUrl()+"api/server/tixian";
		
			JSONObject paramJson = new JSONObject(requestMap);
		
			String postStr = paramJson.toString();
			log.info("===sendGroupSysMsg postStr:"+postStr);
			String result = HttpClientUtils.postData(url, postStr);
		
			JSONObject jsonObject = JSON.parseObject(result);
			if (jsonObject.getString("code").equals("0")) {
				Map<String, Object> data = new HashMap<String, Object>();
				
				
				
				data.put("cash", user.getCash()-changeMoney);
				outputMap.setResult(SysReturnCode.SUCC, data);
			}else {
				//提现失败，资金退回
				userBaseInfoService.updateUserCash(userId, changeMoney);
				userCashService.createCashChangeLog(userId, changeMoney ,null, 1,CashChangeType.BACK.getCode(),  CashChangeType.BACK.getDesc(), new Date());
				outputMap.setResult(SysReturnCode.FAIL, "提现失败");
			}
			
			System.out.print(result);
		} catch (Exception e) {
			log.error("用户{}体现失败", userId, e);
			outputMap.setResult(SysReturnCode.FAIL, "用户体现失败");
		}

		return outputMap;
		
	}

	
	 private  String requestMap2Str(Map<String, Object> requestMap) {
	        String[] keys = requestMap.keySet().toArray(new String[0]);
	        Arrays.sort(keys);
	        StringBuilder stringBuilder = new StringBuilder();
	        for (String str : keys) {
	            if (!str.equals("sign")) {
	                stringBuilder.append(str).append("=").append(requestMap.get(str));
	            }
	        }
	        stringBuilder.append(withdrawlProperties.getKey());
	        return  MD5Utils.MD5(stringBuilder.toString());
	    }
	
	
}
