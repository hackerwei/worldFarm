package com.hz.world.account.common.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.hz.world.account.dao.impl.UserBaseInfoDaoImpl;
import com.hz.world.account.dao.model.UserBaseInfo;
import com.hz.world.account.domain.dto.UserBaseInfoDTO;
import com.hz.world.common.cache.redis.RedisService;
import com.hz.world.common.config.AuthConfig;
import com.hz.world.common.constant.RedisConstants;
import com.hz.world.common.util.BeanUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AccountCacheUtil {

    @Autowired
    RedisService redisService;

    @Autowired
    UserBaseInfoDaoImpl userBaseInfoDao;

    
    public UserBaseInfoDTO getUserBaseInfo(Long userId) {
        UserBaseInfoDTO userBaseInfoDTO = null;

        try {
            String key = String.format(RedisConstants.RICHER_USER_BASE, userId);
            if (redisService.exists(key)) {
            		userBaseInfoDTO = JSON.parseObject( redisService.get(key).toString(), UserBaseInfoDTO.class);
            		return userBaseInfoDTO;
        
            } else {//缓存中无用户信息时
                UserBaseInfo userBaseInfo = userBaseInfoDao.findByUserId(userId);
                if (userBaseInfo != null) {
                	userBaseInfoDTO = new UserBaseInfoDTO();
            
                    BeanUtils.copyProperties(userBaseInfo, userBaseInfoDTO);            
                    redisService.set(key, JSON.toJSONString(userBaseInfoDTO));
                    log.info("无缓存获取用户{}基本信息,{}", userId, userBaseInfoDTO);
                }
            }
        } catch (Exception e) {
            log.error("获取用户{}基本信息失败,{}", userId, e);
        }
        return userBaseInfoDTO;
    }
    public boolean removeUserBaseInfo(Long userId) {
    		String key = String.format(RedisConstants.RICHER_USER_BASE, userId);
    		return redisService.del(key) > 0;
    }

   
    
    public void deleteUserBaeInfo(long userId) {
    		String key = String.format(RedisConstants.RICHER_USER_BASE, userId);
        redisService.del(key);
    }

    
    public String setUserLoginToken(Long userId, String loginToken) {
        String key = String.format(RedisConstants.RICHER_USER_LOGIN_TOKEN, userId);
        return redisService.setex(key, (int) (AuthConfig.ACCESSTOKEN_VALID_TIME / 1000L), loginToken);
    }

    public String getLoginToken(Long userId) {
    	String key = String.format(RedisConstants.RICHER_USER_LOGIN_TOKEN, userId);
    	return redisService.get(key);
    }
    
    
}
