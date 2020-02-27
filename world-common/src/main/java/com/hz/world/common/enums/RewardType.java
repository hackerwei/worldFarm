package com.hz.world.common.enums;

/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 27, 2020 
 */
public enum RewardType {
    
    TASK_REWARD(0, "任务获取"),

    ;
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
