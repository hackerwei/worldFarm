package com.hz.world.common.enums;

/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 25, 2020 
 */
public enum CashChangeType {
    
    RECHARGE(0, "充值"),
    
    ;
    private final int code;
    private final String desc;

    CashChangeType(int code, String desc) {
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
