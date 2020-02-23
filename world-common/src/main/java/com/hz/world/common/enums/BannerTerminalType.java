package com.hz.world.common.enums;

public enum BannerTerminalType {

    TAB("1", "Tab"),
    ACTIVITY("2", "Activity");

    BannerTerminalType(final String type, final String name) {
        this.type = type;
        this.name = name;
    }

    private String type;
    private String name;

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
