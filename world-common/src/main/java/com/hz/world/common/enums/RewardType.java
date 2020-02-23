package com.hz.world.common.enums;


/**  
* <p>Title: DynamicType</p>  
* <p>Description:动态类型 </p>  
* @author linyanchun  
* @date 2018年8月22日  
*/  
public enum RewardType {
    
    TASK_REWARD(0, "任务获取"),

	SIGN_REWARD(1, "签到奖励"),
	SYSTEM_REWARD(1, "系统奖励");
    private final int code;
    private final String desc;

    RewardType(int code, String desc) {
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
