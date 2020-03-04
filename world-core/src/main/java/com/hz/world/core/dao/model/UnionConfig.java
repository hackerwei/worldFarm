package com.hz.world.core.dao.model;

import java.io.Serializable;

public class UnionConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Double target;

    private Double rate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTarget() {
        return target;
    }

    public void setTarget(Double target) {
        this.target = target;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}