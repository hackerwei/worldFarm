package com.hz.world.core.common.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.hz.world.common.cache.redis.RedisService;
import com.hz.world.common.constant.RedisConstants;
import com.hz.world.core.dao.impl.CatchConfigDaoImpl;
import com.hz.world.core.dao.impl.ElementConfigDaoImpl;
import com.hz.world.core.dao.impl.RechargeConfigDaoImpl;
import com.hz.world.core.dao.impl.ShopConfigDaoImpl;
import com.hz.world.core.dao.impl.TitleConfigDaoImpl;
import com.hz.world.core.dao.impl.YearConfigDaoImpl;
import com.hz.world.core.dao.model.CatchConfig;
import com.hz.world.core.dao.model.ElementConfig;
import com.hz.world.core.dao.model.RechargeConfig;
import com.hz.world.core.dao.model.ShopConfig;
import com.hz.world.core.dao.model.YearConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ConfigCacheUtil {

	@Autowired
	RedisService redisService;

	@Autowired
	ElementConfigDaoImpl elementConfigDao;
	@Autowired
	TitleConfigDaoImpl titleConfigDao;
	@Autowired
	CatchConfigDaoImpl catchConfigDao;
	@Autowired
	RechargeConfigDaoImpl rechargeConfigDao;
	@Autowired
	YearConfigDaoImpl yearConfigDao;
	@Autowired
	ShopConfigDaoImpl shopConfigDao;
	
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
	public List<CatchConfig> getCatchList(){
		String key = RedisConstants.RICHER_CONFIG_CATCH;
		List<CatchConfig> list = new ArrayList<CatchConfig>();
		if (!redisService.exists(key)) {
			list = catchConfigDao.findAll();
			redisService.set(key, JSON.toJSONString(list));
		}else {
			String json = redisService.get(key);
			list = JSON.parseArray(json, CatchConfig.class);

		}
		return list;
	}
	public CatchConfig getCatchConfig(Integer id) {
		List<CatchConfig> list = getCatchList();
		if (list != null && list.size() > 0) {
			for (CatchConfig catchConfig : list) {
				if (catchConfig.getId().equals(id)) {
					return catchConfig;
				}
			}
		}
		return null;
	}
	public List<RechargeConfig> getRechargeList(){
		String key = RedisConstants.RICHER_CONFIG_RECHARGE;
		List<RechargeConfig> list = new ArrayList<RechargeConfig>();
		if (!redisService.exists(key)) {
			list = rechargeConfigDao.findAll();
			redisService.set(key, JSON.toJSONString(list));
		}else {
			String json = redisService.get(key);
			list = JSON.parseArray(json, RechargeConfig.class);

		}
		return list;
	}
	public RechargeConfig getRechargeConfig(Integer id) {
		List<RechargeConfig> list = getRechargeList();
		if (list != null && list.size() > 0) {
			for (RechargeConfig catchConfig : list) {
				if (catchConfig.getId().equals(id)) {
					return catchConfig;
				}
			}
		}
		return null;
	}
	public List<ShopConfig> getShopList(){
		String key = RedisConstants.RICHER_CONFIG_SHOP;
		List<ShopConfig> list = new ArrayList<ShopConfig>();
		if (!redisService.exists(key)) {
			list = shopConfigDao.findAll();
			redisService.set(key, JSON.toJSONString(list));
		}else {
			String json = redisService.get(key);
			list = JSON.parseArray(json, ShopConfig.class);

		}
		return list;
	}
	public ShopConfig getShopConfig(Integer id) {
		List<ShopConfig> list = getShopList();
		if (list != null && list.size() > 0) {
			for (ShopConfig catchConfig : list) {
				if (catchConfig.getId().equals(id)) {
					return catchConfig;
				}
			}
		}
		return null;
	}
	public List<YearConfig> getYearList(){
		String key = RedisConstants.RICHER_CONFIG_YEAR;
		List<YearConfig> list = new ArrayList<YearConfig>();
		if (!redisService.exists(key)) {
			list = yearConfigDao.findAll();
			redisService.set(key, JSON.toJSONString(list));
		}else {
			String json = redisService.get(key);
			list = JSON.parseArray(json, YearConfig.class);

		}
		return list;
	}
	public YearConfig getYearConfig(Integer year) {
		List<YearConfig> list = getYearList();
		if (list != null && list.size() > 0) {
			for (YearConfig catchConfig : list) {
				if (catchConfig.getYear().equals(year)) {
					return catchConfig;
				}
			}
		}
		return null;
	}

} 
