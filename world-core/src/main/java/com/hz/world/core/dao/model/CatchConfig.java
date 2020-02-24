package com.hz.world.core.dao.model;

import java.io.Serializable;

public class CatchConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private Integer weight;

    private Integer additon;

    private Integer fight;

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

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getAdditon() {
        return additon;
    }

    public void setAdditon(Integer additon) {
        this.additon = additon;
    }

    public Integer getFight() {
        return fight;
    }

    public void setFight(Integer fight) {
        this.fight = fight;
    }
}