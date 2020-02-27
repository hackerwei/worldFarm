package com.hz.world.core.dao.model;

import java.io.Serializable;
import java.util.Date;

public class TaskDetailInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 父任务编码
     */
    private String parentCode;

    /**
     * 任务编码
     */
    private String code;

    /**
     * 任务名称
     */
    private String name;

    private Integer diamondCount;

    /**
     * 扩展字段
     */
    private String ext;

    /**
     * 到达值
     */
    private Integer toNum;

    private Date addTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 父任务编码
     */
    public String getParentCode() {
        return parentCode;
    }

    /**
     * @param parentCode 父任务编码
     */
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }

    /**
     * @return 任务编码
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code 任务编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * @return 任务名称
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 任务名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getDiamondCount() {
        return diamondCount;
    }

    public void setDiamondCount(Integer diamondCount) {
        this.diamondCount = diamondCount;
    }

    /**
     * @return 扩展字段
     */
    public String getExt() {
        return ext;
    }

    /**
     * @param ext 扩展字段
     */
    public void setExt(String ext) {
        this.ext = ext == null ? null : ext.trim();
    }

    /**
     * @return 到达值
     */
    public Integer getToNum() {
        return toNum;
    }

    /**
     * @param toNum 到达值
     */
    public void setToNum(Integer toNum) {
        this.toNum = toNum;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}