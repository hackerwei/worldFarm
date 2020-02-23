package com.hz.world.account.common.enums;

public enum FlopType {
	
	
	VISITOR(0, "访问我的人"), 
	LIKE_USER(1, "喜欢我的人"); 
	
	private Integer code;
	private String source;
	
	
	
	private FlopType(int code, String source){
		this.code = code;
		this.source = source;
	}
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
}
