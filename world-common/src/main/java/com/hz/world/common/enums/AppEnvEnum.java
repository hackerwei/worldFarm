package com.hz.world.common.enums;

public enum AppEnvEnum {
    DEV("dev", "开发环境"),
    TST("test", "测试环境"),
    PRE("pre", "预发环境"),
    PRD("prod", "生产环境");

    AppEnvEnum(final String code, final String value) {
        this.code = code;
        this.value = value;
    }

    private String code;
    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

}
