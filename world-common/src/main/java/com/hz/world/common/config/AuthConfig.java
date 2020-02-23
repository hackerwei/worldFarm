package com.hz.world.common.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.hz.world.common.util.AppEnvUtil;

/**
 * 全局配置
 * 
 * <pre>
 * Class Name: Config.java
 * &#64;author administrator
 * Modifications:
 * Modifier administrator; 2017-07-17; Create new Class Config.java.
 * </pre>
 */
public class AuthConfig {
	
	/** accesstoken 的有效天数，token有效期90天 **/
	public static long ACCESSTOKEN_VALID_TIME = 90L * 24L * 3600L * 1000L;
	
	/** 临时存放的appid和key */
	@SuppressWarnings("serial")
	public static final ConcurrentHashMap<String, String> API_KEYS_MAP = new ConcurrentHashMap<String, String>() {

		{
			put("ytJ9drS5oG7fCjV0", "kUKEjqUNwqdCrfGB1aFCZBScrB0o7dgz");
		}
	};
	
	/** 临时存放的appid和可访问的路径，暂时不用 */
	@SuppressWarnings("serial")
	public static final ConcurrentHashMap<String, Set<String>> API_SCOPE_MAP = new ConcurrentHashMap<String, Set<String>>() {
		{
			put("ytJ9drS5oG7fCjV0", new HashSet<String>(Arrays.asList("")));
		}
	};
	
	/** 不需要登录可以访问的url */
	@SuppressWarnings("serial")
	public static final ConcurrentHashMap<String, Set<String>> NO_NEED_LOGIN_URL = new ConcurrentHashMap<String, Set<String>>(){
		{
			put("", new HashSet<String>(Arrays.asList("/api/bottle/","/api/user/","/api/account/login")));  
			
		}
	};
	
	/** 全局黑名单用户不能访问的url */
	@SuppressWarnings("serial")
	public static final ConcurrentHashMap<String, Set<String>> FORBIDDEN_URL = new ConcurrentHashMap<String, Set<String>>(){
		{
			put("", new HashSet<String>(Arrays.asList("/api/dynamic/create","/api/user/otherInfo")));  
			
		}
	};
    
    
	/** 不需要授权和登录，可以访问的url，如对外接口回调 */
	public static final Set<String> NOT_NEED_SIGNATURE_URL = new HashSet<String>(Arrays.asList("/api/oss/upload/callback"));
	
	/** 运营后台相关接口，只需要白名单 */
	public static final Set<String> CONCOLE_URL = new HashSet<String>(Arrays.asList("api/console/"));

	/** 客户端与服务端交互，最大时间间隔，防止恶意请求，正式环境600秒，其它60天 */
	public static long MAX_ALLOWED_TIME_GAP_BETWEEN_CLIENT_AND_SERVER = AppEnvUtil.isPrdEnv() ? (30L * 1000L) : (60L * 24L * 3600L * 1000L);
	
}
