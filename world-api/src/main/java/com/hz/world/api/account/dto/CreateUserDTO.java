package com.hz.world.api.account.dto;

import java.io.Serializable;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

	private String userId; 
	
	private String nickname;
	
	private String headImg;
	
	private Integer gender;
	
	private Integer age;
	
	private String creatorId;
	
	private String creatorName;
	
	private String addTime;
	

	
	
}
