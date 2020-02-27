package com.hz.world.core.dao.model;

import java.io.Serializable;
import java.util.Date;

public class UserTaskLog implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    /**
     * 父任务编码
     */
    private String parentCode;

    /**
     * 任务id
     */
    private Integer taskId;

    private Date date;

    /**
     * 任务状态，0：未完成，1：已完成，2：已领奖
     */
    private Integer status;

    private Date addTime;

    private Date updateTime;

    private String isDeleted;

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
     * @return 父任务编码
     */
    public String getParentCode() {
        return parentCode;
    }

    /**
     * @param parentCode 父任务编码
     */
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }

    /**
     * @return 任务id
     */
    public Integer getTaskId() {
        return taskId;
    }

    /**
     * @param taskId 任务id
     */
    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return 任务状态，0：未完成，1：已完成，2：已领奖
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status 任务状态，0：未完成，1：已完成，2：已领奖
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }
}