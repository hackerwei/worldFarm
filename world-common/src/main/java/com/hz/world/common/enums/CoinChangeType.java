package com.hz.world.common.enums;


/**  
* <p>Title: ResourceChangeType</p>  
* <p>Description:动态类型 </p>  
* @author linyanchun  
* @date 2018年8月22日  
*/  
public enum CoinChangeType {
    
    ADD(0, "收益递增"),
    UPGRADE_ELEMENT(1, "升级元素"),
    EXT_INCOME(2, "三小时收益"),
    OFFLINE_REWARD(3, "离线收益"),
    SHOP_REWARD(4, "商城收益"),
    INVEST(5, "投资"),
    FACTORY(6, "厂长"),
    OFFER(7, "祭神"),
    ;
    private final int code;
    private final String desc;

    CoinChangeType(int code, String desc) {
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
