package com.hz.world.core.dao.model;

import java.io.Serializable;
import java.util.Date;

public class UserLimitShare implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    private Double income;

    /**
     * 0:未结束，1：已结束
     */
    private Date finishTime;

    /**
     * 是否已经结算，0：未结算，1：已经结算
     */
    private Integer status;

    /**
     * 持续时间，秒
     */
    private Integer time;

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

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    /**
     * @return 0:未结束，1：已结束
     */
    public Date getFinishTime() {
        return finishTime;
    }

    /**
     * @param finishTime 0:未结束，1：已结束
     */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * @return 是否已经结算，0：未结算，1：已经结算
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status 是否已经结算，0：未结算，1：已经结算
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return 持续时间，秒
     */
    public Integer getTime() {
        return time;
    }

    /**
     * @param time 持续时间，秒
     */
    public void setTime(Integer time) {
        this.time = time;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}