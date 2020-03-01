package com.hz.world.core.dao.model;

import java.io.Serializable;
import java.util.Date;

public class UserCollectLog implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer collectId;

    private Long userId;

    private Integer type;

    private Date addTime;

    public Integer getCollectId() {
        return collectId;
    }

    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}