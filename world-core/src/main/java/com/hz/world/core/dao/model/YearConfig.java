package com.hz.world.core.dao.model;

import java.io.Serializable;

public class YearConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer year;

    private Integer income;

    private Integer weight;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}