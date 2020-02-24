package com.hz.world.core.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hz.world.common.dto.ResultCodeEnum;
import com.hz.world.common.dto.ResultDTO;
import com.hz.world.common.enums.CoinChangeType;
import com.hz.world.common.enums.ElementAdd;
import com.hz.world.common.ids.IDGenerator;
import com.hz.world.core.common.util.ConfigCacheUtil;
import com.hz.world.core.common.util.CoreCacheUtil;
import com.hz.world.core.dao.impl.UserCoinDaoImpl;
import com.hz.world.core.dao.impl.UserElementDaoImpl;
import com.hz.world.core.dao.impl.UserElementLogDaoImpl;
import com.hz.world.core.dao.model.ElementConfig;
import com.hz.world.core.dao.model.UserElement;
import com.hz.world.core.dao.model.UserElementLog;
import com.hz.world.core.domain.dto.UserElementDTO;
import com.hz.world.core.service.UserCoinService;
import com.hz.world.core.service.UserElementService;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: Description: author linyanchun date Feb 22, 2020
 */
@Slf4j
@Service
public class UserElementServiceImpl implements UserElementService {

	@Autowired
	private CoreCacheUtil coreCacheUtil;
	@Autowired
	private ConfigCacheUtil configCacheUtil;
	@Autowired
	private UserCoinDaoImpl userCoinDao;
	@Autowired
	private UserElementDaoImpl userElementDao;
	@Autowired
	private UserElementLogDaoImpl userElementLogDao;
	@Autowired
	private UserCoinService userCoinService;
	@Override
	public ResultDTO<String> upgradeElement(Long userId, Integer element, Integer originLevel, Integer newLevel) {
		ResultDTO<String> resultDTO = new ResultDTO<String>();
		try {
			userCoinService.updateUserCoin(userId);
			UserElement userElement = userElementDao.fingUserElement(userId, element);
			int initLevel = 0;
			if (userElement != null ) {
				initLevel = userElement.getLevel();
				
			}
			if ( originLevel != initLevel) {
				resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "数据不同步");
				return resultDTO;
			}
			BigDecimal cost = getCostCoin(element, originLevel, newLevel);
			if (cost == null) {
				resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "元素不存在");
				return resultDTO;
			}
			ResultDTO<String> resultDTO2 = userCoinService.changeUserCoin(userId, cost.toString(),
					CoinChangeType.UPGRADE_ELEMENT.getCode(), 1);
			if (!resultDTO2.isSuccess()) {
				return resultDTO2;
			}
			//更新元素等级
			if (userElement == null) {
				userElement = new UserElement();
				userElement.setElement(element);
				userElement.setLevel(newLevel);
				userElement.setUserId(userId);
				userElementDao.insert(userElement);
			}else {
				userElement.setLevel(newLevel);
				userElementDao.update(userElement);
			}
			UserElementLog record = new UserElementLog();
			record.setId(IDGenerator.getUniqueId());
			record.setElement(element);
			record.setLevel(newLevel);
			record.setUserId(userId);
			userElementLogDao.insert(record);
			BigDecimal output = getOutputCoin(userId, element);
			//更新元素等级
			coreCacheUtil.addUserElementValue(userId, element, ElementAdd.LEVEL.getCode(), newLevel+"");
			//更新单个元素产出
			coreCacheUtil.addUserElementValue(userId, element, ElementAdd.OUTPUT.getCode(), output.toString());
			//更新总的产出率
			String totalOutput = getUserOutput(userId);
			userCoinService.updateOutput(userId, totalOutput);
			
			resultDTO.set(ResultCodeEnum.SUCCESS, "OK");
		} catch (Exception e) {
			e.printStackTrace();
			resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "异常");
		}
		return resultDTO;
	}
	@Override
	public ResultDTO<String> addElementAdd(Long userId, Integer element, String field, String value){
		ResultDTO<String> resultDTO = new ResultDTO<String>();
		try {
			userCoinService.updateUserCoin(userId);	
			//更新元素收益
			coreCacheUtil.addUserElementValue(userId, element, field, value);
			BigDecimal output = getOutputCoin(userId, element);
			//更新单个元素产出
			coreCacheUtil.addUserElementValue(userId, element, ElementAdd.OUTPUT.getCode(), output.toString());
			//更新总的产出率
			String totalOutput = getUserOutput(userId);
			userCoinService.updateOutput(userId, totalOutput);
			resultDTO.set(ResultCodeEnum.SUCCESS, "OK");
		} catch (Exception e) {
			e.printStackTrace();
			resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "异常");
		}
		return resultDTO;
	}

	private BigDecimal getCostCoin(Integer element, Integer originLevel, Integer newLevel) {
		ElementConfig config = configCacheUtil.getElement(element);
		if (config == null) {
			return null;
		}
		BigDecimal initialCost = new BigDecimal(config.getInitialCost());

		double gostGrowth = Double.parseDouble(config.getCostGrowth());
		BigDecimal unlockedCost = BigDecimal.valueOf(0);
		if (originLevel <= 0) {
			originLevel = 1;
			unlockedCost = initialCost;
		}
		BigDecimal aBigDecimal = initialCost.multiply(BigDecimal.valueOf(1 - Math.pow(gostGrowth, originLevel)))
				.divide(BigDecimal.valueOf(1 - gostGrowth),2, BigDecimal.ROUND_HALF_UP);
		BigDecimal bBigDecimal = initialCost.multiply(BigDecimal.valueOf(1 - Math.pow(gostGrowth, newLevel)))
				.divide(BigDecimal.valueOf(1 - gostGrowth),2, BigDecimal.ROUND_HALF_UP);

		return bBigDecimal.subtract(aBigDecimal).add(unlockedCost);

	}
	
	private BigDecimal getOutputCoin(Long userId, Integer element) {
		UserElement userElement = userElementDao.fingUserElement(userId, element);
		ElementConfig config = configCacheUtil.getElement(element);
		if (userElement == null || config == null) {
			return BigDecimal.valueOf(0);
		}
		Map<String,String> addMaps = coreCacheUtil.getUserElementObject(userId,element);
		int add = 0;
		if (addMaps != null) {
			for(Map.Entry<String, String> entry : addMaps.entrySet()){
			    String mapKey = entry.getKey();
			    String mapValue = entry.getValue();
			    if (!mapKey.equals(ElementAdd.OUTPUT.getCode()) && !mapKey.equals(ElementAdd.LEVEL.getCode()) ) {
			    	add += Integer.parseInt(mapValue);
				}
			}
		}
		
		BigDecimal initialOuput = new BigDecimal(config.getInitialOutput());
		
		BigDecimal result = initialOuput.multiply(BigDecimal.valueOf(userElement.getLevel())).multiply(BigDecimal.valueOf(1+add));
		  
	    return result;

	}
	@Override
	public String getUserOutput(Long userId) {
		List<ElementConfig> configList =  configCacheUtil.getElementList();
		BigDecimal output = new BigDecimal(0);
		if (configList != null && configList.size() > 0) {
			for (ElementConfig elementConfig : configList) {
				String elementOutput = coreCacheUtil.getUserElementValue(userId, elementConfig.getId(), ElementAdd.OUTPUT.getCode() );
				if (StringUtils.isNoneEmpty(elementOutput)) {
					output = output.add(new BigDecimal(elementOutput));
				}
			}
		}
		return output.toString();
	}
	@Override
	public List<UserElementDTO> getUserElementList(Long userId){
		List<UserElementDTO> elementList = new ArrayList<UserElementDTO>();
		List<ElementConfig> configList =  configCacheUtil.getElementList();
		if (configList != null && configList.size() > 0) {
			for (ElementConfig elementConfig : configList) {
				UserElementDTO elementOutput = new UserElementDTO();
				String level = coreCacheUtil.getUserElementValue(userId, elementConfig.getId(), ElementAdd.LEVEL.getCode() );
				String output = coreCacheUtil.getUserElementValue(userId, elementConfig.getId(), ElementAdd.OUTPUT.getCode() );	
				if (StringUtils.isNoneEmpty(level)) {
					elementOutput.setElement(elementConfig.getId());
					elementOutput.setLevel(Integer.parseInt(level));
					elementOutput.setOutput(output);
				}else {
					elementOutput.setElement(elementConfig.getId());
					elementOutput.setLevel(0);
					elementOutput.setOutput("0");
				}
				elementList.add(elementOutput);
			}
		}
		return elementList;
	}
	@Override
	public UserElementDTO getUserElement(Long userId, Integer element) {
		UserElementDTO elementOutput = new UserElementDTO();
		String level = coreCacheUtil.getUserElementValue(userId, element, ElementAdd.LEVEL.getCode() );
		String output = coreCacheUtil.getUserElementValue(userId, element, ElementAdd.OUTPUT.getCode() );	
		if (StringUtils.isNoneEmpty(level)) {
			elementOutput.setElement(element);
			elementOutput.setLevel(Integer.parseInt(level));
			elementOutput.setOutput(output);
		}else {
			elementOutput.setElement(element);
			elementOutput.setLevel(0);
			elementOutput.setOutput("0");
		}
		return elementOutput;
	}
	
	
}
