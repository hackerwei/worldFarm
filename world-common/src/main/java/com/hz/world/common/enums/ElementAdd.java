package com.hz.world.common.enums;


/**  
* <p>Title: DynamicType</p>  
* <p>Description:动态类型 </p>  
* @author linyanchun  
* @date 2018年8月22日  
*/  
public enum ElementAdd {
    
    LEVEL("level", "等级"),
    INVEST("invest", "投资"),
	YEAR("year", "年份"),
	CHALLENGE("challenge", "挑战"),
	COLLECT("collect", "收集"),
	OFFER("offer", "祭天"),
	CATCH("catch", "捕捞"),
	SHOP("shop", "商城"),
	OUTPUT("output", "产出"),
	;
    private final String code;
    private final String desc;

    ElementAdd(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

   
}
