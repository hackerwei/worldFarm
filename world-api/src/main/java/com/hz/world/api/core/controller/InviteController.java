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
import com.hz.world.api.core.domain.dto.GeneralResultMap;
import com.hz.world.api.core.domain.dto.SysReturnCode;
import com.hz.world.api.core.domain.request.CatchRequest;
import com.hz.world.api.core.domain.request.InviteRequest;
import com.hz.world.common.dto.ResultDTO;
import com.hz.world.common.enums.AchievementCodeEnum;
import com.hz.world.core.domain.dto.TaskOutDTO;
import com.hz.world.core.domain.dto.UserInviteDTO;
import com.hz.world.core.service.InviteService;
import com.hz.world.core.service.TaskService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/invite")
@Slf4j
public class InviteController {

	protected static final String X_SECURITY = "X-Security";
	protected static final String X_UUID = "X-UUID";

	@Autowired
	private UserBaseInfoService userBaseInfoService;
	@Autowired
	private InviteService inviteService;
	@Autowired
	private TaskService taskService;
	

	@RequestMapping(value = "/index", method = { RequestMethod.POST })
	public GeneralResultMap index(@RequestHeader("uid") Long userId,@RequestBody CatchRequest request) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {
			
			UserBaseInfoDTO user = userBaseInfoService.getByUserId(userId);
			if (user == null ) {
				outputMap.setResult(SysReturnCode.UNKNOW_USER, "用户不存在");
				return outputMap;
			}
			List<UserInviteDTO> userList = inviteService.getUserInviteList(userId, 200);
			TaskOutDTO inviteTask = taskService.getCurrentTaskByCode(userId,AchievementCodeEnum.INVITE_USER.getTaskCode());
			inviteTask.setGetNum(inviteService.getInviteCount(userId));
			TaskOutDTO inviteTargetTask = taskService.getCurrentTaskByCode(userId,AchievementCodeEnum.INVITE_TARGET.getTaskCode());
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("userList", userList);
			data.put("inviteTask", inviteTask);
			data.put("inviteTargetTask", inviteTargetTask);
			outputMap.setResult(SysReturnCode.SUCC, data);
			
			
			
		} catch (Exception e) {
			log.error("用户{}邀请好友", userId, e);
			outputMap.setResult(SysReturnCode.FAIL, "用户邀请好友失败");
		}

		return outputMap;
		
	}

	/**
	 * 领取邀请个人奖励
	 * @param userId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getReward")
	public GeneralResultMap getReward(@RequestHeader("uid") Long userId, @RequestBody InviteRequest request) {
		GeneralResultMap outMap = new GeneralResultMap();
		try {
			if (request == null || request.getInviteUserId() == null) {
				outMap.setResult(SysReturnCode.INVALID_PARAMS, "参数不合法");
				return outMap;
			}
			if (userBaseInfoService.getByUserId(request.getInviteUserId()) == null) {
				outMap.setResult(SysReturnCode.UNKNOW_USER, "用户不存在");
				return outMap;
			}
			ResultDTO<String> resultDTO = inviteService.getInviteReward(userId,request.getInviteUserId());
			if (resultDTO.isSuccess()) {
				outMap.setResult(SysReturnCode.SUCC); 
			}else {
				outMap.setResult(SysReturnCode.FAIL,resultDTO.getErrDesc()); 
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outMap;
	}
	
	
	
	
}
