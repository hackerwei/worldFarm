
package com.hz.world.common.dto;

/**
 * 通用业务返回结果编码Code枚举
 * @author <a href="mailto:liqg@hiwitech.com">liqiangen</a>
 * @version 1.0
 */
public enum ResultCodeEnum {
    
    /**
     * 成功
     */
    SUCCESS(true, "10000", "业务处理成功"),
    
    /**
     * 服务不可用（未知错误）
     */
    ERROR_UNKNOW(false, "20000", "服务不可用"),
    
    /**
     * 鉴权失败
     */
    ERROR_AUTH(false, "20001", "鉴权失败"),
    
    /**
     * 访问授权过期
     */
    ERROR_AUTH_TIME_OUT(false, "20002", "访问授权过期"),
    
    /**
     * 请求频繁
     */
    ERROR_REQ_OFTEN(false, "20003", "请求频繁"),
    
    /**
     * 缺少必须的参数
     */
    ERROR_MISSING_PARAMS(false, "40001", "缺少必须的参数"),
    
    /**
     * 非法参数
     */
    ERROR_INVALID_PARAMS(false, "40002", "非法参数"),
    
    /**
     * 业务处理失败
     */
    ERROR_HANDLE(false, "40004", "业务处理失败"),
    
    /**
     * 权限不足
     */
    ERROR_NOT_ALLOW(false, "40006", "权限不足"),
	
	NOT_ENOUGH_GOLD(false, "50000", "金币不足"),
	NOT_ENOUGH_DIAMOND(false, "50001", "勾玉不足");
    
    private boolean success; // 结果

    private String code;   // 代码

    private String msg; // 描述
    
    private ResultCodeEnum(boolean success, String code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }
    
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
