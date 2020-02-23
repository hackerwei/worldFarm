package com.hz.world.core.dao.model;

import java.io.Serializable;

public class ElementConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String initialCost;

    private String costGrowth;

    private String initialOutput;

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

    public String getInitialCost() {
        return initialCost;
    }

    public void setInitialCost(String initialCost) {
        this.initialCost = initialCost == null ? null : initialCost.trim();
    }

    public String getCostGrowth() {
        return costGrowth;
    }

    public void setCostGrowth(String costGrowth) {
        this.costGrowth = costGrowth == null ? null : costGrowth.trim();
    }

    public String getInitialOutput() {
        return initialOutput;
    }

    public void setInitialOutput(String initialOutput) {
        this.initialOutput = initialOutput == null ? null : initialOutput.trim();
    }
}