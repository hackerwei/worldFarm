package com.hz.world.account.domain.dto;

import java.io.Serializable;

/**
 * @outhor linyanchun
 * @create 2018-06-29 16:10
 */

public class UserRankingDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId; //用户ID
    private String nickname; //用户昵称
    private Integer gender; // 性别
    private String headImg; // 头像完整地址
    private Integer userLevel; // 用户等级
    private Integer rank;  //用户排名
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public Integer getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
 
    
   
}
