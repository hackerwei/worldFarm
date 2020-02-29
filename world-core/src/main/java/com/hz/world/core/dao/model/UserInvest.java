package com.hz.world.core.dao.model;

import java.io.Serializable;

public class UserInvest implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer investId;

    private Long userId;

    public Integer getInvestId() {
        return investId;
    }

    public void setInvestId(Integer investId) {
        this.investId = investId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}