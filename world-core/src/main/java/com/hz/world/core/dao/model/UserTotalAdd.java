package com.hz.world.core.dao.model;

import java.io.Serializable;

public class UserTotalAdd implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;

    /**
     * 全体收益加成
     */
    private Integer totalAdd;

    /**
     * 来源，0:商店，1:投资，2:挑战
     */
    private String type;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return 全体收益加成
     */
    public Integer getTotalAdd() {
        return totalAdd;
    }

    /**
     * @param totalAdd 全体收益加成
     */
    public void setTotalAdd(Integer totalAdd) {
        this.totalAdd = totalAdd;
    }

    /**
     * @return 来源，0:商店，1:投资，2:挑战
     */
    public String getType() {
        return type;
    }

    /**
     * @param type 来源，0:商店，1:投资，2:挑战
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}