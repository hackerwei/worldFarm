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
	/** 记录用户在线的时间*/
	public static final String RICHER_USER_ONLINE_TIME = "richer:user:online_time:%s";
	/** =========配置相关==================*/
	public static final String RICHER_CONFIG_ELEMENT = "richer:config:element";
	public static final String RICHER_CONFIG_TITLE = "richer:config:title";
	public static final String RICHER_CONFIG_CATCH = "richer:config:catch";
	public static final String RICHER_CONFIG_RECHARGE = "richer:config:recharge";
	public static final String RICHER_CONFIG_YEAR = "richer:config:year";
	public static final String RICHER_CONFIG_SHOP = "richer:config:shop";
	public static final String RICHER_CONFIG_CHALLENGE = "richer:config:challenge";
	public static final String RICHER_CONFIG_COLLECT = "richer:config:collect";
	public static final String RICHER_CONFIG_UNION = "richer:config:union";
	/** =========业务相关==================*/
	public static final String RICHER_USER_ELEMENT = "richer:user:element:%s:%s";
	/** 用户3小时收益*/
	public static final String RICHER_USER_TMP_INCOME = "richer:user:tmp_income:%s";
	/** 离线奖励*/
	public static final String RICHER_USER_OFFLINE_REWARD = "richer:user:offline_reward:%s";
	/** 排行榜*/
	public static final String RICHER_USER_RANKING = "richer:user:ranking:%s";
	/** 有分红小龙虾的用户集合*/
	public static final String RICHER_USER_CATCH_FOREVER_SET = "richer:user:catch:forever_set";
	/** 有小龙虾的用户集合，排序用*/
	public static final String RICHER_USER_CATCH_SET = "richer:user:catch:set";
	/** 排行榜*/
	public static final String RICHER_RANKING_LIST = "richer:ranking_list:%s";
	/** 用户全局增益*/
	public static final String RICHER_USER_TOTAL_ADD = "richer:user:total_add:%s";
	/** 用户当日福利是否领取*/
	public static final String USER_FORTUNE_TODAY = "richer:user:fortune_today:%s";
	/** 外卖次数*/
	public static final String USER_TAKE_OUT = "richer:user:take_out:%s";
	/** 快递倒计时*/
	public static final String USER_EXPRESS = "richer:user:express:%s";
	/** 上次拉取的最后一个id*/
	public static final String LAST_INVEST_ID = "richer:user:last_invest_id:%s";
	/** 用户广告数*/
	public static final String USER_AD = "richer:user:ad:%s";
	/** 用户当日广告数*/
	public static final String USER_TODAY_AD = "richer:user:today_ad:%s";
}
