package com.hz.world.common.enums;

/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 25, 2020 
 */
public enum CashChangeType {
    
    RECHARGE(0, "充值"),
    LIMIT_SHARE(1, "限时分红小龙虾"),
    FOREVER_SHARE(2, "永久分红小龙虾"),
    UNION(3, "联盟收益"),
    CASH(4, "提现"),
    BACK(5, "退款"),
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
