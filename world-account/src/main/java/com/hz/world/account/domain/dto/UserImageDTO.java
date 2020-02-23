package com.hz.world.account.domain.dto;

import java.io.Serializable;

/**
 * @outhor lujian
 * @create 2018-06-29 16:10
 */
public class UserImageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id; //图片
    private Long userId; //用户ID
    private String imgInfo; //图片信息
    private String imgUrl; //图片地址
    private int sort; // 排序
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getImgInfo() {
		return imgInfo;
	}
	public void setImgInfo(String imgInfo) {
		this.imgInfo = imgInfo;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
    
  
}
