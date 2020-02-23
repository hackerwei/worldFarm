package com.hz.world.common.enums;


/**  
* <p>Title: TaskCode</p>  
* <p>Description:任务类型 </p>  
* @author linyanchun  
* @date 2018年8月22日  
*/  
public enum TaskCode {
    
	DRIFTER_DAY_REWARD("DRIFTER_DAY_REWARD", "漂流瓶每日奖励"),
	DRIFTER_WEEK_REWARD("DRIFTER_WEEK_REWARD", "漂流瓶每周奖励"),
	DRIFTER_MONTH_REWARD("DRIFTER_MONTH_REWARD", "漂流瓶每月奖励"),
	DRIFTER_DAY_REWARD_ONE("DRIFTER_DAY_REWARD_ONE", "漂流瓶每日1级奖励"),
	DRIFTER_DAY_REWARD_TWO("DRIFTER_DAY_REWARD_TWO", "漂流瓶每日2级奖励"),
	DRIFTER_DAY_REWARD_THREE("DRIFTER_DAY_REWARD_THREE", "漂流瓶每日3级奖励"),
	EVERY_DAY_TASK("EVERY_DAY_TASK", "每日任务"),
	EVERY_DAY_PROCESS_REWARD("EVERY_DAY_PROCESS_REWARD", "每日任务进度奖励"),
	INVITE_USER("INVITE_USER", "邀请好友成就"),
	GUESS_RICH("GUESS_RICH", "尿尿猜中土豪成就"),
	BE_PEED("BE_PEED", "被尿成就"),
	BREAK_HOME("BREAK_HOME", "捣乱成就"),
	GACHA("GACHA", "扭蛋成就"),
	BE_DESTROYED("BE_DESTROYED", "被破坏成就"),
	COSPLAY("COSPLAY", "时装成就"),
	TRAVEL("TRAVEL", "旅行成就"),
	SHARE("SHARE", "分享成就"),
	USER_LEVEL("USER_LEVEL", "猫爪成就"),
	
	GET_COIN("GET_COIN", "赚取金币成就"),
	AD("AD", "观看广告成就"),
	FEED("FEED", "喂食成就"),
	ENTERTAINMENT("ENTERTAINMENT", "娱乐成就"),
	CLEAN("CLEAN", "打扫成就"),
	PEE("PEE", "拉屎、尿尿成就"),
	SHOWER("SHOWER", "洗澡成就");
	
	
	
    private final String code;
    private final String desc;

    TaskCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

   
}
