package com.hz.world.api.core.controller;

import com.hz.world.account.domain.dto.UserBaseInfoDTO;
import com.hz.world.account.service.UserBaseInfoService;
import com.hz.world.api.core.domain.dto.GeneralResultMap;
import com.hz.world.api.core.domain.dto.SysReturnCode;
import com.hz.world.api.core.domain.request.CatchRequest;
import com.hz.world.core.service.ChallengeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/challenge")
@Slf4j
public class ChallengeController {

	protected static final String X_SECURITY = "X-Security";
	protected static final String X_UUID = "X-UUID";

	@Autowired
	private ChallengeService challengeService;
	@Autowired
	private UserBaseInfoService userBaseInfoService;

	// 展示挑战初始页面, 默认element=0
	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	public GeneralResultMap list(@RequestHeader("uid") Long userId, @RequestHeader("element") Integer element, @RequestBody CatchRequest request) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {

			UserBaseInfoDTO user = userBaseInfoService.getByUserId(userId);
			if (user == null) {
				outputMap.setResult(SysReturnCode.UNKNOW_USER, "用户不存在");
				return outputMap;
			}

			outputMap.setResult(SysReturnCode.SUCC, challengeService.userChallengeElementList(userId,element));

		} catch (Exception e) {
			log.error("用户{}获取挑战列表{}失败", userId, element, e);
			outputMap.setResult(SysReturnCode.FAIL, "用户获取挑战列表失败");
		}
		return outputMap;
	}
	// 已达成的挑战
	@RequestMapping(value = "/achived", method = { RequestMethod.POST })
	public GeneralResultMap list(@RequestHeader("uid") Long userId, @RequestBody CatchRequest request) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {

			UserBaseInfoDTO user = userBaseInfoService.getByUserId(userId);
			if (user == null) {
				outputMap.setResult(SysReturnCode.UNKNOW_USER, "用户不存在");
				return outputMap;
			}

			outputMap.setResult(SysReturnCode.SUCC, challengeService.userAchivedChallenge(userId));

		} catch (Exception e) {
			log.error("用户{}获取挑战列表{}失败", userId, e);
			outputMap.setResult(SysReturnCode.FAIL, "用户获取挑战列表失败");
		}
		return outputMap;
	}

}
