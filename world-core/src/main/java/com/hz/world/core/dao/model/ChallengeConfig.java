package com.hz.world.core.dao.model;

import java.io.Serializable;

public class ChallengeConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 种类
     */
    private Integer element;

    /**
     * 重量
     */
    private Integer weight;

    /**
     * 奖励的元素
     */
    private Integer rewardWeight;

    /**
     * 元素收益倍数
     */
    private Integer power;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 种类
     */
    public Integer getElement() {
        return element;
    }

    /**
     * @param element 种类
     */
    public void setElement(Integer element) {
        this.element = element;
    }

    /**
     * @return 重量
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * @param weight 重量
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * @return 奖励的元素
     */
    public Integer getRewardWeight() {
        return rewardWeight;
    }

    /**
     * @param rewardWeight 奖励的元素
     */
    public void setRewardWeight(Integer rewardWeight) {
        this.rewardWeight = rewardWeight;
    }

    /**
     * @return 元素收益倍数
     */
    public Integer getPower() {
        return power;
    }

    /**
     * @param power 元素收益倍数
     */
    public void setPower(Integer power) {
        this.power = power;
    }
}