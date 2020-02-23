package com.hz.world.common.enums;

public enum SmsTemplateEnum {

    VERIFY_CODE("309031002", "验证码短信", "验证码：[%s]请在10分钟内完成输入，感谢您的使用！「要要直播」");

    SmsTemplateEnum(final String id, final String name, final String template) {
        this.id = id;
        this.name = name;
        this.template = template;
    }

    private String id;
    private String name;
    private String template;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTemplate() {
        return template;
    }

}
