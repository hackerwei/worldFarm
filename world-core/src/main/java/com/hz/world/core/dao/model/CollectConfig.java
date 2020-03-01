package com.hz.world.core.dao.model;

import java.io.Serializable;

public class CollectConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 解锁类型：1:累计答错，2:累计答对，3:观看广告次数，4:投资次数，5:祭天次数，6:首充，7:解锁所有元素，8:获得指定数量金币，9:完成指定数量挑战
     */
    private Integer type;

    private String param;

    private Integer rewardElement;

    /**
     * 倍率
     */
    private Integer power;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 解锁类型：1:累计答错，2:累计答对，3:观看广告次数，4:投资次数，5:祭天次数，6:首充，7:解锁所有元素，8:获得指定数量金币，9:完成指定数量挑战
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type 解锁类型：1:累计答错，2:累计答对，3:观看广告次数，4:投资次数，5:祭天次数，6:首充，7:解锁所有元素，8:获得指定数量金币，9:完成指定数量挑战
     */
    public void setType(Integer type) {
        this.type = type;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param == null ? null : param.trim();
    }

    public Integer getRewardElement() {
        return rewardElement;
    }

    public void setRewardElement(Integer rewardElement) {
        this.rewardElement = rewardElement;
    }

    /**
     * @return 倍率
     */
    public Integer getPower() {
        return power;
    }

    /**
     * @param power 倍率
     */
    public void setPower(Integer power) {
        this.power = power;
    }
}