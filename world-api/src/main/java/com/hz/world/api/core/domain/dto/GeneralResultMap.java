package com.hz.world.api.core.domain.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class GeneralResultMap extends HashMap<String, Object> implements Serializable {
	private static final long serialVersionUID = 3536613179626466634L;

	private final String MESSSAGE = "message";
	private final String CODE = "code";
	private final String DATA = "data";

	public GeneralResultMap() {
		super.put(MESSSAGE, "OK");
		super.put(CODE, 0);
		super.put(DATA, new HashMap<String, Object>());
	}

	public void setResult(SysReturnCode sysMsg) {
		setResult(sysMsg, null);
	}

	public void setResult(SysReturnCode sysMsg, String desc) {
		setResult(sysMsg, desc, null);
	}
	
	public void setResult(SysReturnCode sysMsg, Object data) {
		setResult(sysMsg, null, data);
	}

	public void setResult(SysReturnCode sysMsg, String desc, Object data) {
		if (sysMsg != null) {
			if (StringUtils.isNotEmpty(desc)) {
				setMessage(desc);
			} else {
				setMessage(sysMsg.getMsg4Cn());
			}
			setCode(sysMsg.getCode());
		}

		if (data != null) {
			setData(data);
		} else {
			if(!super.containsKey(DATA)){
				setData(new HashMap<String, Object>());
			}
		}
	}
	
	public void setResult(Integer code) {
		setResult(code, null, null);
	}
	
	public void setResult(Integer code, String message) {
		setResult(code, message, null);
	}
	
	public void setResult(Integer code, String message, Object data) {
		setCode(code);
		setMessage(message == null ? "" : message);
		setData(data == null ? new HashMap<String, Object>() : data);
	}
	
	public String getMessage() {
		return String.valueOf(super.get(MESSSAGE));
	}

	public void setMessage(String value) {
		super.put(MESSSAGE, value);
	}

	public String getCODE() {
		return CODE;
	}

	public void setCode(Integer code) {
		super.put(CODE, code);
	}

	public void setData(Object data) {
		super.put(DATA, data);
	}

	public Object getData() {
		return super.get(DATA);
	}
	
	public void setData(String dataName,Object data) {
		if(StringUtils.isNotEmpty(dataName)){
			HashMap<String,Object> dataMap = new HashMap<String,Object>();
			dataMap.put(dataName, data);
			super.put(DATA, dataMap);
		}else{
			super.put(DATA, data);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void putDataMap(String name,Object value) {
		if(super.containsKey(DATA) && super.get(DATA) instanceof Map){
			if(StringUtils.isNotEmpty(name)){
				HashMap<String,Object> dataMap = new HashMap<String,Object>();
				dataMap.put(name, value);
				((Map)super.get(DATA)).putAll(dataMap);
			}
		}else{
			setData(name,value);
		}
	}
}
