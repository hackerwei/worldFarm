package com.hz.world.api.account.controller;

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
import com.hz.world.api.account.dto.UserOutDTO;
import com.hz.world.api.account.request.UserInfoRequest;
import com.hz.world.api.core.domain.dto.GeneralResultMap;
import com.hz.world.api.core.domain.dto.SysReturnCode;
import com.hz.world.common.util.BeanUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

	protected static final String X_SECURITY = "X-Security";
	protected static final String X_UUID = "X-UUID";

	@Autowired
	private UserBaseInfoService userBaseInfoService;
	

	@RequestMapping(value = "/info", method = { RequestMethod.POST })
	public GeneralResultMap userInfo(@RequestHeader("uid") Long userId,@RequestBody UserInfoRequest request) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {
			if (request == null || request.getToId() == null) {
				outputMap.setResult(SysReturnCode.UNKNOW_USER, "用户不存在");
				return outputMap;
			}
			UserBaseInfoDTO user = userBaseInfoService.getByUserId(request.getToId());
			if (user == null) {
				outputMap.setResult(SysReturnCode.UNKNOW_USER, "用户不存在");
				return outputMap;
			}
			UserOutDTO dto = BeanUtil.copyProperties(user, UserOutDTO.class);
			dto.setUserId(user.getUserId()+"");
			
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("user", dto);
			outputMap.setResult(SysReturnCode.SUCC, data);
		} catch (Exception e) {
			log.error("用户{}查询个人信息失败", userId, e);
			outputMap.setResult(SysReturnCode.FAIL, "查询个人信息失败");
		}

		return outputMap;
		
	}

	
	
	
	
}
