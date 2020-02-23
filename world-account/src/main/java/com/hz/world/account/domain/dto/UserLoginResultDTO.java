package com.hz.world.account.domain.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * 用户登录输出信息DTO
 * <pre>
 * Class Name: UserOutDTO.java
 * @author linyanchun
 * Modifications:
 * Modifier linyanchun; 2018年5月28日; Create new Class UserOutDTO.java.
 * </pre>
 */
@Data
public class UserLoginResultDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String userId; //用户ID String
	
	private String accessToken; //登录token
	
	private Integer loginType; //登陆结果 1：已经注册，0.未注册
	
	private String nickname;
	
	private String headImg; 
	
}