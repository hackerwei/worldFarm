package com.hz.world.common.enums;


/**  
* <p>Title: DynamicType</p>  
* <p>Description:动态类型 </p>  
* @author linyanchun  
* @date 2018年8月22日  
*/  
public enum TaskStatus {
    
    UN_FINISHED(0, "未完成"),
    FINISHED(1, "已完成"),
	REWARDED(2, "已领取奖励");
    private final int code;
    private final String desc;

    TaskStatus(int code, String desc) {
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
