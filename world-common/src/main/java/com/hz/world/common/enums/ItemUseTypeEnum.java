package com.hz.world.common.enums;

public enum ItemUseTypeEnum {

	 	FOOD(301, "食物"),
	    COSPLAY(601, "装扮"),
	    ENTERTIMENT(901, "娱乐"),
	    POSTCARD(1101, "明信片");

	    private final int code;
	    private final String desc;

	    ItemUseTypeEnum(int code, String desc) {
	        this.code = code;
	        this.desc = desc;
	    }

	    public int getCode() {
	        return code;
	    }

	    public String getDesc() {
	        return desc;
	    }

	  
}
