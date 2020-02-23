package com.hz.world.api.common.cache;

import org.codehaus.groovy.tools.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hz.world.common.cache.redis.RedisService;
import com.hz.world.common.config.AuthConfig;
import com.hz.world.common.constant.RedisConstants;

@Component
public class ApiCacheUtil {

	@Autowired
	RedisService redisService;

	/**
	 * 取用户登录token
	 * 
	 * @param userId
	 * @return
	 */
	public String getUserLoginToken(Long userId) {
		String key = String.format(RedisConstants.RICHER_USER_LOGIN_TOKEN, userId);
		return redisService.get(key);
	}

	public String setUserLoginToken(Long userId, String loginToken) {
		String key = String.format(RedisConstants.RICHER_USER_LOGIN_TOKEN, userId);
		return redisService.setex(key, (int) (AuthConfig.ACCESSTOKEN_VALID_TIME / 1000L), loginToken);
	}

	/**
	 * @param userId
	 * @return
	 */
	public Long delUserLoginToken(Long userId) {
		String key = String.format(RedisConstants.RICHER_USER_LOGIN_TOKEN, userId);
		return redisService.del(key);
	}

	/**
	 * @param userId
	 */
	public void deleteUserBaeInfo(long userId) {
		String key = String.format(RedisConstants.RICHER_USER_BASE, userId);
		redisService.del(key);
	}

	/**
	 * 记录用户最近在线时间
	 * 
	 * @param userId
	 * @param time
	 */
	public void setUserOnline(long userId, long time) {
		String key = String.format(RedisConstants.RICHER_USER_ONLINE_TIME, userId);
		redisService.set(key, time + "");
	}

	/**
	 * 获取用户最近在线时间
	 * 
	 * @param userId
	 * @return
	 */
	public String getUserOnline(long userId) {
		String key = String.format(RedisConstants.RICHER_USER_ONLINE_TIME, userId);
		return redisService.get(key);
	}

	/**
	 * 设置离线奖励
	 * @param userId
	 * @param reward
	 */
	public void setOfflineReward(Long userId, String reward) {
		String key = String.format(RedisConstants.RICHER_USER_OFFLINE_REWARD, userId);
		redisService.set(key, reward);
	}

	/**
	 * 获取离线奖励
	 * @param userId
	 * @return
	 */
	public String getUserOfflineReward(long userId) {
		String key = String.format(RedisConstants.RICHER_USER_OFFLINE_REWARD, userId);
		return redisService.get(key);
	}
	
	/**
	 * 清除离线奖励
	 * @param userId
	 */
	public void clearOfflineReward(long userId) {
		String key = String.format(RedisConstants.RICHER_USER_OFFLINE_REWARD, userId);
		redisService.del(key);
	}

}
