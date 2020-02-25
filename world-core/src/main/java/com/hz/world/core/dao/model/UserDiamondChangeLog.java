package com.hz.world.core.dao.model;

import java.io.Serializable;
import java.util.Date;

public class UserDiamondChangeLog implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    private String num;

    private String afterNum;

    /**
     * 变动类型，0：收益，1：消费
     */
    private Integer relatedType;

    private Date addTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num == null ? null : num.trim();
    }

    public String getAfterNum() {
        return afterNum;
    }

    public void setAfterNum(String afterNum) {
        this.afterNum = afterNum == null ? null : afterNum.trim();
    }

    /**
     * @return 变动类型，0：收益，1：消费
     */
    public Integer getRelatedType() {
        return relatedType;
    }

    /**
     * @param relatedType 变动类型，0：收益，1：消费
     */
    public void setRelatedType(Integer relatedType) {
        this.relatedType = relatedType;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}