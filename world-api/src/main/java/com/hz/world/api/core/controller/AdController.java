package com.hz.world.api.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hz.world.api.core.domain.dto.GeneralResultMap;
import com.hz.world.api.core.domain.dto.SysReturnCode;
import com.hz.world.api.core.domain.request.CatchRequest;
import com.hz.world.core.service.AdService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/ad")
@Slf4j
public class AdController {

	protected static final String X_SECURITY = "X-Security";
	protected static final String X_UUID = "X-UUID";

	@Autowired
	private AdService adService;

	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public GeneralResultMap add(@RequestHeader("uid") Long userId, @RequestBody CatchRequest request) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {
			adService.addUserAd(userId);
			outputMap.setResult(SysReturnCode.SUCC, "ok");

		} catch (Exception e) {
			log.error("用户{}看广告失败", userId, e);
			outputMap.setResult(SysReturnCode.FAIL, "用户看广告失败");
		}

		return outputMap;

	}

}
