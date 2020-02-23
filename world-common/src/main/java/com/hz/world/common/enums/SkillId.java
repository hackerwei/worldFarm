package com.hz.world.common.enums;

/**
 * @outhor lujian
 * @create 2018-05-31 16:04
 */
public enum SkillId {
    SKILL1(1, "财神驾到","有捣乱时有概率额外增加金币"),
    SKILL2(2, "铜钱铁壁","有概率抵消敌人的捣乱"),
    SKILL3(3, "水来土掩","被别人尿尿时有概率减少金币的损失"),
    SKILL4(4, "火眼金睛","有概率直接找到最富有的尿尿目标");

    private final int code;
    private final String name;
    private final String desc;

    SkillId(int code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public String getName() {
        return name;
    }

}
