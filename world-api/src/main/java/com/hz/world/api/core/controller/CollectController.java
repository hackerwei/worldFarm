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
import com.hz.world.core.service.CollectService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/collect")
@Slf4j
public class CollectController {

	protected static final String X_SECURITY = "X-Security";
	protected static final String X_UUID = "X-UUID";

	@Autowired
	private CollectService collectService;
	@Autowired
	private UserBaseInfoService userBaseInfoService;

	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	public GeneralResultMap list(@RequestHeader("uid") Long userId, @RequestBody CatchRequest request) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {

			UserBaseInfoDTO user = userBaseInfoService.getByUserId(userId);
			if (user == null) {
				outputMap.setResult(SysReturnCode.UNKNOW_USER, "用户不存在");
				return outputMap;
			}

			outputMap.setResult(SysReturnCode.SUCC, collectService.userCollectList(userId));

		} catch (Exception e) {
			log.error("用户{}获取收集列表失败", userId, e);
			outputMap.setResult(SysReturnCode.FAIL, "用户获取收集列表失败");
		}

		return outputMap;

	}

}
