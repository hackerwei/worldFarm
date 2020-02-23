package com.hz.world.account.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hz.world.common.util.HttpClientUtils;
import com.hz.world.common.util.JsonUtil;

import lombok.extern.slf4j.Slf4j;


/**
 * 微信登录SDK，服务端交互

 */
@Slf4j
@Service
public class LoginSdkApiServiceImpl {


    /**
     * 调用微信获取getAccessToken接口，获取AccessToken
     * </pre>
     */
    public Map<String, Object> getAccessToken(String appId, String appKey, String loginUrl, String code) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        try {
            Map<String, String> paramsMap = new HashMap<String, String>();
 
            paramsMap.put("appid", appId); // 应用ID
            paramsMap.put("secret", appKey); // app secret
            paramsMap.put("js_code", code); // 授权码
            paramsMap.put("grant_type", "authorization_code"); // 类型
            
            resultMap = JsonUtil.jsonStringToMap(HttpClientUtils.get(loginUrl, paramsMap));
            System.out.println(resultMap);
        } catch (Exception e) {
            log.warn("getAccessToken,Exception,code:{},resultMap:{}", code, resultMap);
            e.printStackTrace();
        }

        log.info("getAccessToken,code:{},resultMap:{}", code, resultMap);
        return resultMap;
    }

   
}

