package com.hz.world.core.common.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.hz.world.account.domain.dto.UserBaseInfoDTO;
import com.hz.world.account.service.UserBaseInfoService;
import com.hz.world.common.cache.redis.RedisService;
import com.hz.world.common.constant.RedisConstants;
import com.hz.world.core.dao.impl.TitleConfigDaoImpl;
import com.hz.world.core.dao.impl.UserCoinDaoImpl;
import com.hz.world.core.dao.impl.UserTotalIncomeDaoImpl;
import com.hz.world.core.dao.model.CatchConfig;
import com.hz.world.core.dao.model.TitleConfig;
import com.hz.world.core.dao.model.UserCoin;
import com.hz.world.core.dao.model.UserTotalIncome;
import com.hz.world.core.domain.dto.RankDTO;
import com.hz.world.core.domain.dto.UserCoinDTO;
import com.hz.world.core.domain.dto.UserTmpIncomeDTO;
import com.hz.world.core.service.UserElementService;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Tuple;

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
	TitleConfigDaoImpl titleConfigDao;
	@Autowired
	UserTotalIncomeDaoImpl userTotalIncomeDao;
	@Autowired
	UserBaseInfoService userBaseInfoService;
	@Autowired
	ConfigCacheUtil configCacheUtil;

	public UserCoinDTO getUserCoin(Long userId) {
		UserCoinDTO userCoinDTO = new UserCoinDTO();
		String key = String.format(RedisConstants.RICHER_USER_COIN, userId);
		if (!redisService.exists(key)) {
			UserCoin coin = userCoinDao.findByUserId(userId);
			if (coin != null) {
				BeanUtils.copyProperties(coin, userCoinDTO);
				userCoinDTO.setIcomeRate(userElementService.getUserOutput(userId));
			} else {
				coin = new UserCoin();
				coin.setUserId(userId);
				coin.setIncomeRate("0");
				coin.setCoin("100");
				coin.setUpdateTime(new Date().getTime() / 1000);
				userCoinDTO.setIcomeRate("0");
			}
			redisService.set(key, JSON.toJSONString(userCoinDTO));

		} else {
			String json = redisService.get(key);
			userCoinDTO = JSON.parseObject(json, UserCoinDTO.class);
		}
		return userCoinDTO;
	}

	public void updateCoin(UserCoinDTO coin) {
		if (coin != null) {
			String key = String.format(RedisConstants.RICHER_USER_COIN, coin.getUserId());
			redisService.set(key, JSON.toJSONString(coin));
		}
	}

	/**
	 * 增加用户全局收益
	 * @param userId
	 * @param field
	 * @param value
	 */
	public void addUserTotalAdd(Long userId, String field, long value) {
		String key = String.format(RedisConstants.RICHER_USER_TOTAL_ADD, userId);
		redisService.hincrBy(key, field, value);
	}
	public int getUserTotalAddByField(Long userId, String field) {
		String key = String.format(RedisConstants.RICHER_USER_TOTAL_ADD, userId);
		String value = redisService.hget(key, field);
		if (value == null) {
			return 1;
		}
		return Integer.parseInt(value);
	}
	public int getUserTotalAdd(Long userId) {
		String key = String.format(RedisConstants.RICHER_USER_TOTAL_ADD, userId);
		int total = 0;
		Map<String,String> map = redisService.hgetAll(key);
		if (map != null && map.size() > 0) {
			for (Map.Entry<String, String> entry : map.entrySet()) {
				total += Integer.parseInt(entry.getValue());
			}
		}
		return total;
	}
	public void addUserElementValue(Long userId, Integer element, String field, String value) {
		String key = String.format(RedisConstants.RICHER_USER_ELEMENT, userId, element);
		redisService.hset(key, field, value);
	}

	public String getUserElementValue(Long userId, Integer element, String field) {
		String key = String.format(RedisConstants.RICHER_USER_ELEMENT, userId, element);
		return redisService.hget(key, field);
	}

	public Map<String, String> getUserElementObject(Long userId, Integer element) {
		String key = String.format(RedisConstants.RICHER_USER_ELEMENT, userId, element);
		return redisService.hgetAll(key);
	}

	public void updateUserIncome(Long userId, String income) {
		String key = String.format(RedisConstants.RICHER_USER_INCOME_COIN, userId);
		redisService.set(key, income);
	}

	public String getUserIncome(Long userId) {
		String key = String.format(RedisConstants.RICHER_USER_INCOME_COIN, userId);
		String result = redisService.get(key);
		if (StringUtils.isEmpty(result)) {
			result = "0";
		}
		return result;
	}

	/**
	 * 获取用户已达当前等级
	 * 
	 * @param income
	 * @return
	 */
	public int getIncomeLevel(String income) {
		String key = RedisConstants.RICHER_CONFIG_TITLE;
		List<TitleConfig> list = new ArrayList<TitleConfig>();
		if (!redisService.exists(key)) {
			list = titleConfigDao.findAll();
			redisService.set(key, JSON.toJSONString(list));
		} else {
			String json = redisService.get(key);
			list = JSON.parseArray(json, TitleConfig.class);

		}
		BigDecimal a = new BigDecimal(income);

		int level = 1;
		if (list != null && list.size() > 0) {
			for (TitleConfig titleConfig : list) {
				BigDecimal b = new BigDecimal(titleConfig.getIncome());
				if (a.compareTo(b) >= 0) {
					level = titleConfig.getId();
				}
			}
		}
		return level;
	}

	/**
	 * 创建临时收益
	 * 
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
			return JSON.parseObject(json, UserTmpIncomeDTO.class);

		}
		return null;
	}

	/**
	 * 永久小龙虾集合
	 * 
	 * @param userId
	 */
	public void addForeverCatchSet(Long userId) {
		String key = RedisConstants.RICHER_USER_CATCH_FOREVER_SET;
		redisService.sadd(key, userId + "");
	}

	/**
	 * 是否有永久小龙虾
	 * 
	 * @param userId
	 * @return
	 */
	public boolean isInForeverSet(Long userId) {
		String key = RedisConstants.RICHER_USER_CATCH_FOREVER_SET;
		return redisService.sismember(key, userId + "");
	}

	public void addCatchSet(Long userId, Double score) {
		String key = RedisConstants.RICHER_USER_CATCH_SET;
		redisService.zadd(key, score, userId + "");
	}

	public long getCatchSetCount() {
		String key = RedisConstants.RICHER_USER_CATCH_SET;
		return redisService.zcount(key, 0d, -1d);
	}

	public double getCatchMinScore() {
		String key = RedisConstants.RICHER_USER_CATCH_SET;
		Set<Tuple> set = redisService.zrangeByScoreWithScores(key, "+inf", "-inf", 0, 1);
		if (set != null && set.size() > 0) {
			for (Tuple tuple : set) {
				return tuple.getScore();
			}
		}
		return 0;
	}

	public Set<Tuple> getYearRanking() {
		String key = RedisConstants.RICHER_USER_CATCH_SET;
		return redisService.zrevrangeByScoreWithScores(key, "+inf", "-inf", 0, 100);
	}
	public Double  getUserYear(Long userId) {
		String key = RedisConstants.RICHER_USER_CATCH_SET;
		return redisService.zscore(key, userId+"");
	}
	

	/**
	 * 排行榜
	 * 
	 * @param type
	 * @return
	 */
	public List<RankDTO> getRankingList(Integer type) {
		List<RankDTO> list = new ArrayList<RankDTO>();
		String key = String.format(RedisConstants.RICHER_RANKING_LIST, type);
		if (redisService.exists(key)) {
			String json = redisService.get(key);
			list = JSON.parseArray(json, RankDTO.class);
		} else {
			// 年份排行
			if (type == 0) {
				int rank = 1;
				Set<Tuple> set = getYearRanking();
				if (set != null && set.size() > 0) {
					for (Tuple tuple : set) {
						RankDTO rankDTO = new RankDTO();
						rankDTO.setRank(rank++);
						UserBaseInfoDTO user = userBaseInfoService.getByUserId(Long.parseLong(tuple.getElement()));
						if (user != null) {
							rankDTO.setUserId(user.getUserId());
							rankDTO.setHeadImg(user.getHeadImg());
							rankDTO.setNickname(user.getNickname());
						}
						double score = tuple.getScore();

						// 永久分红
						if (score > 100000) {
							CatchConfig config = configCacheUtil.getCatchConfig(1);
							if (config != null) {
								rankDTO.setText(config.getName());
							}
							int year = (int) (score - 100000) / 10;
							rankDTO.setYear(year);
							list.add(rankDTO);
						} else {
							int catchId = (int) score % 10;
							CatchConfig config = configCacheUtil.getCatchConfig(catchId);
							if (config != null) {
								rankDTO.setText(config.getName());
							}
							int year = (int) score / 10;
							rankDTO.setYear(year);
							list.add(rankDTO);
						}
					}
				}
			} else {
				int rank = 1;
				List<UserTotalIncome> incomeList = userTotalIncomeDao.getUserList();
				if (incomeList != null && incomeList.size() > 0) {
					for (UserTotalIncome userTotalIncome : incomeList) {
						RankDTO rankDTO = new RankDTO();
						rankDTO.setRank(rank++);
						UserBaseInfoDTO user = userBaseInfoService.getByUserId(userTotalIncome.getUserId());
						if (user != null) {
							rankDTO.setUserId(user.getUserId());
							rankDTO.setHeadImg(user.getHeadImg());
							rankDTO.setNickname(user.getNickname());
							rankDTO.setYear(user.getYear());
						}
						rankDTO.setText(userTotalIncome.getIncome());
						list.add(rankDTO);
					}
				}
				redisService.set(key, JSON.toJSONString(list));
				redisService.expire(key, 1 * 60);
			}
		
		}
		return list;
	}
}
