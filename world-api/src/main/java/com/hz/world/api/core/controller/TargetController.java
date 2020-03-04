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

import com.hz.world.account.domain.dto.UserBaseInfoDTO;
import com.hz.world.account.service.UserBaseInfoService;
import com.hz.world.api.common.domain.PageRequest;
import com.hz.world.api.core.domain.dto.GeneralResultMap;
import com.hz.world.api.core.domain.dto.SysReturnCode;
import com.hz.world.api.core.domain.request.CatchRequest;
import com.hz.world.core.common.util.ConfigCacheUtil;
import com.hz.world.core.dao.model.YearConfig;
import com.hz.world.core.domain.dto.ForeverShareElementDTO;
import com.hz.world.core.domain.dto.OtherElementDTO;
import com.hz.world.core.domain.dto.UserElementDTO;
import com.hz.world.core.domain.dto.limitShareElementDTO;
import com.hz.world.core.service.TargetService;
import com.hz.world.core.service.UserCatchService;
import com.hz.world.core.service.UserElementService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/target")
@Slf4j
public class TargetController {

	protected static final String X_SECURITY = "X-Security";
	protected static final String X_UUID = "X-UUID";

	@Autowired
	private UserCatchService userCatchService;
	@Autowired
	private ConfigCacheUtil configCacheUtil;
	@Autowired
	private UserBaseInfoService userBaseInfoService;
	@Autowired
	private UserElementService userElementService;
	@Autowired
	private TargetService targetService;

	@RequestMapping(value = "/index", method = { RequestMethod.POST })
	public GeneralResultMap index(@RequestHeader("uid") Long userId, @RequestBody CatchRequest request) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {
			if (request == null || request.getYear() == null) {
				outputMap.setResult(SysReturnCode.LACK_PARAMS, "参数不存在");
			}
			UserBaseInfoDTO user = userBaseInfoService.getByUserId(userId);
			if (user == null) {
				outputMap.setResult(SysReturnCode.UNKNOW_USER, "用户不存在");
				return outputMap;
			}
			Integer currentWeight = 0;
			UserElementDTO element = userElementService.getUserElement(userId, 1);
			if (element != null) {
				currentWeight = element.getLevel();
			}
			YearConfig year = configCacheUtil.getYearConfig(user.getYear() + 1);

			Integer targetWeight = year.getWeight();
			Double todayTotalShare = targetService.getTodayTotalShare();
			// 限时分红小龙虾数量
			Integer limitElementCount = targetService.getUserLimitShareCount(userId);
			;
			// 永久分红小龙虾数量
			Integer foreverElementCount = targetService.getForeverElementCount(userId);
			// 5洲小龙虾数量
			Integer otherElementCount = targetService.getOtherElementCount(userId);
			// 限时分红小龙虾
			List<limitShareElementDTO> limitElementList = targetService.getUnfinishedList(userId);

			List<OtherElementDTO> otherElementList = targetService.getOtherElementList(userId);

			Map<String, Object> data = new HashMap<String, Object>();
			data.put("currentWeight", currentWeight);
			data.put("targetWeight", targetWeight);
			data.put("todayTotalShare", todayTotalShare);
			data.put("limitElementCount", limitElementCount);
			data.put("foreverElementCount", foreverElementCount);
			data.put("otherElementCount", otherElementCount);
			data.put("limitElementList", limitElementList);
			data.put("otherElementList", otherElementList);
			outputMap.setResult(SysReturnCode.SUCC, data);

		} catch (Exception e) {
			log.error("用户{}目标主页", userId, e);
			outputMap.setResult(SysReturnCode.FAIL, "目标主页");
		}

		return outputMap;

	}

	/**
	 * 限时分红小龙虾列表
	 * 
	 * @param userId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/limitList", method = { RequestMethod.POST })
	public GeneralResultMap limitList(@RequestHeader("uid") Long userId, @RequestBody PageRequest request) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {

			Integer offset = (request.getPageNo() - 1) * request.getPageSize();
			Integer limit = request.getPageSize();
			// 限时分红小龙虾
			List<limitShareElementDTO> limitElementList = targetService.getLimitList(userId, offset, limit);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("limitElementList", limitElementList);
			outputMap.setResult(SysReturnCode.SUCC, data);

		} catch (Exception e) {
			log.error("用户{}限时分红页面失败", userId, e);
			outputMap.setResult(SysReturnCode.FAIL, "用户限时分红页面失败");
		}

		return outputMap;

	}

	/**
	 * 永久分红小龙虾用户列表
	 * 
	 * @param userId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/foreverUserList", method = { RequestMethod.POST })
	public GeneralResultMap foreverUserList(@RequestHeader("uid") Long userId, @RequestBody PageRequest request) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {
			List<ForeverShareElementDTO> foreverList = targetService.getForeverElementList();

			outputMap.setResult(SysReturnCode.SUCC, foreverList);

		} catch (Exception e) {
			log.error("用户{}永久分红页面失败", userId, e);
			outputMap.setResult(SysReturnCode.FAIL, "永久分红页面失败");
		}

		return outputMap;

	}
}
