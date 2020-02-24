package com.hz.world.core.dao.model;

import java.io.Serializable;
import java.util.Date;

public class UserTotalIncome implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;

    private String income;

    private Date updateTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income == null ? null : income.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}