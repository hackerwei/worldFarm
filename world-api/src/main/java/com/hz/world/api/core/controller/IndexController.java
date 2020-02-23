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

import com.alibaba.druid.sql.visitor.functions.If;
import com.hz.world.account.domain.dto.UserBaseInfoDTO;
import com.hz.world.account.service.UserBaseInfoService;
import com.hz.world.api.account.request.UserInfoRequest;
import com.hz.world.api.core.domain.dto.GeneralResultMap;
import com.hz.world.api.core.domain.dto.SysReturnCode;
import com.hz.world.api.core.domain.dto.UserCoinOutDTO;
import com.hz.world.core.domain.dto.UserCoinDTO;
import com.hz.world.core.domain.dto.UserElementDTO;
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
			outputMap.setResult(SysReturnCode.SUCC, data);
		} catch (Exception e) {
			log.error("用户{}查询主页失败", userId, e);
			outputMap.setResult(SysReturnCode.FAIL, "查询主页失败");
		}

		return outputMap;
		
	}

	
	
	
	
}
