package com.hz.world.core.dao.model;

import java.io.Serializable;

public class TitleConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String income;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income == null ? null : income.trim();
    }
}