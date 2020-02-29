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

import com.hz.world.api.core.domain.dto.GeneralResultMap;
import com.hz.world.api.core.domain.dto.SysReturnCode;
import com.hz.world.api.core.domain.request.InvestRequest;
import com.hz.world.common.dto.ResultDTO;
import com.hz.world.core.domain.dto.UserCoinDTO;
import com.hz.world.core.service.InvestService;
import com.hz.world.core.service.UserCoinService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/invest")
@Slf4j
public class InvestController {

	protected static final String X_SECURITY = "X-Security";
	protected static final String X_UUID = "X-UUID";

	@Autowired
	private InvestService investService;
	@Autowired
	private UserCoinService userCoinService;
	
	/**
	 * 投资列表
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	public GeneralResultMap index(@RequestHeader("uid") Long userId) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {
			
			List<Integer> list = investService.getUserUnInvestList(userId);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("investList", list);
			outputMap.setResult(SysReturnCode.SUCC, list);
			
			
			
		} catch (Exception e) {
			log.error("用户{}获取投资列表失败", userId, e);
			outputMap.setResult(SysReturnCode.FAIL, "获取投资列表失败");
		}

		return outputMap;
		
	}


	/**
	 * 单个投资
	 * @param userId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/investOne")
	public GeneralResultMap getReward(@RequestHeader("uid") Long userId, @RequestBody InvestRequest request) {
		GeneralResultMap outMap = new GeneralResultMap();
		try {
			if (request == null || request.getInvestId() == null) {
				outMap.setResult(SysReturnCode.INVALID_PARAMS, "参数不合法");
				return outMap;
			}
			ResultDTO<Integer> resultDTO = investService.invest(userId, request.getInvestId());
			
			if (resultDTO.isSuccess()) {
				Map<String, Object> data = new HashMap<String, Object>();
				UserCoinDTO coin = userCoinService.getUserCoin(userId);
				data.put("nextId", resultDTO.getResult());
				data.put("coin", coin.getCoin());
				data.put("incomeRate", coin.getIcomeRate());
				outMap.setResult(SysReturnCode.SUCC,data); 
			}else {
				outMap.setResult(SysReturnCode.FAIL,resultDTO.getErrDesc()); 
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outMap;
	}

	/**
	 * 一键投资
	 * @param userId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/investBatch")
	public GeneralResultMap investBatch(@RequestHeader("uid") Long userId) {
		GeneralResultMap outMap = new GeneralResultMap();
		try {
			
			ResultDTO<String> resultDTO = investService.batchInvest(userId);
			
			if (resultDTO.isSuccess()) {
				Map<String, Object> data = new HashMap<String, Object>();
				List<Integer> list = investService.getUserUnInvestList(userId);
				UserCoinDTO coin = userCoinService.getUserCoin(userId);
				data.put("investList", list);
				data.put("coin", coin.getCoin());
				data.put("incomeRate", coin.getIcomeRate());
				outMap.setResult(SysReturnCode.SUCC,data); 
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
