package com.hz.world.api.common.util;

import java.util.UUID;

import com.hz.world.api.common.support.AppSignature;
import com.hz.world.common.util.AccessToken;

/**
 * 生成请求header头部参数，appId和userId要确保正确
 * @author mengxianhua
 * Modifier mengxianhua; 2017年9月13日; Create new Method SecurityAndToken
 */
public class SecurityAndToken {
	
	public static void main(String[] args) {
		AppSignature appSignature = null;
		String appId = "ytJ9drS5oG7fCjV0"; // appId
		String userId = "100016"; // userId
		
		long random = System.currentTimeMillis();
		
		try {
			appSignature = AppSignature.generate(appId, random);
			System.out.println("X-Security:" + appSignature.getAppId() + ":" + random + ":" + appSignature.getCheckSum());
			System.out.println("X-UUID:" + UUID.randomUUID());
			
			System.out.println("X-User-ID:" + userId);
			String token = AccessToken.encrtyToken(appId, userId, random);
			System.out.println("X-Token:" + token);
			
			System.out.println("Content-Type:application/json");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
