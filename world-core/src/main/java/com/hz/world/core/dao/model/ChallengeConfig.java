package com.hz.world.core.dao.model;

import java.io.Serializable;

public class ChallengeConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 种类，11:全体
     */
    private Integer element;

    /**
     * 重量
     */
    private Integer weight;

    /**
     * 奖励的元素，11:全体
     */
    private Integer rewardElement;

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
     * @return 种类，11:全体
     */
    public Integer getElement() {
        return element;
    }

    /**
     * @param element 种类，11:全体
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
     * @return 奖励的元素，11:全体
     */
    public Integer getRewardElement() {
        return rewardElement;
    }

    /**
     * @param rewardElement 奖励的元素，11:全体
     */
    public void setRewardElement(Integer rewardElement) {
        this.rewardElement = rewardElement;
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