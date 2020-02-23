package com.hz.world.common.enums;

public enum AchievementCodeEnum {
	INVITE_USER(1300100, "INVITE_USER","邀请好友成就"),
	GUESS_RICH(1300200, "GUESS_RICH","尿尿猜中土豪成就"),
	BE_PEED(1300300, "BE_PEED","被尿成就"),
	BREAK_HOME(1300400, "BREAK_HOME","捣乱成就"),
	GACHA(1300500, "GACHA","扭蛋成就"),
	BE_DESTROYED(1300600, "BE_DESTROYED","被破坏成就"),
	COSPLAY(1300700, "COSPLAY","时装成就"),
	TRAVEL(1300800, "TRAVEL","旅行成就"),
	SHARE(1300900, "SHARE","分享成就"),
	USER_LEVEL(1301000, "USER_LEVEL","猫爪成就"),
	GET_COIN(1301100, "GET_COIN","赚取金币成就"),
	AD(1301200, "AD","观看广告一次"),
	FEED(1301300, "FEED","喂食成就"),
	ENTERTAINMENT(1301400, "ENTERTAINMENT","娱乐成就"),
	CLEAN(1301500, "CLEAN","打扫成就"),
	PEE(1301600, "PEE","拉屎、尿尿成就"),
	SHOWER(1301700, "SHOWER","洗澡成就");

    AchievementCodeEnum(final int code, final String taskCode, final String desc) {
        this.code = code;
        this.taskCode = taskCode;
        this.desc = desc;
    }

    private int code;
    private String taskCode;
    private String desc;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getTaskCode() {
		return taskCode;
	}
	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

    
	

}
