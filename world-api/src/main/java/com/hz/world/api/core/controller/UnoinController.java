package com.hz.world.api.core.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hz.world.account.domain.dto.UserBaseDTO;
import com.hz.world.account.domain.dto.UserBaseInfoDTO;
import com.hz.world.account.service.FriendService;
import com.hz.world.account.service.UserBaseInfoService;
import com.hz.world.api.core.domain.dto.FriendIncomeDTO;
import com.hz.world.api.core.domain.dto.GeneralResultMap;
import com.hz.world.api.core.domain.dto.InviterDTO;
import com.hz.world.api.core.domain.dto.SysReturnCode;
import com.hz.world.api.core.domain.dto.UnionLogOutDTO;
import com.hz.world.api.core.domain.request.CatchRequest;
import com.hz.world.api.core.domain.request.UnionRequest;
import com.hz.world.common.util.BeanUtil;
import com.hz.world.common.util.DateUtil;
import com.hz.world.core.dao.model.UnionConfig;
import com.hz.world.core.dao.model.UserUnionIncomeLog;
import com.hz.world.core.service.UnionService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/union")
@Slf4j
public class UnoinController {

	protected static final String X_SECURITY = "X-Security";
	protected static final String X_UUID = "X-UUID";

	@Autowired
	private UserBaseInfoService userBaseInfoService;
	
	@Autowired
	private FriendService friendService;
	@Autowired
	private UnionService unionService;
	

	@RequestMapping(value = "/index", method = { RequestMethod.POST })
	public GeneralResultMap index(@RequestHeader("uid") Long userId,@RequestBody CatchRequest request) {
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
			Map<String, Object> data = new HashMap<String, Object>();
			
			
			int friendCount = friendService.getFriendCount(userId);
			int friengWeight = friendService.getFriendWeight(userId);
			int unAuthFriendCount = friendService.getUnAuthFriendCount(userId);
			double unAuthFriendIncome = 0.0;
			double friendIncome = 0.0;
			double todayIncome = 0;
			double rate = 1.00;
			List<UserBaseDTO> unActiveFriendList= friendService.getUnActiveFriendList(userId, 0, unAuthFriendCount);
			if (unActiveFriendList != null && unActiveFriendList.size() > 0) {
				for (UserBaseDTO userBaseDTO : unActiveFriendList) {
					unAuthFriendIncome += unionService.getUserUnionIncome(userBaseDTO.getUserId());
				}
			}
			todayIncome = unionService.getUserTodayUnionIncome(userId);
			UnionConfig step = unionService.getUnionStep(userId);
			rate = step.getRate();
			data.put("friendCount", friendCount);
			data.put("friengWeight", friengWeight);
			data.put("todayIncome", todayIncome);
			data.put("unAuthFriendCount", unAuthFriendCount);
			data.put("unAuthFriendIncome", unAuthFriendIncome);
			data.put("rate", rate);
			if (user.getFromUserId() != null) {
				UserBaseInfoDTO inviter = userBaseInfoService.getByUserId(user.getFromUserId());
				InviterDTO dto = new InviterDTO();
				dto = BeanUtil.copyProperties(inviter, InviterDTO.class);
				dto.setTodayIncome( unionService.getUserTodayUnionIncome(user.getFromUserId()));
				dto.setInviteNum(friendService.getFriendCount(user.getFromUserId()));
				dto.setTotalIncome(unionService.getUserUnionIncome(user.getFromUserId()));
				data.put("inviter", dto);
			}
			
			FriendIncomeDTO income = new FriendIncomeDTO();
			List<UserBaseDTO> friendList= friendService.getUserFriendList(userId, 0, 1000);
			if (friendList != null && friendList.size() > 0) {
				for (UserBaseDTO userBaseDTO : friendList) {
					friendIncome += unionService.getUserUnionIncome(userBaseDTO.getUserId());
				}
			}
			if (step.getId() > 1) {
				UnionConfig lastStep = unionService.getStepById(step.getId() - 1);
				friendIncome -= lastStep.getTarget();
			}
			income.setStep(step.getId());
			income.setTargetIncome(step.getTarget());	
			income.setTotalIncome(friendIncome);
			income.setRate(rate);
			data.put("friendIncome", income);
			outputMap.setResult(SysReturnCode.SUCC, data);
			
		} catch (Exception e) {
			log.error("用户{}联盟首页失败", userId, e);
			outputMap.setResult(SysReturnCode.FAIL, "用户联盟首页失败");
		}

		return outputMap;
		
	}

	@RequestMapping(value = "/getReward", method = { RequestMethod.POST })
	public GeneralResultMap getReward(@RequestHeader("uid") Long userId,@RequestBody UnionRequest request) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {
			if (request== null || request.getStep() == null) {
				outputMap.setResult(SysReturnCode.LACK_PARAMS, "参数不存在");
			}
			UserBaseInfoDTO user = userBaseInfoService.getByUserId(userId);
			if (user == null ) {
				outputMap.setResult(SysReturnCode.UNKNOW_USER, "用户不存在");
				return outputMap;
			}
			UnionConfig step = unionService.getUnionStep(userId);
			if (!step.getId().equals(request.getStep())) {
				outputMap.setResult(SysReturnCode.FAIL, "当前阶段不符");
				return outputMap;
			}
			double friendIncome = 0.0; 
			List<UserBaseDTO> friendList= friendService.getUserFriendList(userId, 0, 1000);
			if (friendList != null && friendList.size() > 0) {
				for (UserBaseDTO userBaseDTO : friendList) {
					friendIncome += unionService.getUserUnionIncome(userBaseDTO.getUserId());
				}
			}
			if (step.getId() > 1) {
				UnionConfig lastStep = unionService.getStepById(step.getId() - 1);
				friendIncome -= lastStep.getTarget();
			}
			if (friendIncome*step.getRate() >= step.getTarget()) {
				outputMap.setResult(SysReturnCode.FAIL, "还未达成条件");
				return outputMap;
			}
			
			if (unionService.getReward(userId, request.getStep())) {
				outputMap.setResult(SysReturnCode.SUCC, "领取成功");
			}else {
				outputMap.setResult(SysReturnCode.FAIL, "领取失败");
			}
		
			
			
		} catch (Exception e) {
			log.error("用户{}获取联盟失败", userId, e);
			outputMap.setResult(SysReturnCode.FAIL, "用户捕捞失败");
		}

		return outputMap;
		
	}
	
	@RequestMapping(value = "/logList", method = { RequestMethod.POST })
	public GeneralResultMap logList(@RequestHeader("uid") Long userId,@RequestBody UnionRequest request) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {
			if (request== null || request.getStep() == null) {
				outputMap.setResult(SysReturnCode.LACK_PARAMS, "参数不存在");
			}
			Integer offset = (request.getPageNo() - 1) * request.getPageSize();
			Integer limit = request.getPageSize();
			List<UnionLogOutDTO> list = new ArrayList<UnionLogOutDTO>();
			List<UserUnionIncomeLog>logList  =  unionService.getLogList(userId, offset, limit);
			if (logList != null && logList.size() > 0) {
				for (UserUnionIncomeLog userUnionIncomeLog : logList) {
					UnionLogOutDTO logOutDTO = new UnionLogOutDTO();
					logOutDTO.setIncome(userUnionIncomeLog.getIncome());
					logOutDTO.setContent("联盟收益");
					logOutDTO.setDate(DateUtil.format(userUnionIncomeLog.getDate(), "yyyy/MM/dd"));
					list.add(logOutDTO);
				}
			}
			
		
			outputMap.setResult(SysReturnCode.SUCC, list);
			
		} catch (Exception e) {
			log.error("用户{}联盟收益日志失败", userId, e);
			outputMap.setResult(SysReturnCode.FAIL, "用户联盟收益日志失败");
		}

		return outputMap;
		
	}
	
	
	
}
