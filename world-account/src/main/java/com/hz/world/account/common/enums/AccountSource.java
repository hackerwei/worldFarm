package com.hz.world.account.common.enums;

public enum AccountSource {
	
	
	WECHAT(0, "wechatkey"), // 微信
	QQ(1, "qqkey"),// QQ
	AUTO(2, "auto"); // 自动登录
	private Integer code;
	private String source;
	
	
	/**
	 * 
	 * @param code   代码，每次替增
	 * @param source 帐号来源的第三方（如万能钥匙）
	 */
	private AccountSource(int code, String source){
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
