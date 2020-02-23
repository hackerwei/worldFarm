package com.hz.world.account.domain.dto;

import java.io.Serializable;

/**
 * @outhor lujian
 * @create 2018-06-29 16:10
 */
public class UserFriendDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId; 
    private String nickname; 
    private String headImg; 
    private String image; 
    private Double face; 
    private Integer gender; 
    private Integer age; 
    private Integer aiAge; 
    private String emotionalState;
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
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Double getFace() {
		return face;
	}
	public void setFace(Double face) {
		this.face = face;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getAiAge() {
		return aiAge;
	}
	public void setAiAge(Integer aiAge) {
		this.aiAge = aiAge;
	}
	public String getEmotionalState() {
		return emotionalState;
	}
	public void setEmotionalState(String emotionalState) {
		this.emotionalState = emotionalState;
	}

    
}
