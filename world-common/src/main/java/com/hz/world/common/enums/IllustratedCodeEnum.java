package com.hz.world.common.enums;

public enum IllustratedCodeEnum {
	DRESS_REWARD(1400100, "DRESS_REWARD","累积装扮道具奖励"),
	CARD_REWARD(1400200, "CARD_REWARD","累积明星片奖励");

    IllustratedCodeEnum(final int code, final String taskCode, final String desc) {
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
