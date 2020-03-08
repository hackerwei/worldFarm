package com.hz.world.api.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hz.world.api.core.domain.dto.GeneralResultMap;
import com.hz.world.api.core.domain.dto.SysReturnCode;
import com.hz.world.api.core.domain.request.FactoryRequest;
import com.hz.world.common.dto.ResultDTO;
import com.hz.world.core.domain.dto.BossDTO;
import com.hz.world.core.domain.dto.ManagerDTO;
import com.hz.world.core.service.FactoryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/factory")
@Slf4j
public class FactoryController {

	protected static final String X_SECURITY = "X-Security";
	protected static final String X_UUID = "X-UUID";

	@Autowired
	private FactoryService factoryService;

	/**
	 * 接受好友邀请
	 * 
	 * @param userId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/invited", method = { RequestMethod.POST })
	public GeneralResultMap invited(@RequestHeader("uid") Long userId, @RequestBody FactoryRequest request) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {
			if (request == null || request.getToUserId() == null) {
				outputMap.setResult(SysReturnCode.LACK_PARAMS, "参数不存在");
			}
			ResultDTO<String> resultDTO = factoryService.inviteUser(request.getToUserId(), request.getElementId(),
					userId);
			if (resultDTO.isSuccess()) {
				outputMap.setResult(SysReturnCode.SUCC, "ok");
			} else {
				outputMap.setResult(SysReturnCode.FAIL, resultDTO.getErrDesc());
			}

		} catch (Exception e) {
			log.error("用户{}接受厂长邀请失败", userId, e);
			outputMap.setResult(SysReturnCode.FAIL, "接受厂长邀请失败");
		}

		return outputMap;

	}

	/**
	 * 解雇厂长
	 * 
	 * @param userId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/fireManager", method = { RequestMethod.POST })
	public GeneralResultMap fireManager(@RequestHeader("uid") Long userId, @RequestBody FactoryRequest request) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {
			if (request == null || request.getToUserId() == null) {
				outputMap.setResult(SysReturnCode.LACK_PARAMS, "参数不存在");
			}
			boolean result = factoryService.fireManager(userId, request.getElementId(), request.getToUserId());
			if (result) {
				outputMap.setResult(SysReturnCode.SUCC, "ok");
			} else {
				outputMap.setResult(SysReturnCode.FAIL, "解雇失败");
			}

		} catch (Exception e) {
			log.error("用户{}解雇厂长失败", userId, e);
			outputMap.setResult(SysReturnCode.FAIL, "解雇厂长失败");
		}

		return outputMap;

	}

	/**
	 * 厂长列表
	 * 
	 * @param userId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/managerList", method = { RequestMethod.POST })
	public GeneralResultMap managerList(@RequestHeader("uid") Long userId, @RequestBody FactoryRequest request) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {
			List<ManagerDTO> managerList = factoryService.getElementManagerList(userId);

			outputMap.setResult(SysReturnCode.SUCC, "ok", managerList);

		} catch (Exception e) {
			log.error("用户{}厂长列表失败", userId, e);
			outputMap.setResult(SysReturnCode.FAIL, "厂长列表失败");
		}

		return outputMap;

	}

	/**
	 * 打工列表
	 * 
	 * @param userId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/workingList", method = { RequestMethod.POST })
	public GeneralResultMap workingList(@RequestHeader("uid") Long userId, @RequestBody FactoryRequest request) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {
			List<BossDTO> workingList = factoryService.workingList(userId);
			outputMap.setResult(SysReturnCode.SUCC, "ok", workingList);

		} catch (Exception e) {
			log.error("用户{}打工列表失败", userId, e);
			outputMap.setResult(SysReturnCode.FAIL, "打工列表失败");
		}

		return outputMap;

	}

	/**
	 * 辞职
	 * 
	 * @param userId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/quit", method = { RequestMethod.POST })
	public GeneralResultMap quit(@RequestHeader("uid") Long userId, @RequestBody FactoryRequest request) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {
			if (request == null || request.getToUserId() == null) {
				outputMap.setResult(SysReturnCode.LACK_PARAMS, "缺少参数");
			}
			if (factoryService.quit(userId, request.getToUserId())) {
				outputMap.setResult(SysReturnCode.SUCC, "ok");
			} else {
				outputMap.setResult(SysReturnCode.SUCC, "ok");
			}

		} catch (Exception e) {
			log.error("用户{}辞职失败", userId, e);
			outputMap.setResult(SysReturnCode.FAIL, "辞职失败");
		}

		return outputMap;

	}

	/**
	 * 请求发送金币
	 * @param userId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/requireCoin", method = { RequestMethod.POST })
	public GeneralResultMap requireCoin(@RequestHeader("uid") Long userId, @RequestBody FactoryRequest request) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {
			if (request == null || request.getToUserId() == null) {
				outputMap.setResult(SysReturnCode.LACK_PARAMS, "缺少参数");
			}
			ResultDTO<String> resultDTO =  factoryService.requireCoin(userId, request.getToUserId());
			if (resultDTO.isSuccess()) {
				outputMap.setResult(SysReturnCode.SUCC, "ok");
			}else {
				outputMap.setResult(SysReturnCode.SUCC, resultDTO.getErrDesc());
			}
		

		} catch (Exception e) {
			log.error("用户{}请求发送金币失败", userId, e);
			outputMap.setResult(SysReturnCode.FAIL, "请求发送金币失败");
		}

		return outputMap;

	}

	/**
	 * 发送金币
	 * @param userId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/sendCoin", method = { RequestMethod.POST })
	public GeneralResultMap sendCoin(@RequestHeader("uid") Long userId, @RequestBody FactoryRequest request) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {
			if (request == null || request.getToUserId() == null) {
				outputMap.setResult(SysReturnCode.LACK_PARAMS, "缺少参数");
			}
			ResultDTO<String> resultDTO =  factoryService.sendCoin(userId, request.getToUserId());
			if (resultDTO.isSuccess()) {
				outputMap.setResult(SysReturnCode.SUCC, "ok");
			}else {
				outputMap.setResult(SysReturnCode.SUCC, resultDTO.getErrDesc());
			}
		

		} catch (Exception e) {
			log.error("用户{}发送金币失败", userId, e);
			outputMap.setResult(SysReturnCode.FAIL, "发送金币失败");
		}

		return outputMap;

	}

}
