package com.hz.world.common.enums;


/**  
* <p>Title: ResourceChangeType</p>  
* <p>Description:动态类型 </p>  
* @author linyanchun  
* @date 2018年8月22日  
*/  
public enum CollectType {
    
    ANSWER_WRONG(1, "累计答错"),
    ANSWER_RIGHT(2, "累计答对"),
    AD(3, "看广告次数"),
    INVEST(4, "投资次数"),
    OFFER(5, "祭天次数"),
    RECHARGE(6, "首充"),
    ELEMENT(7, "解锁所有元素"),
    COIN(8, "获得指定数量金币"),
    CHALLENGE(9, "完成指定数量挑战"),
    ;
    private final int code;
    private final String desc;

    CollectType(int code, String desc) {
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
