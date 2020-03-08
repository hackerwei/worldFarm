package com.hz.world.common.enums;

/**
 * 消息dto
 * Title: 
 * Description: 
 * author linyanchun
 * date Mar 8, 2020 
 */
public enum MessageType {
    
    REQUIRE_COIN(1000, "催发金币"),
    RECV_COIN(1001, "收到金币"),
	;
	
	
    private final int code;
    private final String desc;

    MessageType(int code, String desc) {
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
