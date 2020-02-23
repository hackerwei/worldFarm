package com.hz.world.core.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @outhor lujian
 * @create 2018-06-11 16:30
 */
@Configuration
public class CoreConfig {
    @Value("${core.wechart.loginUrl}")
    private String WechatLoginUrl; // 微信登录正式环境url

    @Value("${core.wechart.appId}")
    private String WechatAppId; // 微信登录appId

    @Value("${core.wechart.appKey}")
    private String WechatLoginKey; // 微信登录appKey

	public String getWechatLoginUrl() {
		return WechatLoginUrl;
	}

	public void setWechatLoginUrl(String wechatLoginUrl) {
		WechatLoginUrl = wechatLoginUrl;
	}

	public String getWechatAppId() {
		return WechatAppId;
	}

	public void setWechatAppId(String wechatAppId) {
		WechatAppId = wechatAppId;
	}

	public String getWechatLoginKey() {
		return WechatLoginKey;
	}

	public void setWechatLoginKey(String wechatLoginKey) {
		WechatLoginKey = wechatLoginKey;
	}

  
}
