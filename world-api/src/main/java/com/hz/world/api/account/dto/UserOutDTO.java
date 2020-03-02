package com.hz.world.api.account.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



import lombok.Data;

@Data
public class UserOutDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long userId; //用户ID
	    
	private String nickname; //用户昵称

	private String headImg; // 头像完整地址
	    
	private Double score; //用户评分
	    
	private Double finishRate; //完成百分比
	    
	private Double cash;//现金
	
	private String inviteCode;//用户等级
	    
	private Integer auth; //0:未实名，1:已实名

	private Integer level;
	
	private String qq;
	
	private String weixin;
	

}
