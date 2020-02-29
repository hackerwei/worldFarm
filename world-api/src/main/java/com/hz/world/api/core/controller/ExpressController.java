package com.hz.world.api.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hz.world.account.domain.dto.UserBaseInfoDTO;
import com.hz.world.account.service.UserBaseInfoService;
import com.hz.world.api.core.domain.dto.GeneralResultMap;
import com.hz.world.api.core.domain.dto.SysReturnCode;
import com.hz.world.common.dto.ResultDTO;
import com.hz.world.core.domain.dto.ExpressInfoDTO;
import com.hz.world.core.domain.dto.ExpressResultDTO;
import com.hz.world.core.service.ExpressService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/express")
@Slf4j
public class ExpressController {

	protected static final String X_SECURITY = "X-Security";
	protected static final String X_UUID = "X-UUID";

	@Autowired
	private ExpressService expressService;
	@Autowired
	private UserBaseInfoService userBaseInfoService;

	

	

	@RequestMapping(value = "/getReward", method = { RequestMethod.POST })
	public GeneralResultMap buy(@RequestHeader("uid") Long userId) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {
			
			UserBaseInfoDTO user = userBaseInfoService.getByUserId(userId);
			if (user == null) {
				outputMap.setResult(SysReturnCode.UNKNOW_USER, "用户不存在");
				return outputMap;
			}
			ResultDTO<ExpressResultDTO> resultDTO = expressService.getReward(userId);
			if (resultDTO.isSuccess()) {
				outputMap.setResult(SysReturnCode.SUCC, "ok",resultDTO.getResult());
			}else {
				outputMap.setResult(SysReturnCode.FAIL, resultDTO.getErrDesc());
			}
			
			
		} catch (Exception e) {
			log.error("用户{}领取快递失败", userId, e);
			outputMap.setResult(SysReturnCode.FAIL, "领取快递失败");
		}

		return outputMap;
		
	}
	@RequestMapping(value = "/clearCountDown", method = { RequestMethod.POST })
	public GeneralResultMap clearCountDown(@RequestHeader("uid") Long userId) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {
			
			UserBaseInfoDTO user = userBaseInfoService.getByUserId(userId);
			if (user == null) {
				outputMap.setResult(SysReturnCode.UNKNOW_USER, "用户不存在");
				return outputMap;
			}
			ResultDTO<String> resultDTO = expressService.clearCountDown(userId);
			if (resultDTO.isSuccess()) {
				outputMap.setResult(SysReturnCode.SUCC, "ok");
			}else {
				outputMap.setResult(SysReturnCode.FAIL, resultDTO.getErrDesc());
			}
			
			
		} catch (Exception e) {
			log.error("用户{}清除倒计时失败", userId, e);
			outputMap.setResult(SysReturnCode.FAIL, "清除倒计时失败");
		}

		return outputMap;
		
	}
	
	@RequestMapping(value = "/index", method = { RequestMethod.POST })
	public GeneralResultMap index(@RequestHeader("uid") Long userId) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {
			
			ExpressInfoDTO info = expressService.getCountDown(userId);
			
		
			outputMap.setResult(SysReturnCode.SUCC, "ok",info);
			
			
			
		} catch (Exception e) {
			log.error("用户{}快递倒计时失败", userId, e);
			outputMap.setResult(SysReturnCode.FAIL, "快递倒计时失败");
		}

		return outputMap;
		
	}
	
}
