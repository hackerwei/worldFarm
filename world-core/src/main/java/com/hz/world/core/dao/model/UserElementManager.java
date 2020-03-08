package com.hz.world.core.dao.model;

import java.io.Serializable;
import java.util.Date;

public class UserElementManager implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer element;

    private Long userId;

    private Long manager;

    /**
     * 收益增加倍数
     */
    private Double incomerate;

    private Date addTime;

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

    public Long getManager() {
        return manager;
    }

    public void setManager(Long manager) {
        this.manager = manager;
    }

    /**
     * @return 收益增加倍数
     */
    public Double getIncomerate() {
        return incomerate;
    }

    /**
     * @param incomerate 收益增加倍数
     */
    public void setIncomerate(Double incomerate) {
        this.incomerate = incomerate;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}