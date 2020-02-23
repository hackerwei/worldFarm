package com.hz.world.api.common.domain;

import java.io.Serializable;

/**
 * @outhor lujian
 * @create 2018-06-01 14:41
 */
public class PageRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    public int pageNo; // 页码
    public int pageSize; // 页面数据条数

    public int getPageNo() {
        return this.pageNo = (pageNo == 0) ? 1: pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return this.pageSize = (pageSize == 0) ? 20 : pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}