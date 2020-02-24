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
import com.hz.world.api.core.domain.request.RankingRequest;
import com.hz.world.common.dto.ResultDTO;
import com.hz.world.core.domain.dto.RankDTO;
import com.hz.world.core.domain.dto.UserCoinDTO;
import com.hz.world.core.service.RankingService;
import com.hz.world.core.service.UserCoinService;
import com.hz.world.core.service.UserElementService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/ranking")
@Slf4j
public class rankingController {

	protected static final String X_SECURITY = "X-Security";
	protected static final String X_UUID = "X-UUID";

	@Autowired
	private RankingService rankingService;
	

	

	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	public GeneralResultMap list(@RequestHeader("uid") Long userId,@RequestBody RankingRequest request) {
		GeneralResultMap outputMap = new GeneralResultMap();
		try {
			if (request == null || request.getType() == null) {
				outputMap.setResult(SysReturnCode.LACK_PARAMS, "参数错误");
			}
			int myrank = 0;
			List<RankDTO> list = rankingService.getRankingList(request.getType());
			if (list != null && list.size() > 0 ) {
				for (RankDTO rankDTO : list) {
					if (rankDTO.getUserId().equals(userId)) {
						myrank = rankDTO.getRank();
					}
				}
			}
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("list", list);
			data.put("myrank", myrank);
			outputMap.setResult(SysReturnCode.SUCC, data);
			
		} catch (Exception e) {
			log.error("用户{}获取排行榜{}失败", userId,request.getType(), e);
			outputMap.setResult(SysReturnCode.FAIL, "获取排行榜失败");
		}

		return outputMap;
		
	}

	
	
	
	
}
