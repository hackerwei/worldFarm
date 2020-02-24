package com.hz.world.core.dao.model;

import java.io.Serializable;
import java.util.Date;

public class UserCatch implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;

    private Integer year;

    private Integer catchId;

    private Date addTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getCatchId() {
        return catchId;
    }

    public void setCatchId(Integer catchId) {
        this.catchId = catchId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}