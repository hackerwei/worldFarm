package com.hz.world.common.dto;

import java.io.Serializable;

import org.springframework.util.Assert;

import com.hz.world.common.util.EnumUtils;

/**
 * 通用业务返回信息
 * 
 * @author <a href="mailto:jiangq@hiwitech.com">JiangQiang</a>
 * @version 1.0
 */
public class ResultDTO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean success; // 是否成功

    private String errCode; // 错误代码

    private String errDesc; // 错误描述

    private T result; // 结果信息

    /**
     * 通用set方法
     * 
     * @param code 编码
     * @param msg   描述
     * @return
     */
    public ResultDTO<T> set(String code, String msg) {

        return set(code, msg, null);
    }
    
    /**
     * 通用set方法
     * @param code  编码
     * @param msg   描述
     * @param result    结果
     * @return
     * @throws Exception
     */
    public ResultDTO<T> set(String code, String msg, T result) {
        ResultCodeEnum codeEnum = EnumUtils.getEnum(ResultCodeEnum.values(), "code", code);
        Assert.notNull(codeEnum);

        this.success = codeEnum.isSuccess();
        this.errCode = codeEnum.getCode();
        this.errDesc = msg;
        this.result = result;

        return this;
    }

    /**
     * 通用set方法
     * 
     * @param resultEnum
     *            结果枚举
     * @param msg
     *            描述信息
     * @return
     * @throws Exception
     */
    public ResultDTO<T> set(ResultCodeEnum resultEnum, String msg) {

        return set(resultEnum, msg, null);
    }

    /**
     * 通用set方法
     * 
     * @param resultEnum
     *            结果枚举
     * @param msg
     *            描述信息
     * @param result
     *            返回结果
     * @return
     * @throws Exception
     */
    public ResultDTO<T> set(ResultCodeEnum resultEnum, String msg, T result) {
        Assert.notNull(resultEnum);

        this.success = resultEnum.isSuccess();
        this.errCode = resultEnum.getCode();
        this.errDesc = msg;
        this.result = result;

        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrDesc() {
        return errDesc;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public void setErrDesc(String errDesc) {
        this.errDesc = errDesc;
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("[success:" + success + ",errCode:").append(this.errCode);
        buf.append(",errDesc:").append(this.errDesc).append("],result:").append(this.result.toString());
        return buf.toString();
    }

}
