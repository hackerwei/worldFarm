package com.hz.world.api.core.controller;

import java.util.HashMap;
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
import com.hz.world.api.core.domain.request.CatchRequest;
import com.hz.world.common.dto.ResultDTO;
import com.hz.world.core.service.UserCatchService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/catch")
@Slf4j
public class CatchController {

	protected static final String X_SECURITY = "X-Security";
	protected static final String X_UUID = "X-UUID";

	@Autowired
	private UserCatchService userCatchService;
	@Autowired
	private UserBaseInfoService userBaseInfoService;
	

	

	@RequestMapping(value = "/draw", method = { RequestMethod.POST })
	public GeneralResultMap draw(@RequestHeader("uid") Long userId,@RequestBody CatchRequest request) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {
			if (request== null || request.getYear() == null) {
				outputMap.setResult(SysReturnCode.LACK_PARAMS, "参数不存在");
			}
			UserBaseInfoDTO user = userBaseInfoService.getByUserId(userId);
			if (user == null ) {
				outputMap.setResult(SysReturnCode.UNKNOW_USER, "用户不存在");
				return outputMap;
			}
			//校验年份
			ResultDTO<Integer> resultDTO = userCatchService.doCatch(userId, request.getYear());
			if (resultDTO.isSuccess()) {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("catchId", resultDTO.getResult());
				outputMap.setResult(SysReturnCode.SUCC, data);
			}else {
				outputMap.setResult(SysReturnCode.FAIL, resultDTO.getErrDesc());
			}
			
			
		} catch (Exception e) {
			log.error("用户{}捕捞失败", userId, e);
			outputMap.setResult(SysReturnCode.FAIL, "用户捕捞失败");
		}

		return outputMap;
		
	}

	
	
	
	
}
