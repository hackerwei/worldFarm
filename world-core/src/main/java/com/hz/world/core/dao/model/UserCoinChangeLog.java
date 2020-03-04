package com.hz.world.core.dao.model;

import java.io.Serializable;
import java.util.Date;

public class UserCoinChangeLog implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    private String num;

    private String afterNum;

    /**
     * 变动类型，0：消费，1：收益
     */
    private Integer relatedType;

    private String content;

    /**
     * 事件：0:充值，1:限时分红小龙虾，2:永久分红小龙虾，3:联盟收益，4:提现
     */
    private Integer type;

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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num == null ? null : num.trim();
    }

    public String getAfterNum() {
        return afterNum;
    }

    public void setAfterNum(String afterNum) {
        this.afterNum = afterNum == null ? null : afterNum.trim();
    }

    /**
     * @return 变动类型，0：消费，1：收益
     */
    public Integer getRelatedType() {
        return relatedType;
    }

    /**
     * @param relatedType 变动类型，0：消费，1：收益
     */
    public void setRelatedType(Integer relatedType) {
        this.relatedType = relatedType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * @return 事件：0:充值，1:限时分红小龙虾，2:永久分红小龙虾，3:联盟收益，4:提现
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type 事件：0:充值，1:限时分红小龙虾，2:永久分红小龙虾，3:联盟收益，4:提现
     */
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