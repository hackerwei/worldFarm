package com.hz.world.common.enums;

public enum AchievementCodeEnum {
	INVITE_USER(1300100, "INVITE_USER","邀请好友成就"),
	INVITE_TARGET(1300200, "INVITE_TARGET","小龙虾超过100斤"),
	;

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
