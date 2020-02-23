package com.hz.world.common.enums;

public enum ItemTypeEnum {

	 	ITEM(2, "道具"),
	    DRESS_ITEM(1, "装扮碎片");

	    private final int code;
	    private final String desc;

	    ItemTypeEnum(int code, String desc) {
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
