package com.hz.world.core.common.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.hz.world.common.cache.redis.RedisService;
import com.hz.world.common.constant.RedisConstants;
import com.hz.world.core.dao.impl.CatchConfigDaoImpl;
import com.hz.world.core.dao.impl.ChallengeConfigDaoImpl;
import com.hz.world.core.dao.impl.CollectConfigDaoImpl;
import com.hz.world.core.dao.impl.ElementConfigDaoImpl;
import com.hz.world.core.dao.impl.InvestConfigDaoImpl;
import com.hz.world.core.dao.impl.RechargeConfigDaoImpl;
import com.hz.world.core.dao.impl.ShopConfigDaoImpl;
import com.hz.world.core.dao.impl.TitleConfigDaoImpl;
import com.hz.world.core.dao.impl.UnionConfigDaoImpl;
import com.hz.world.core.dao.impl.WorshipDaoImpl;
import com.hz.world.core.dao.impl.YearConfigDaoImpl;
import com.hz.world.core.dao.model.CatchConfig;
import com.hz.world.core.dao.model.ChallengeConfig;
import com.hz.world.core.dao.model.CollectConfig;
import com.hz.world.core.dao.model.ElementConfig;
import com.hz.world.core.dao.model.InvestConfig;
import com.hz.world.core.dao.model.RechargeConfig;
import com.hz.world.core.dao.model.ShopConfig;
import com.hz.world.core.dao.model.UnionConfig;
import com.hz.world.core.dao.model.Worship;
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
	@Autowired
	InvestConfigDaoImpl investConfigDao;
	@Autowired
	ChallengeConfigDaoImpl challengeConfigDao;
	@Autowired
	CollectConfigDaoImpl collectConfigDao;
	@Autowired
	UnionConfigDaoImpl unionConfigDao;
	@Autowired
	WorshipDaoImpl worshipDao;
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
	public List<InvestConfig> getInvestList(){
		String key = RedisConstants.RICHER_CONFIG_INVEST;
		List<InvestConfig> list = new ArrayList<InvestConfig>();
		if (!redisService.exists(key)) {
			list = investConfigDao.findAll();
			redisService.set(key, JSON.toJSONString(list));
		}else {
			String json = redisService.get(key);
			list = JSON.parseArray(json, InvestConfig.class);

		}
		return list;
	}
	public InvestConfig getInvestConfig(Integer id) {
		List<InvestConfig> list = getInvestList();
		if (list != null && list.size() > 0) {
			for (InvestConfig catchConfig : list) {
				if (catchConfig.getId().equals(id)) {
					return catchConfig;
				}
			}
		}
		return null;
	}
	public List<ChallengeConfig> getChallengeList(){
		String key = RedisConstants.RICHER_CONFIG_CHALLENGE;
		List<ChallengeConfig> list = new ArrayList<ChallengeConfig>();
		if (!redisService.exists(key)) {
			list = challengeConfigDao.findAll();
			redisService.set(key, JSON.toJSONString(list));
		}else {
			String json = redisService.get(key);
			list = JSON.parseArray(json, ChallengeConfig.class);

		}
		return list;
	}
	// # TODO 提供一个按照element查询的ChallengeList的接口，能优化Challenge的一些函数
	// @linyanchun  怎么写?
	public List<ChallengeConfig> getChallengeListByElement(Integer element){
		String key = RedisConstants.RICHER_CONFIG_CHALLENGE;
		List<ChallengeConfig> list = new ArrayList<ChallengeConfig>();
		if (!redisService.exists(key)) {
			list = challengeConfigDao.findAll();
			redisService.set(key, JSON.toJSONString(list));
		}else {
			String json = redisService.get(key);
			list = JSON.parseArray(json, ChallengeConfig.class);

		}
		return list;
	}

	public ChallengeConfig getChallengeConfig(Integer id) {
		List<ChallengeConfig> list = getChallengeList();
		if (list != null && list.size() > 0) {
			for (ChallengeConfig catchConfig : list) {
				if (catchConfig.getId().equals(id)) {
					return catchConfig;
				}
			}
		}
		return null;
	}
	public List<CollectConfig> getCollectList(){
		String key = RedisConstants.RICHER_CONFIG_COLLECT;
		List<CollectConfig> list = new ArrayList<CollectConfig>();
		if (!redisService.exists(key)) {
			list = collectConfigDao.findAll();
			redisService.set(key, JSON.toJSONString(list));
		}else {
			String json = redisService.get(key);
			list = JSON.parseArray(json, CollectConfig.class);

		}
		return list;
	}
	public CollectConfig getCollectConfig(Integer id) {
		List<CollectConfig> list = getCollectList();
		if (list != null && list.size() > 0) {
			for (CollectConfig catchConfig : list) {
				if (catchConfig.getId().equals(id)) {
					return catchConfig;
				}
			}
		}
		return null;
	}
	public List<CollectConfig> findByType(Integer type, String param ){
		List<CollectConfig> result = new ArrayList<CollectConfig>();
		List<CollectConfig> list = getCollectList();
		if (list != null && list.size() > 0) {
			for (CollectConfig catchConfig : list) {
				BigDecimal b1 = new BigDecimal(param) ;
				BigDecimal b2 = new BigDecimal(catchConfig.getParam()) ;
				if (catchConfig.getType().equals(type) && b1.compareTo(b2) >= 0) {
					result.add(catchConfig);
				}
			}
		}

		return result;
	}
	public List<UnionConfig> getUnionList(){
		String key = RedisConstants.RICHER_CONFIG_UNION;
		List<UnionConfig> list = new ArrayList<UnionConfig>();
		if (!redisService.exists(key)) {
			list = unionConfigDao.findAll();
			redisService.set(key, JSON.toJSONString(list));
		}else {
			String json = redisService.get(key);
			list = JSON.parseArray(json, UnionConfig.class);

		}
		return list;
	}
	public UnionConfig getUnionConfig(Integer id) {
		List<UnionConfig> list = getUnionList();
		if (list != null && list.size() > 0) {
			for (UnionConfig catchConfig : list) {
				if (catchConfig.getId().equals(id)) {
					return catchConfig;
				}
			}
		}
		return null;
	}
	public List<Worship> getWorshipList(){
		String key = RedisConstants.RICHER_CONFIG_WORSHIP;
		List<Worship> list = new ArrayList<Worship>();
		if (!redisService.exists(key)) {
			list = worshipDao.findAll();
			redisService.set(key, JSON.toJSONString(list));
		}else {
			String json = redisService.get(key);
			list = JSON.parseArray(json, Worship.class);

		}
		return list;
	}
	public Worship getWorshipConfig(Integer id) {
		List<Worship> list = getWorshipList();
		if (list != null && list.size() > 0) {
			for (Worship data : list) {
				if (data.getId().equals(id)) {
					return data;
				}
			}
		}
		return null;
	}
} 
