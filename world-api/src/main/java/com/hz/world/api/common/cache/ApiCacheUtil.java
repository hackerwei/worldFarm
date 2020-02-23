package com.hz.world.api.common.cache;

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

   
  
   
   
   

   
}
