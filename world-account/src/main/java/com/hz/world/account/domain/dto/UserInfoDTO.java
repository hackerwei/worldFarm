package com.hz.world.account.domain.dto;

import java.io.Serializable;

/**
 * @outhor lujian
 * @create 2018-06-29 16:10
 */
public class UserInfoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId; //用户ID
    private String nickname; //用户昵称
    private String realName; //用户真实姓名
    private String mobile; //手机号码
    private String regTime; // 注册时间
    private String gender; // 性别
    private String headImg; // 头像完整地址
    private String birthday; // 出生日期
    private String sign; // 签名
    private String locationProvinceId; // 所在地
    private String professionCode; // 职业code
    private String spaceImg; //空间背景
    private String accessToken;//token

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
        this.nickname = nickname;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getLocationProvinceId() {
        return locationProvinceId;
    }

    public void setLocationProvinceId(String locationProvinceId) {
        this.locationProvinceId = locationProvinceId;
    }

    public String getProfessionCode() {
        return professionCode;
    }

    public void setProfessionCode(String professionCode) {
        this.professionCode = professionCode;
    }

    public String getSpaceImg() {
        return spaceImg;
    }

    public void setSpaceImg(String spaceImg) {
        this.spaceImg = spaceImg;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
