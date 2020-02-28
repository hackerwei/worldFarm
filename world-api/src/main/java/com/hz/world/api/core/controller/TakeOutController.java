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
import com.hz.world.core.domain.dto.TakeOutResultDTO;
import com.hz.world.core.service.TakeOutService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/takeOut")
@Slf4j
public class TakeOutController {

	protected static final String X_SECURITY = "X-Security";
	protected static final String X_UUID = "X-UUID";

	@Autowired
	private TakeOutService takeOutService;
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
			ResultDTO<TakeOutResultDTO> resultDTO = takeOutService.getReward(userId);
			if (resultDTO.isSuccess()) {
				outputMap.setResult(SysReturnCode.SUCC, "ok",resultDTO.getResult());
			}else {
				outputMap.setResult(SysReturnCode.FAIL, resultDTO.getErrDesc());
			}
			
			
		} catch (Exception e) {
			log.error("用户{}领取外卖失败", userId, e);
			outputMap.setResult(SysReturnCode.FAIL, "领取外卖失败");
		}

		return outputMap;
		
	}
	
	
	
}
