package com.hz.world.api.account.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hz.world.account.domain.dto.UserBaseDTO;
import com.hz.world.account.service.FriendService;
import com.hz.world.api.account.request.FriendRequest;
import com.hz.world.api.core.domain.dto.GeneralResultMap;
import com.hz.world.api.core.domain.dto.SysReturnCode;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/friend")
@Slf4j
public class FriendController {

	protected static final String X_SECURITY = "X-Security";
	protected static final String X_UUID = "X-UUID";

	@Autowired
	private FriendService friendService;

	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	public GeneralResultMap list(@RequestHeader("uid") Long userId,@RequestBody FriendRequest request) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {
			if (request == null || request.getType() == null) {
				outputMap.setResult(SysReturnCode.LACK_PARAMS, "参数不合法");
				return outputMap;
			}
			Integer offset = (request.getPageNo() - 1) * request.getPageSize();
			Integer limit = request.getPageSize();
			List<UserBaseDTO> userList = new ArrayList<UserBaseDTO>();
			if (request.getType().equals(0)) {
				userList = friendService.getUserFriendList(userId, offset, limit);
			}
			else if (request.getType().equals(1)) {
				userList = friendService.getFriendFriendList(userId, offset, limit);
			}
			else if (request.getType().equals(2)) {
				userList = friendService.getUnActiveFriendList(userId, offset, limit);
			}
					
			
			outputMap.setResult(SysReturnCode.SUCC, userList);
		} catch (Exception e) {
			log.error("用户{}查询好友列表失败", userId, e);
			outputMap.setResult(SysReturnCode.FAIL, "查询好友列表失败");
		}

		return outputMap;
		
	}

	

	
	
}
