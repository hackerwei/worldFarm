package com.hz.world.api.account.dto;

import java.io.Serializable;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCheckDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

	private int match; //星座匹配度
	
	private int commonCount;//共同兴趣数
	
	private Set<String> commonList;//兴趣
	
	private String work;//工作
	
	private String industry;
	
	private String profession;
	
	private Integer gender;
	
	private String constellation;
	
	private int visitCount;
	
	private String visitTime;
	
	
}
