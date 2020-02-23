/**
 * 
 */
package com.hz.world.common.util;

import com.hz.world.common.config.AuthConfig;
import com.hz.world.common.util.crypt.AESUtil;

/**
 * 关于用户的accessToken的加密
 * <pre>
 * Author: administrator
 * Modifications:
 * Modifier administrator;2017-07-17; Creat new mothed;
 * </pre>
 */
public class AccessToken {
	
	public static final String DELIMITER = "-";
	public static final String TOEKN_SECRET_KEY = "2FDwiPlbYTmK8SsC"; // token 生成的key
	public static final int INVALID_TOKEN = -1;//无用的key
	public static final int EXPIRED_TOKEN = -2;//失效的key
	public static final int UNMATCH_TOKEN = -3;//和uid不匹配
	public static final int EXIT_LOGIN = -4; // 用户退出登录
	public static final int REDUPLICATE_LOGIN = -5; // 用户重复登录
	public static final int TOKEN_OK = 0;//校验成功
	public static final int SYSTEN_ERROR = -10;//系统错误
	
	/**
	 * 加密token
	 * <pre>
	 * Author: administrator
	 * @param appId
	 * @param userId
	 * @return
	 * Modifications:
	 * Modifier administrator; 2017-07-17; Creat new mothed;
	 * </pre>
	 */
	public static String encrtyToken(String appId, String userId, Long createTime){
		StringBuilder secret = new StringBuilder(appId);
		secret.append(DELIMITER).append(userId);
		secret.append(DELIMITER).append(createTime);
		String secretStr = "";
		try {
			secretStr = AESUtil.encryptBaseECBWithPKCS5Padding(secret.toString(), TOEKN_SECRET_KEY, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return secretStr;
	}

	/**
	 * 解密token
	 * @param secret
	 * @return
	 */
	public static String decryptToken(String secret){
		String secretStr = "";
		try {
			secretStr = AESUtil.decryptBaseECBWithPKCS5Padding(secret.toString(), TOEKN_SECRET_KEY, "UTF-8");
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return secretStr;
		//secretStr = MD5.md5(secret.toString());
	}
	
	/**
	 * 校验accessToken解密
	 * <pre>
	 * Author: administrator
	 * @param inputToken
	 * @param userId
	 * @return
	 * Modifications:
	 * Modifier administrator; 2017-07-17; Creat new mothed;
	 * </pre>
	 */
	public static int decrtyToken(String inputToken, String userId, String loginToken) {
		try {
			if (null != inputToken) {
				String tokens[] = decryptToken(inputToken).split(DELIMITER);
				if (null != tokens && tokens.length == 3) {
					String appId = tokens[0]; // appId
					String uid = tokens[1]; // userId
					long createTime = Long.parseLong(tokens[2]); // createTime

					if (!userId.equals(uid)) {// accessToken和userId不匹配
						return UNMATCH_TOKEN;
					}

					long validTime = AuthConfig.ACCESSTOKEN_VALID_TIME;
					if (createTime + validTime < System.currentTimeMillis()) { // token是否过期
						return EXPIRED_TOKEN;
					}

					String token = encrtyToken(appId, userId, createTime);
					if (!inputToken.equals(token)) {// inputToken与token比较，token是否正常
						return INVALID_TOKEN;
					}

					if (!inputToken.equals(loginToken)) {// token已经变更，帐号可能在另外设备登录
						return REDUPLICATE_LOGIN;
					}

					return TOKEN_OK;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return SYSTEN_ERROR;
		}
		return INVALID_TOKEN;
	}
	 
	public static void main(String args[]) {
		Long createTime = System.currentTimeMillis();
		String appId = "ytJ9drS5oG7fCjV0";
		String userId = "100010";
		String token = encrtyToken(appId, userId, createTime);
		
		System.out.println("加密：appId:" + appId + ",userId:" + userId + ",token:" + token);
		System.out.println("解密："+ decryptToken(token));
	}
	
}
