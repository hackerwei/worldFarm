package com.hz.world.api.account.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



import lombok.Data;

@Data
public class UserOutDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String userId; //用户ID
	    
	private String nickname; //用户昵称

	private Integer gender; // 性别

	private String headImg; // 头像完整地址

	private String signature; // 签名

	private String weixin; // 微信号
	    
	private String province;//省
	    
	private String city;//市
	    
	private String star;//星座
	
	private Integer userLevel;//用户等级
	    
	private Long weimoId;
	   
	private Integer weixinSetting; //微信设置0：不可见，1：可见
	

}
