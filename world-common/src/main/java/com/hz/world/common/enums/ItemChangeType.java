package com.hz.world.common.enums;


/**  
* <p>Title: ResourceChangeType</p>  
* <p>Description:动态类型 </p>  
* @author linyanchun  
* @date 2018年8月22日  
*/  
public enum ItemChangeType {
    
    REWARD(0, "任务获取"),
    CONSUME(1, "消费"),
	COMPOUND_GET(2, "合成获得"),
	COMPOUND_CONSUME(3, "合成消费"),
	SEND_FRIEND(4, "赠送好友"),
	RECV_FRIEND(5, "好友赠送"),
	ITEM_REWARD(6, "道具获取"),
	FEED_CAT(7, "喂养宠物");
	
	
    private final int code;
    private final String desc;

    ItemChangeType(int code, String desc) {
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
