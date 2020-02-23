package com.hz.world.common.enums;

/**
 * @outhor linyanchun
 * @create 2018-05-31 16:04
 */
public enum BreakType {
    BREAK(1, "正常破坏"),
    DESTROY(2, "彻底破坏"),
    NOTHING(3, "不进行破坏");

    private final int code;
    private final String desc;

    BreakType(int code, String desc) {
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
