package com.hz.world.core.dao.model;

import java.io.Serializable;
import java.util.Date;

public class UserCashChangeLog implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    private Double num;

    private Double afterNum;

    /**
     * 变动类型，0：消费，1：收益
     */
    private Integer relatedType;

    /**
     * 变动类型，1:限时分红小龙虾，2:永久分红小龙虾，3:提现，4:充值，5:联盟收益
     */
    private Integer type;

    private String content;

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

    public Double getNum() {
        return num;
    }

    public void setNum(Double num) {
        this.num = num;
    }

    public Double getAfterNum() {
        return afterNum;
    }

    public void setAfterNum(Double afterNum) {
        this.afterNum = afterNum;
    }

    /**
     * @return 变动类型，0：消费，1：收益
     */
    public Integer getRelatedType() {
        return relatedType;
    }

    /**
     * @param relatedType 变动类型，0：消费，1：收益
     */
    public void setRelatedType(Integer relatedType) {
        this.relatedType = relatedType;
    }

    /**
     * @return 变动类型，1:限时分红小龙虾，2:永久分红小龙虾，3:提现，4:充值，5:联盟收益
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type 变动类型，1:限时分红小龙虾，2:永久分红小龙虾，3:提现，4:充值，5:联盟收益
     */
    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}