package com.hz.world.core.common.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.hz.world.common.cache.redis.RedisService;
import com.hz.world.common.constant.RedisConstants;
import com.hz.world.core.dao.impl.ElementConfigDaoImpl;
import com.hz.world.core.dao.impl.TitleConfigDaoImpl;
import com.hz.world.core.dao.impl.UserCoinDaoImpl;
import com.hz.world.core.dao.model.ElementConfig;
import com.hz.world.core.dao.model.TitleConfig;
import com.hz.world.core.dao.model.UserCoin;
import com.hz.world.core.domain.dto.UserCoinDTO;
import com.hz.world.core.domain.dto.UserTmpIncomeDTO;
import com.hz.world.core.service.UserElementService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CoreCacheUtil {

	@Autowired
	RedisService redisService;
	@Autowired
	UserElementService userElementService;
	@Autowired
	UserCoinDaoImpl userCoinDao;
	@Autowired
	ElementConfigDaoImpl elementConfigDao;
	@Autowired
	TitleConfigDaoImpl titleConfigDao;

	public UserCoinDTO getUserCoin(Long userId) {
		UserCoinDTO userCoinDTO = new UserCoinDTO();
		String key = String.format(RedisConstants.RICHER_USER_COIN, userId);
		if (!redisService.exists(key)) {
			UserCoin coin = userCoinDao.findByUserId(userId);
			if (coin != null) {
				BeanUtils.copyProperties(coin, userCoinDTO);
				userCoinDTO.setIcomeRate(userElementService.getUserOutput(userId));
			}else {
				coin = new UserCoin();
				coin.setUserId(userId);
				coin.setIncomeRate("0");
				coin.setCoin("100");
				coin.setUpdateTime(new Date().getTime()/1000);
				userCoinDTO.setIcomeRate("0");
			}
			redisService.set(key, JSON.toJSONString(userCoinDTO));
			
		}else {
			String json = redisService.get(key);
			userCoinDTO =  JSON.parseObject(json, UserCoinDTO.class);
		}
		return userCoinDTO; 
	}
	public void updateCoin(UserCoinDTO coin) {
		if (coin != null) {
			String key = String.format(RedisConstants.RICHER_USER_COIN, coin.getUserId());
			redisService.set(key, JSON.toJSONString(coin));
		}
	}
	
	public ElementConfig getElement(Integer id) {
		
		List<ElementConfig> list = getElementList();
		if (list != null && list.size() > 0) {
			for (ElementConfig elementConfig : list) {
				if (elementConfig.getId().equals(id)) {
					return elementConfig;
				}
			}
		}
		return null;
	}
	public List<ElementConfig> getElementList(){
		String key = RedisConstants.RICHER_CONFIG_ELEMENT;
		List<ElementConfig> list = new ArrayList<ElementConfig>();
		if (!redisService.exists(key)) {
			list = elementConfigDao.findAll();
			redisService.set(key, JSON.toJSONString(list));
		}else {
			String json = redisService.get(key);
			list = JSON.parseArray(json, ElementConfig.class);

		}
		return list;
	}
	public void addUserElementValue(Long userId, Integer element,String field, String value ) {
		String key = String.format(RedisConstants.RICHER_USER_ELEMENT, userId,element);
		redisService.hset(key, field, value);
	}
	
	public String getUserElementValue(Long userId, Integer element, String field) {
		String key = String.format(RedisConstants.RICHER_USER_ELEMENT, userId,element);
		return redisService.hget(key, field);
	}
	
	public void updateUserIncome(Long userId, String income) {
		String key = String.format(RedisConstants.RICHER_USER_INCOME_COIN, userId);
		redisService.set(key, income);
	}
	public String getUserIncome(Long userId) {
		String key = String.format(RedisConstants.RICHER_USER_INCOME_COIN, userId);
		String result =  redisService.get(key);
		if (StringUtils.isEmpty(result)) {
			result =  "0";
		}
		return result;
	}
	

	/**
	 * 获取用户已达当前等级
	 * @param income
	 * @return
	 */
	public int getIncomeLevel(String income) {
		String key = RedisConstants.RICHER_CONFIG_TITLE;
		List<TitleConfig> list = new ArrayList<TitleConfig>();
		if (!redisService.exists(key)) {
			list = titleConfigDao.findAll();
			redisService.set(key, JSON.toJSONString(list));
		}else {
			String json = redisService.get(key);
			list = JSON.parseArray(json, TitleConfig.class);

		}
		BigDecimal a = new BigDecimal (income);
	
		int level = 1;
		if (list != null && list.size() > 0) {
			for (TitleConfig titleConfig : list) {
				BigDecimal b = new BigDecimal (titleConfig.getIncome());
				if (a.compareTo(b) >= 0) {
					level = titleConfig.getId();
				}
			}
		}
		return level;
	}

	/**
	 * 创建临时收益
	 * @param income
	 * @return
	 */
	public void createTmpUserIncome(Long userId, UserTmpIncomeDTO income) {
		String key = String.format(RedisConstants.RICHER_USER_TMP_INCOME, userId);
		redisService.set(key, JSON.toJSONString(income));
	}
	
	public UserTmpIncomeDTO getTmpIncome(Long userId) {
		String key = String.format(RedisConstants.RICHER_USER_TMP_INCOME, userId);
		if (redisService.exists(key)) {
			String json = redisService.get(key);
			return  JSON.parseObject(json, UserTmpIncomeDTO.class);
			 
		}
		return null;
	}

} 
