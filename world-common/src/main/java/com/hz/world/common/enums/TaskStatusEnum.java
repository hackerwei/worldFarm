package com.hz.world.common.enums;

public enum TaskStatusEnum {
    UN_FINISHED(0, "未达成"),
    FINISHED(1, "已达成"),
    REWARDED(2, "已领奖");

    TaskStatusEnum(final int code, final String value) {
        this.code = code;
        this.value = value;
    }

    private int code;
    private String value;

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

}
