package com.hz.world.common.result;

public enum AppVersionCheckCode {
	VERSION_CODE_ERROR(1000, "版本号参数格式有误"),
	NO_UPDATE_VERSION(1001, "当前是最新版本");
	
	private AppVersionCheckCode(final Integer code, final String message) {
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