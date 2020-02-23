package com.hz.world.core.dao.model;

import java.io.Serializable;
import java.util.Date;

public class UserElement implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer element;

    private Long userId;

    private Integer level;

    private Date createTime;

    private Date updateTime;

    public Integer getElement() {
        return element;
    }

    public void setElement(Integer element) {
        this.element = element;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}