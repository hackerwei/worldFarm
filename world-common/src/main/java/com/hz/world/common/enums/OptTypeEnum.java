package com.hz.world.common.enums;

/**
 * @outhor lujian
 * @create 2018-06-07 15:01
 */
public enum OptTypeEnum {
    ADD(1, "新增"),
    UPDATE(2, "修改"),
    DELETE(3, "删除");

    private final int code;
    private final String desc;

    OptTypeEnum(int code, String desc) {
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
