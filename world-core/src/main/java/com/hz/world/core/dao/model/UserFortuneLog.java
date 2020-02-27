package com.hz.world.core.dao.model;

import java.io.Serializable;
import java.util.Date;

public class UserFortuneLog implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    private Integer diamondCount;

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

    public Integer getDiamondCount() {
        return diamondCount;
    }

    public void setDiamondCount(Integer diamondCount) {
        this.diamondCount = diamondCount;
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