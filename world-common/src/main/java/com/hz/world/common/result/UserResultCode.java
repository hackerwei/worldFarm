package com.hz.world.common.result;

public enum UserResultCode {
	NICKNAME_TOO_LONG(1000, "昵称太长"),
	SIGNATURE_TOO_LONG(1001, "签名太长"),
	NICKNAME_SENSITIVE_WORD(1002, "昵称包含敏感词"),
	SIGNATURE_SENSITIVE_WORD(1003, "签名包含敏感词"),
	NICKNAME_EXIST(1004, "该昵称已被使用");
	
	private UserResultCode(final Integer code, final String message) {
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