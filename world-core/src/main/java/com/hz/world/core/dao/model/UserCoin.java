package com.hz.world.core.dao.model;

import java.io.Serializable;

public class UserCoin implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;

    private String coin;

    private String incomeRate;

    private Long updateTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin == null ? null : coin.trim();
    }

    public String getIncomeRate() {
        return incomeRate;
    }

    public void setIncomeRate(String incomeRate) {
        this.incomeRate = incomeRate == null ? null : incomeRate.trim();
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}