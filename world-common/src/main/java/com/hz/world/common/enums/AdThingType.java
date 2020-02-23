package com.hz.world.common.enums;


/**  
* <p>Title: DynamicType</p>  
* <p>Description:动态类型 </p>  
* @author linyanchun  
* @date 2018年8月22日  
*/  
public enum AdThingType {
    
    DICE_COIN(1, "筛子金币事件");
    private final int code;
    private final String desc;

    AdThingType(int code, String desc) {
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
