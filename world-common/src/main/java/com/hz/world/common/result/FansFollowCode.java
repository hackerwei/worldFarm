package com.hz.world.common.result;

public enum FansFollowCode {
	HAVE_FOLLOWED(1000, "已关注过"), 
	FREQUENT_FOLLOW(1001, "频繁关注"),
	SELF_FOLLOW(1002, "不能关注自己"),
	BLACK_USER(1003, "已被拉入黑名单"),;
	
	private FansFollowCode(final Integer code, final String message) {
		this.code = code;
		this.message = message;
	}
	
	private int code;
	private String message;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}