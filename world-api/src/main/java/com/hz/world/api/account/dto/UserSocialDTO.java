package com.hz.world.api.account.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



import lombok.Data;

@Data
public class UserSocialDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long userId; //用户ID
	    
	private String nickname; //用户昵称

	private String headImg; // 头像完整地址
	    
	    
	private Integer level;

	private String qq;
	
	private String weixin;
	
}
