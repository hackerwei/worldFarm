package com.hz.world.core.dao.model;

import java.io.Serializable;

public class ShopConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer type;

    private Integer param;

    private Integer price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}