package com.hz.world.core.dao.model;

import java.io.Serializable;

public class InvestConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer element;

    private Integer type;

    private Integer param;

    private String initialCost;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getElement() {
        return element;
    }

    public void setElement(Integer element) {
        this.element = element;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getParam() {
        return param;
    }

    public void setParam(Integer param) {
        this.param = param;
    }

    public String getInitialCost() {
        return initialCost;
    }

    public void setInitialCost(String initialCost) {
        this.initialCost = initialCost == null ? null : initialCost.trim();
    }
}