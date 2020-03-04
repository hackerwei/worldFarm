package com.hz.world.core.dao.model;

import java.io.Serializable;
import java.util.Date;

public class UserUnionIncomeLog implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    private Date date;

    private Double income;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }
}