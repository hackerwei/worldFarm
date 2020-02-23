package com.hz.world.api.core.domain.dto;

/**
 * 系统返回码，请一定按照以下规则定义
 * 1) code = 0: 正确返回。可以从api返回的对应字段中取数据。
 * 2) code > 0: 调用API时发生错误，需要开发者进行相应的处理。
 * 3) -50 < code <= -1: 权限验证失败，需要开发者进行相应的处理。
 * 4) code <= -50: 系统内部错误，请联系技术支持，调查问题原因并获得解决方案。
 * <pre>
 * Class Name: SysReturnCode.java
 * @author mengxianhua
 * Modifications:
 * Modifier mengxianhua; 2017年8月10日; Create new Class SysReturnCode.java.
 * </pre>
 */
public enum SysReturnCode {
	
	/**
	 * 成功
	 */
	SUCC(0,"SYS0","正常","success"),
	/**
	 * 失败
	 */
	FAIL(1,"SYS1","失败","failed"),
	
	/**
	 * 警告
	 */
	WARN(2,"SYS2","提示","warn"),
	
	/**
	 * 非法请求
	 */
	ILLEGA_REQ(3,"SYS3","非法请求","Illega request"),
	
	/**
	 * 过于频繁请求
	 */
	FREQUENTLY_REQ(4,"SYS4","过于频繁请求","Frequently request"),
	
	/**
	 * 缺少参数
	 */
	LACK_PARAMS(5,"SYS5","缺少参数","Lack Paramter"),
	
	/**
	 * 缺少签名
	 */
	LACK_SIGN(6,"SYS6","缺少签名","Lack Signature"),
	
	/**
	 * 参数不匹配
	 */
	UNMATCH_PARAMS(7,"SYS7","参数不匹配","Unmatch Paramter"),
	
	/**
	 * 签名不匹配
	 */
	UNMATCH_SIGN(8,"SYS8","签名不匹配","Unmatch Signature"),
	
	/**
	 * 无效的参数
	 */
	INVALID_PARAMS(9,"SYS9","无效的参数","Invalid Paramter"),
	
	
	/**
	 * 金币不足
	 */
	COIN_NOT_ENOUQH(10,"SYS10","金币不足","Coin not enough"),
	
	/**
	 * 礼物不足
	 */
	GIFT_NOT_ENOUQH(11,"SYS11","礼物不足","Gift not enough"),
	
	/**
	 * 黑名单用户
	 */
	BLACK_USER(12,"SYS12","黑名单用户","Black user"),
	
	/**
	 * 没有微信号
	 */
	NO_WEIXIN(13,"SYS13","没有微信号","No weixin"),
	
	/**
	 * 没有微信号
	 */
	ILLEGAL_INGFO(14,"SYS14","违规信息","ILLEGAL_INGFO"),
	
	/**
	 * 余额不足
	 */
	BALANCE_NOT_ENOUGH(15,"SYS15","余额不足","BALANCE_NOT_ENOUGH"),
	
	
	/**
	 * 未授权
	 */
    UNAUTHORIZED(-1,"AUTH-1","未授权","Unauthorized"),
	
    /**
     * 禁止访问
     */
	FORBIDDEN(-2,"AUTH-2","禁止访问","Forbidden"),
	
	/**
	 * 方法不存在
	 */
	METHOD_NOT_FOUND(-3,"AUTH-3","方法不存在","Method not found"),
	
	/**
	 * 方法不可访问
	 */
	METHOD_NOT_ALLOWED(-4,"AUTH-4","方法不可访问","Method not allowed"),
	
	/**
	 * 需要认证
	 */
	AUTHENTICATION_REQUIRED(-5,"AUTH-5","需要认证","Authentication required"),
	
	/**
	 * 请求底层服务超时
	 */
	REQUEST_TIMEOUT(-6,"AUTH-6","请求底层服务超时","Request timeout"),
	
	/**
	 * 用户不存在
	 */
	UNKNOW_USER(-7,"AUTH-7","用户不存在","Unknow User"),
	
	/**
	 * 用户不可用
	 */
	USER_UNUSABLE(-8,"AUTH-8","用户不可用","User unusable"),
	
	/**
	 * 无效的security值
	 */
	INVALID_SECURITY_CODE(-9,"AUTH-9","无效的security值","Invalid security code"),
	
	/**
	 * 服务端和客户端连接超时
	 */
	TOO_MUCH_TIME_GAP(-10,"AUTH-10","服务端和客户端连接超时","APP Connection timeout"),
	
	/**
	 * security值不匹配
	 */
	SECURITY_CODE_MISMATCH(-11,"AUTH-11","security值不匹配","Security code mismatch"),
	
	/**
	 * uuid 没有设置
	 */
	INVALID_UUID_CODE(-12,"AUTH-12","uuid 没有设置 ","Invalid UUID code"),

	/**
	 * 特殊定义的，给注册登录token校验使用的
	 */
	LOGIN_TOKEN_ERROR (-13, "AUTH-13","Token无效，请重新登录","AccessToken ERROR"),
	
	/**
	 * 用户已退出登录，需要重新登录
	 */
	USER_EXIT_LOGIN (-14, "AUTH-14","用户已退出登录，需要重新登录","User already exit login"),
	
	/**
	 * 系统内部错误
	 */
	SYSTEM_ERROR(-50,"SYS-50","系统内部错误","System Error");
	
	
	private int code;
	private String number;
	private String msg4Cn;
	private String msg4En;
	
	private SysReturnCode(int code,String number,String msg4Cn,String msg4En){
		this.code = code;
		this.number = number;
		this.msg4Cn = msg4Cn;
		this.msg4En = msg4En;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getMsg4Cn() {
		return msg4Cn;
	}

	public void setMsg4Cn(String msg4Cn) {
		this.msg4Cn = msg4Cn;
	}

	public String getMsg4En() {
		return msg4En;
	}

	public void setMsg4En(String msg4En) {
		this.msg4En = msg4En;
	}
}
