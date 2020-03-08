package com.hz.world.api.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hz.world.account.domain.dto.UserBaseInfoDTO;
import com.hz.world.account.service.UserBaseInfoService;
import com.hz.world.api.account.dto.UserOutDTO;
import com.hz.world.api.account.dto.UserSocialDTO;
import com.hz.world.api.account.request.UserInfoRequest;
import com.hz.world.api.core.domain.dto.GeneralResultMap;
import com.hz.world.api.core.domain.dto.SysReturnCode;
import com.hz.world.common.util.BeanUtil;
import com.hz.world.core.domain.dto.UserCashChangeLogDTO;
import com.hz.world.core.service.TargetService;
import com.hz.world.core.service.UserCashService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

	protected static final String X_SECURITY = "X-Security";
	protected static final String X_UUID = "X-UUID";

	@Autowired
	private UserBaseInfoService userBaseInfoService;
	@Autowired
	private UserCashService userCashService;
	@Autowired
	private TargetService targetService;

	@RequestMapping(value = "/info", method = { RequestMethod.POST })
	public GeneralResultMap userInfo(@RequestHeader("uid") Long userId,@RequestBody UserInfoRequest request) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {
			
			UserBaseInfoDTO user = userBaseInfoService.getByUserId(userId);
			if (user == null) {
				outputMap.setResult(SysReturnCode.UNKNOW_USER, "用户不存在");
				return outputMap;
			}
			UserOutDTO dto = BeanUtil.copyProperties(user, UserOutDTO.class);
			targetService.limitFinishedCash(userId);
			outputMap.setResult(SysReturnCode.SUCC, dto);
		} catch (Exception e) {
			log.error("用户{}查询个人信息失败", userId, e);
			outputMap.setResult(SysReturnCode.FAIL, "查询个人信息失败");
		}

		return outputMap;
		
	}

	
	@RequestMapping(value = "/socialInfo", method = { RequestMethod.POST })
	public GeneralResultMap socialInfo(@RequestHeader("uid") Long userId,@RequestBody UserInfoRequest request) {
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
			UserSocialDTO dto = BeanUtil.copyProperties(user, UserSocialDTO.class);
			
			outputMap.setResult(SysReturnCode.SUCC, dto);
		} catch (Exception e) {
			log.error("用户{}查询个人信息失败", userId, e);
			outputMap.setResult(SysReturnCode.FAIL, "查询个人信息失败");
		}

		return outputMap;
		
	}

	@RequestMapping(value = "/updateSocialInfo", method = { RequestMethod.POST })
	public GeneralResultMap updateSocialInfo(@RequestHeader("uid") Long userId,@RequestBody UserInfoRequest request) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {
			if (request == null  ) {
				outputMap.setResult(SysReturnCode.UNKNOW_USER, "用户不存在");
				return outputMap;
			}
			
			UserBaseInfoDTO userBaseInfoDTO = userBaseInfoService.getByUserId(userId);
			userBaseInfoDTO.setQq(request.getQq());
			userBaseInfoDTO.setWeixin(request.getWeixin());
			if (userBaseInfoService.update(userBaseInfoDTO)) {
				outputMap.setResult(SysReturnCode.SUCC, "修改成功");
				return outputMap;
			}
				
			outputMap.setResult(SysReturnCode.FAIL, "修改失败");
		} catch (Exception e) {
			log.error("用户{}修改社交信息失败", userId, e);
			outputMap.setResult(SysReturnCode.FAIL, "修改社交信息失败");
		}

		return outputMap;
		
	}

	/**
	 * 收入日志
	 * @param userId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/cashLog", method = { RequestMethod.POST })
	public GeneralResultMap cashLog(@RequestHeader("uid") Long userId,@RequestBody UserInfoRequest request) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {
			
			Integer offset = (request.getPageNo() - 1) * request.getPageSize();
			Integer limit = request.getPageSize();
			List<UserCashChangeLogDTO> list = userCashService.getChangeList(userId, offset, limit);
			outputMap.setResult(SysReturnCode.SUCC, list);
		
		} catch (Exception e) {
			log.error("用户{}收入日志失败", userId, e);
			outputMap.setResult(SysReturnCode.FAIL, "收入日志失败");
		}

		return outputMap;
		
	}
	
	
}
