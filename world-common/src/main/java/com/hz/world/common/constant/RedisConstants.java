package com.hz.world.common.constant;

public final class RedisConstants {
	
	/** =========用户相关==================*/
	/** 用户基本信息，%s为userId,值为 UserBaseInfoDTO的hash数据 */
	public static final String RICHER_USER_BASE = "richer:user:base:%s";
	/** 记录用户金币*/
	public static final String RICHER_USER_COIN = "richer:user:coin:%s";
	/** 记录用户累计收益*/
	public static final String RICHER_USER_INCOME_COIN = "richer:user:income_coin:%s";
	/** 用户邀请码*/
	public static final String RICHER_USER_NO = "richer:user:no:%s";
	/** 记录用户登录token*/
	public static final String RICHER_USER_LOGIN_TOKEN = "richer:user:login:token:%s";
	
	/** =========配置相关==================*/
	public static final String RICHER_CONFIG_ELEMENT = "richer:config:element";
	public static final String RICHER_CONFIG_TITLE = "richer:config:title";
	
	/** =========业务相关==================*/
	public static final String RICHER_USER_ELEMENT = "richer:user:element:%s:%s";
}
