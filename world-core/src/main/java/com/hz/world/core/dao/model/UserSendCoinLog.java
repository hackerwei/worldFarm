package com.hz.world.core.dao.model;

import java.io.Serializable;
import java.util.Date;

public class UserSendCoinLog implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    private Long toUserId;

    /**
     * 0:未读，1:已读
     */
    private Integer status;

    /**
     * 赠送金币
     */
    private String coin;

    private Date date;

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

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    /**
     * @return 0:未读，1:已读
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status 0:未读，1:已读
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return 赠送金币
     */
    public String getCoin() {
        return coin;
    }

    /**
     * @param coin 赠送金币
     */
    public void setCoin(String coin) {
        this.coin = coin == null ? null : coin.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}