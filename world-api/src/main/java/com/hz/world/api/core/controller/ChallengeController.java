package com.hz.world.api.core.controller;

import com.hz.world.account.domain.dto.UserBaseInfoDTO;
import com.hz.world.account.service.UserBaseInfoService;
import com.hz.world.api.core.domain.dto.GeneralResultMap;
import com.hz.world.api.core.domain.dto.SysReturnCode;
import com.hz.world.api.core.domain.request.CatchRequest;
import com.hz.world.core.common.util.ConfigCacheUtil;
import com.hz.world.core.dao.model.ElementConfig;
import com.hz.world.core.domain.dto.ChallengeDTO;
import com.hz.world.core.service.ChallengeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	@Autowired
	private ConfigCacheUtil configCacheUtil;

//	private ConfigCacheUtil configCacheUtil;

	// 展示挑战初始页面, 默认element=0
	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	public GeneralResultMap list(@RequestHeader("uid") Long userId, @RequestBody CatchRequest request) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {

			UserBaseInfoDTO user = userBaseInfoService.getByUserId(userId);
			if (user == null) {
				outputMap.setResult(SysReturnCode.UNKNOW_USER, "用户不存在");
				return outputMap;
			}
			Map<String, Object> data = new HashMap<String, Object>();
			// 遍历所有的element
			List<ElementConfig> configList =  configCacheUtil.getElementList();
			if (configList != null && configList.size() > 0) {
				for (ElementConfig elementConfig : configList) {
					List<ChallengeDTO> elementChallenge= challengeService.userChallengeElementList(userId,elementConfig.getId());
					//存储element挑战列表
					Map<String, Object> elementData = new HashMap<String, Object>();
					elementData.put(elementConfig.getName(),elementChallenge);
					// 存储下一个挑战的重量，等待延春！！！！
					elementData.put("nextWeight", 0);
					//保存("challengeList","nextWeight")
					data.put(elementConfig.getName(),elementData);
				}
			}
			// 记录底部已达成的挑战数据
			data.put("AchivedChallenge",challengeService.userAchivedChallenges(userId));
			data.put("TotalChallenge",challengeService.userTotalChallenges());
			outputMap.setResult(SysReturnCode.SUCC, data);

		} catch (Exception e) {
			log.error("用户{}获取挑战列表{}失败", userId, request.getElement(), e);
			outputMap.setResult(SysReturnCode.FAIL, "用户获取挑战列表失败");
		}
		return outputMap;
	}
//	// 已达成的挑战
//	@RequestMapping(value = "/achived", method = { RequestMethod.POST })
//	public GeneralResultMap achivedChallenge(@RequestHeader("uid") Long userId, @RequestBody CatchRequest request) {
//		GeneralResultMap outputMap = new GeneralResultMap();
//		try {
//
//			UserBaseInfoDTO user = userBaseInfoService.getByUserId(userId);
//			if (user == null) {
//				outputMap.setResult(SysReturnCode.UNKNOW_USER, "用户不存在");
//				return outputMap;
//			}
//
//			outputMap.setResult(SysReturnCode.SUCC, challengeService.userAchivedChallenge(userId));
//
//		} catch (Exception e) {
//			log.error("用户{}获取挑战列表{}失败", userId, e);
//			outputMap.setResult(SysReturnCode.FAIL, "用户获取挑战列表失败");
//		}
//		return outputMap;
//	}

}
