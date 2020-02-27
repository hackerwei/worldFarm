package com.hz.world.core.dao.model;

import java.io.Serializable;
import java.util.Date;

public class UserInviteLog implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    /**
     * 0:未领取，1:已领取
     */
    private Integer status;

    private Long inviteUserId;

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

    /**
     * @return 0:未领取，1:已领取
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status 0:未领取，1:已领取
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getInviteUserId() {
        return inviteUserId;
    }

    public void setInviteUserId(Long inviteUserId) {
        this.inviteUserId = inviteUserId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}