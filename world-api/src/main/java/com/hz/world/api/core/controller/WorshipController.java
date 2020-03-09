package com.hz.world.api.core.controller;

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
import com.hz.world.api.core.domain.request.CatchRequest;
import com.hz.world.common.dto.ResultDTO;
import com.hz.world.core.domain.dto.OfferResultDTO;
import com.hz.world.core.service.CollectService;
import com.hz.world.core.service.WorshipService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/worship")
@Slf4j
public class WorshipController {

	protected static final String X_SECURITY = "X-Security";
	protected static final String X_UUID = "X-UUID";

	@Autowired
	private WorshipService worshipService;
	@Autowired
	private UserBaseInfoService userBaseInfoService;
	@RequestMapping(value = "/index", method = { RequestMethod.POST })
	public GeneralResultMap index(@RequestHeader("uid") Long userId, @RequestBody CatchRequest request) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {

			

			outputMap.setResult(SysReturnCode.SUCC, worshipService.getWorship(userId));

		} catch (Exception e) {
			log.error("用户{}祭神页面失败", userId, e);
			outputMap.setResult(SysReturnCode.FAIL, "用户祭神页面失败");
		}

		return outputMap;

	}
	@RequestMapping(value = "/offer", method = { RequestMethod.POST })
	public GeneralResultMap offer(@RequestHeader("uid") Long userId, @RequestBody CatchRequest request) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {

			ResultDTO<OfferResultDTO> resultDTO = worshipService.offer(userId);
			if (resultDTO.isSuccess()) {
				outputMap.setResult(SysReturnCode.SUCC, resultDTO.getResult());
			}else {
				outputMap.setResult(SysReturnCode.FAIL, resultDTO.getErrDesc());
			}

			

		} catch (Exception e) {
			log.error("用户{}祭神失败", userId, e);
			outputMap.setResult(SysReturnCode.FAIL, "用户祭神失败");
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
			ResultDTO<String> resultDTO = worshipService.clearCountDown(userId);
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

}
