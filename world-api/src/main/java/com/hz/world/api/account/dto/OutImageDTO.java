package com.hz.world.api.account.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class OutImageDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id; // 图片ID

	private String imgInfo; // 图片信息
	private String imgUrl; // 图片地址
	private int sort; // 排序

}
