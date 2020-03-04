package com.hz.world.core.dao.model;

import java.io.Serializable;
import java.util.Date;

public class DateShare implements Serializable {
    private static final long serialVersionUID = 1L;

    private Date date;

    private Double share;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getShare() {
        return share;
    }

    public void setShare(Double share) {
        this.share = share;
    }
}