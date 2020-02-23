package com.hz.world.core.common.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.hz.world.common.cache.redis.RedisService;
import com.hz.world.common.constant.RedisConstants;
import com.hz.world.core.dao.impl.ElementConfigDaoImpl;
import com.hz.world.core.dao.impl.UserCoinDaoImpl;
import com.hz.world.core.dao.model.ElementConfig;
import com.hz.world.core.dao.model.UserCoin;
import com.hz.world.core.domain.dto.UserCoinDTO;
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

	public UserCoinDTO getUserCoin(Long userId) {
		UserCoinDTO userCoinDTO = new UserCoinDTO();
		String key = String.format(RedisConstants.RICHER_USER_COIN, userId);
		if (!redisService.exists(key)) {
			UserCoin coin = userCoinDao.findByUserId(userId);
			if (coin != null) {
				BeanUtils.copyProperties(coin, userCoinDTO);
				redisService.set(key, JSON.toJSONString(userCoinDTO));
			}
			userCoinDTO.setIcomeRate(userElementService.getUserOutput(userId));
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

}
