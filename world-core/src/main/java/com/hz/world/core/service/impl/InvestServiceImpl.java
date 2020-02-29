/**
 * Java Framework Auto Generator Tools
 * Copyright (c) 2015-2020 www.wifi.com
 *
 * @company wifi
 */
package com.hz.world.core.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hz.world.common.dto.ResultCodeEnum;
import com.hz.world.common.dto.ResultDTO;
import com.hz.world.common.enums.CoinChangeType;
import com.hz.world.common.enums.ElementAdd;
import com.hz.world.core.common.util.ConfigCacheUtil;
import com.hz.world.core.common.util.CoreCacheUtil;
import com.hz.world.core.dao.impl.UserInvestDaoImpl;
import com.hz.world.core.dao.impl.UserInvestLogDaoImpl;
import com.hz.world.core.dao.model.InvestConfig;
import com.hz.world.core.dao.model.UserFortuneLog;
import com.hz.world.core.dao.model.UserInvest;
import com.hz.world.core.domain.dto.UserCoinDTO;
import com.hz.world.core.service.InvestService;
import com.hz.world.core.service.UserCoinService;
import com.hz.world.core.service.UserElementService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InvestServiceImpl implements InvestService {

	@Autowired
	private UserInvestLogDaoImpl userInvestLogDao;

	@Autowired
	private UserInvestDaoImpl userInvestDao;
	@Autowired
	private ConfigCacheUtil configCacheUtil;
	@Autowired
	private UserCoinService userCoinService;
	@Autowired
	private CoreCacheUtil coreCacheUtil;
	@Autowired
	private UserElementService userElementService;

	@Override
	public ResultDTO<String> batchInvest(Long userId) {
		ResultDTO<String> resultDTO = new ResultDTO<String>();
		List<Integer> investList = new ArrayList<Integer>();
		List<Integer> list = getUserUnInvestList(userId);
		userCoinService.updateUserCoin(userId);
		UserCoinDTO coin = userCoinService.getUserCoin(userId);
		BigDecimal cost = new BigDecimal(0) ;
		if (list != null && list.size() > 0) {
			for (Integer invest : list) {
				InvestConfig config = configCacheUtil.getInvestConfig(invest);
				BigDecimal a = new BigDecimal(config.getInitialCost());
				BigDecimal b = new BigDecimal(coin.getCoin());
				
				if (config != null && cost.add(a).compareTo(b) <0) {
					cost =cost.add(a);
					investList.add(invest);
				}
			}
		}
		if (investList.size() <= 0) {
			resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "无可升级");
			return resultDTO;
		}
		ResultDTO<String> resultDTO2 = userCoinService.changeUserCoin(userId, cost.toString(),
				CoinChangeType.INVEST.getCode(), 1);
		if (!resultDTO2.isSuccess()) {
			resultDTO.set(ResultCodeEnum.ERROR_HANDLE, resultDTO2.getErrDesc());
			return resultDTO;
		}
		addRecord(userId, investList);
		resultDTO.set(ResultCodeEnum.SUCCESS, "OK");
		return resultDTO;
	}

	@Override
	public List<Integer> getUserUnInvestList(Long userId) {
		List<Integer> list = new ArrayList<Integer>();
		List<InvestConfig> configList = configCacheUtil.getInvestList();
		for (InvestConfig investConfig : configList) {
			list.add(investConfig.getId());
		}
		List<UserInvest> investList = userInvestDao.getUserInvestList(userId);
		if (investList != null && investList.size() > 0) {
			for (UserInvest userInvest : investList) {
				list.remove(userInvest.getInvestId());
			}
		}
		List<Integer> resultList = new ArrayList<Integer>();
		if (list.size() > 10) {
			resultList = list.subList(0, 10);
		} else {
			resultList = list;
		}
		coreCacheUtil.setLastInvestId(userId, resultList.get(resultList.size() -1));
		return resultList;

	}

	@Override
	public ResultDTO<Integer> invest(Long userId, Integer investId) {
		ResultDTO<Integer> resultDTO = new ResultDTO<Integer>();
		InvestConfig config = configCacheUtil.getInvestConfig(investId);
		if (config == null) {
			resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "数据不存在");
			return resultDTO;
		}
		if (userInvestDao.findByUserId(userId, investId) != null) {
			resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "已经投资过");
			return resultDTO;
		}

		// 扣除金币
		ResultDTO<String> resultDTO2 = userCoinService.changeUserCoin(userId, config.getInitialCost(),
				CoinChangeType.INVEST.getCode(), 1);
		if (!resultDTO2.isSuccess()) {
			resultDTO.set(ResultCodeEnum.ERROR_HANDLE, resultDTO2.getErrDesc());
			return resultDTO;
		}
		List<Integer> invests = new ArrayList<Integer>();
		invests.add(investId);
		addRecord(userId, invests);
		Integer result = null;
		String nextInvest = coreCacheUtil.getLastInvestId(userId);
		if (nextInvest != null) {
			Integer index = Integer.parseInt(nextInvest)+1;
			config = configCacheUtil.getInvestConfig(index);
			if (config != null) {
				coreCacheUtil.setLastInvestId(userId, index);
				result = index;
			}
		}
		resultDTO.set(ResultCodeEnum.SUCCESS, "OK",result);
		return resultDTO;
	}

	private void addRecord(Long userId, List<Integer> investList) {
		if (investList != null && investList.size() > 0) {
			for (Integer investId : investList) {
				InvestConfig config = configCacheUtil.getInvestConfig(investId);
				if (config == null) {
					return ;
				}
				//收益加成
				if (config.getType() == 1) {
					userElementService.addElementAdd(userId, config.getElement(), ElementAdd.INVEST.getCode(), config.getParam()+"");
				}
				//喂养打折
				if (config.getType() == 2) {
					
				}
				// 保存数据
				UserInvest invest = new UserInvest();
				invest.setUserId(userId);
				invest.setInvestId(investId);
				userInvestDao.insert(invest);
				
			}
		}
		

	}
}
