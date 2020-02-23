package com.hz.world.common.dto;

import java.io.Serializable;

import com.hz.world.common.result.BaseErrorCode;

/**
 * @outhor lujian
 * @create 2018-06-03 0:04
 */
public class PageResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean success = true;
    private int total;// 总条数
    private String message;
    private int code;

    public void success() {
        this.code = BaseErrorCode.SUCCESS_CODE;
        this.message = BaseErrorCode.SUCCESS_MESSAGE;
    }

    public void fail() {
        this.success = false;
        this.code = BaseErrorCode.FAIL_CODE;
        this.message = BaseErrorCode.FAIL_MESSAGE;
    }

    public void fail(String message) {
        this.success = false;
        this.code = BaseErrorCode.FAIL_CODE;
        this.message = message;
    }

    public void fail(Integer code, String message) {
        this.success = false;
        this.code = code;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PageResult [success=" + success + ", total=" + total + ", message=" + message + ", code=" + code + "]";
    }
}