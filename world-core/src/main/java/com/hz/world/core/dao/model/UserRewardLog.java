package com.hz.world.core.dao.model;

import java.io.Serializable;
import java.util.Date;

public class UserRewardLog implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    /**
     * 奖励类型
     */
    private Integer rewardType;

    /**
     * 关联id，比如任务id
     */
    private Integer relatedId;

    private String rewardCount;

    /**
     * 奖励信息
     */
    private String rewardMessage;

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
     * @return 奖励类型
     */
    public Integer getRewardType() {
        return rewardType;
    }

    /**
     * @param rewardType 奖励类型
     */
    public void setRewardType(Integer rewardType) {
        this.rewardType = rewardType;
    }

    /**
     * @return 关联id，比如任务id
     */
    public Integer getRelatedId() {
        return relatedId;
    }

    /**
     * @param relatedId 关联id，比如任务id
     */
    public void setRelatedId(Integer relatedId) {
        this.relatedId = relatedId;
    }

    public String getRewardCount() {
        return rewardCount;
    }

    public void setRewardCount(String rewardCount) {
        this.rewardCount = rewardCount == null ? null : rewardCount.trim();
    }

    /**
     * @return 奖励信息
     */
    public String getRewardMessage() {
        return rewardMessage;
    }

    /**
     * @param rewardMessage 奖励信息
     */
    public void setRewardMessage(String rewardMessage) {
        this.rewardMessage = rewardMessage == null ? null : rewardMessage.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}