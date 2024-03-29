package com.hz.world.core.dao.model;

import java.io.Serializable;
import java.util.Date;

public class UserChallengeLog implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer challengeId;

    private Long userId;

    private Integer element;

    private Date addTime;

    public Integer getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(Integer challengeId) {
        this.challengeId = challengeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getElement() {
        return element;
    }

    public void setElement(Integer element) {
        this.element = element;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}