package com.hz.world.core.dao.model;

import java.io.Serializable;

public class UserUnionStep implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;

    private Integer step;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }
}