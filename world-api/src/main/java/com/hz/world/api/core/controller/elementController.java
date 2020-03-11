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
import com.hz.world.api.core.domain.dto.UserCoinOutDTO;
import com.hz.world.api.core.domain.request.ElementRequest;
import com.hz.world.common.dto.ResultDTO;
import com.hz.world.core.domain.dto.FeedResultDTO;
import com.hz.world.core.domain.dto.UserCoinDTO;
import com.hz.world.core.service.UserCoinService;
import com.hz.world.core.service.UserElementService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/element")
@Slf4j
public class elementController {

	protected static final String X_SECURITY = "X-Security";
	protected static final String X_UUID = "X-UUID";

	@Autowired
	private UserBaseInfoService userBaseInfoService;
	
	@Autowired
	private UserCoinService userCoinService;
	@Autowired
	private UserElementService userElementService;
	

	@RequestMapping(value = "/upgrade", method = { RequestMethod.POST })
	public GeneralResultMap upgrade(@RequestHeader("uid") Long userId,@RequestBody ElementRequest request) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {
			if (request == null || request.getElementId() == null || request.getNewLevel() == null || request.getProLevel() == null) {
				outputMap.setResult(SysReturnCode.LACK_PARAMS, "参数不正确");
				return outputMap;
			}
			UserBaseInfoDTO user = userBaseInfoService.getByUserId(userId);
			if (user == null) {
				outputMap.setResult(SysReturnCode.UNKNOW_USER, "用户不存在");
				return outputMap;
			}
			if (request.getNewLevel() <= request.getProLevel()) {
				outputMap.setResult(SysReturnCode.UNKNOW_USER, "等级错误");
				return outputMap;
			}
			userCoinService.updateUserCoin(userId);
			ResultDTO<List<FeedResultDTO>> resultDTO = userElementService.upgradeElement(userId, request.getElementId() , request.getProLevel(), request.getNewLevel());
			if (resultDTO.isSuccess()) {
				UserCoinDTO coin = userCoinService.getUserCoin(userId);
				UserCoinOutDTO out = new UserCoinOutDTO();
				out.setUpdateTime(coin.getUpdateTime());
				out.setCoin(coin.getCoin());
				out.setOutput(coin.getIcomeRate());
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("coin", out);
				data.put("reward", resultDTO.getResult());
				data.put("element", userElementService.getUserElement(userId, request.getElementId()));
				outputMap.setResult(SysReturnCode.SUCC, data);
				
			}else {
				outputMap.setResult(SysReturnCode.FAIL, resultDTO.getErrDesc());
			}
			

			
			
		} catch (Exception e) {
			log.error("用户{}升级元素{}失败", userId,request.getElementId(), e);
			outputMap.setResult(SysReturnCode.FAIL, "升级元素失败");
		}

		return outputMap;
		
	}

	
	
	
	
}
