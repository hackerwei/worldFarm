package com.hz.world.common.enums;


/**  
* <p>Title: ResourceChangeType</p>  
* <p>Description:动态类型 </p>  
* @author linyanchun  
* @date 2018年8月22日  
*/  
public enum DiamondChangeType {
    
    RECHARGE(0, "充值"),
    SHOP(1, "商场"),
    INVITE(2, "邀请"),
    FORTUNE(3, "福袋"),
    TASK(4, "任务"),
    EXPRESS(5, "外卖"),
    WORSHIP(6, "祭神"),
    ;
    private final int code;
    private final String desc;

    DiamondChangeType(int code, String desc) {
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
