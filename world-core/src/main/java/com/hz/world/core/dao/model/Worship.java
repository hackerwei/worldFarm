package com.hz.world.core.dao.model;

import java.io.Serializable;

public class Worship implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Long money;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }
}