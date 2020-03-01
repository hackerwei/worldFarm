package com.hz.world.account.dao.model;

import java.io.Serializable;
import java.util.Date;

public class UserBaseInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;

    private String nickname;

    private String headImg;

    /**
     * 性别
     */
    private Integer gender;

    private Integer year;

    /**
     * 段位等级
     */
    private Integer level;

    private Integer weight;

    /**
     * 用户现金
     */
    private Double cash;

    private Long fromUserId;

    private String inviteCode;

    /**
     * 钻石
     */
    private Integer diamond;

    /**
     * 0:未认证，1:已认证
     */
    private Integer auth;

    /**
     * 是否激活，0:未激活，1:已激活
     */
    private Integer active;

    /**
     * 用户评分
     */
    private Double score;

    private String weixin;

    private String qq;

    private Date createTime;

    private Date updateTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg == null ? null : headImg.trim();
    }

    /**
     * @return 性别
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * @param gender 性别
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * @return 段位等级
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * @param level 段位等级
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * @return 用户现金
     */
    public Double getCash() {
        return cash;
    }

    /**
     * @param cash 用户现金
     */
    public void setCash(Double cash) {
        this.cash = cash;
    }

    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode == null ? null : inviteCode.trim();
    }

    /**
     * @return 钻石
     */
    public Integer getDiamond() {
        return diamond;
    }

    /**
     * @param diamond 钻石
     */
    public void setDiamond(Integer diamond) {
        this.diamond = diamond;
    }

    /**
     * @return 0:未认证，1:已认证
     */
    public Integer getAuth() {
        return auth;
    }

    /**
     * @param auth 0:未认证，1:已认证
     */
    public void setAuth(Integer auth) {
        this.auth = auth;
    }

    /**
     * @return 是否激活，0:未激活，1:已激活
     */
    public Integer getActive() {
        return active;
    }

    /**
     * @param active 是否激活，0:未激活，1:已激活
     */
    public void setActive(Integer active) {
        this.active = active;
    }

    /**
     * @return 用户评分
     */
    public Double getScore() {
        return score;
    }

    /**
     * @param score 用户评分
     */
    public void setScore(Double score) {
        this.score = score;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin == null ? null : weixin.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}