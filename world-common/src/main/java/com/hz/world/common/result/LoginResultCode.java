package com.hz.world.common.result;

public enum LoginResultCode {

	INTERFACE_CALL_FAILED(1000, "调用钥匙登录接口失败"), 
	F_ACCOUNT(1001, "帐号被封"),
	LOGIN_EXCEPTION(1002, "帐号登录异常");
	
	private LoginResultCode(final Integer code, final String message) {
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
