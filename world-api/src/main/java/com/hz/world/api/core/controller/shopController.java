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
import com.hz.world.api.core.domain.request.ShopRequest;
import com.hz.world.common.dto.ResultDTO;
import com.hz.world.core.domain.dto.ShopResultDTO;
import com.hz.world.core.service.ShopService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/shop")
@Slf4j
public class shopController {

	protected static final String X_SECURITY = "X-Security";
	protected static final String X_UUID = "X-UUID";

	@Autowired
	private ShopService shopService;
	@Autowired
	private UserBaseInfoService userBaseInfoService;

	

	

	@RequestMapping(value = "/buy", method = { RequestMethod.POST })
	public GeneralResultMap buy(@RequestHeader("uid") Long userId,@RequestBody ShopRequest request) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {
			if (request == null || request.getProductId() == null ) {
				outputMap.setResult(SysReturnCode.LACK_PARAMS, "参数错误");
			}
			UserBaseInfoDTO user = userBaseInfoService.getByUserId(userId);
			if (user == null) {
				outputMap.setResult(SysReturnCode.UNKNOW_USER, "用户不存在");
				return outputMap;
			}
			ResultDTO<ShopResultDTO> resultDTO = shopService.buy(userId, request.getProductId());
			if (resultDTO.isSuccess()) {
				outputMap.setResult(SysReturnCode.SUCC, "ok",resultDTO.getResult());
			}else {
				outputMap.setResult(SysReturnCode.FAIL, resultDTO.getErrDesc());
			}
			
			
		} catch (Exception e) {
			log.error("用户{}购买商品失败{}", userId,request.getProductId(), e);
			outputMap.setResult(SysReturnCode.FAIL, "用户购买商品失败");
		}

		return outputMap;
		
	}
	
	
	
}
