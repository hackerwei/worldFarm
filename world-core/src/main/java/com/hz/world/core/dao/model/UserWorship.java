package com.hz.world.core.dao.model;

import java.io.Serializable;
import java.util.Date;

public class UserWorship implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;

    /**
     * 当前拥有祭品数
     */
    private String worship;

    /**
     * 当前祭祀的id
     */
    private Integer shipId;

    /**
     * 祭祀需要达到的金币数
     */
    private String coin;

    private Date updateTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return 当前拥有祭品数
     */
    public String getWorship() {
        return worship;
    }

    /**
     * @param worship 当前拥有祭品数
     */
    public void setWorship(String worship) {
        this.worship = worship == null ? null : worship.trim();
    }

    /**
     * @return 当前祭祀的id
     */
    public Integer getShipId() {
        return shipId;
    }

    /**
     * @param shipId 当前祭祀的id
     */
    public void setShipId(Integer shipId) {
        this.shipId = shipId;
    }

    /**
     * @return 祭祀需要达到的金币数
     */
    public String getCoin() {
        return coin;
    }

    /**
     * @param coin 祭祀需要达到的金币数
     */
    public void setCoin(String coin) {
        this.coin = coin == null ? null : coin.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}