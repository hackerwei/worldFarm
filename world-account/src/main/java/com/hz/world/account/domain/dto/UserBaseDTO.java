package com.hz.world.account.domain.dto;

import java.io.Serializable;

import lombok.Data;


/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Mar 1, 2020 
 */
@Data
public class UserBaseDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long userId; //用户ID

	private String nickname; //用户昵称

	
	private String regTime; // 注册时间



	private String headImg; // 头像完整地址

	private Integer auth;
    

    private Integer active;
	
    private Integer level;
}