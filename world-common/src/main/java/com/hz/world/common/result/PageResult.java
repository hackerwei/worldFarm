package com.hz.world.common.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 服务之间调用返回分页对象
 */
public class PageResult<E> implements Serializable {
	private static final long serialVersionUID = 1L;

	private boolean success;
	private Integer total;// 总条数
	private String message;
	private Integer code;
	private List<E> list;// 数据列表

	public PageResult<E> success() {
		this.success = true;
		this.code = BaseErrorCode.SUCCESS_CODE;
		this.message = BaseErrorCode.SUCCESS_MESSAGE;
		return this;
	}

	public PageResult<E> success(List<E> list) {
		this.success = true;
		this.code = BaseErrorCode.SUCCESS_CODE;
		this.message = BaseErrorCode.SUCCESS_MESSAGE;
		this.list = list == null ? new ArrayList<E>() : list;
		return this;
	}

	public PageResult<E> fail() {
		this.success = false;
		this.code = BaseErrorCode.FAIL_CODE;
		this.message = BaseErrorCode.FAIL_MESSAGE;
		return this;
	}

	public PageResult<E> fail(String message) {
		this.success = false;
		this.code = BaseErrorCode.FAIL_CODE;
		this.message = message;
		return this;
	}

	public PageResult<E> fail(Integer code, String message) {
		this.success = false;
		this.code = code;
		this.message = message;
		return this;
	}

	public List<E> getlist() {
		return list;
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

	public void setlist(List<E> list) {
		this.list = list;
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
		return "PageResult [success=" + success + ", total=" + total + ", message=" + message
				+ ", code=" + code + ", list=" + list + "]";
	}
}
