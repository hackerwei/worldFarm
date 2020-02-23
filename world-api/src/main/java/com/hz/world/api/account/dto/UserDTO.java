package com.hz.world.api.account.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



import lombok.Data;

@Data
public class UserDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long userId; //用户ID
	
	private String nickname; //用户昵称
	
	private String mobile; //手机号码
	
	private String gender; // 性别
	
	private String headImg; // 头像完整地址
	
	private String spaceImage; // 主播封面
	
	private String signature; // 用户签名
	
	private String city; // 城市
	
	private Integer cityCode; // 城市code
	
	private Date birthday; // 出生日期
	
	private String locationProvinceId; // 所在地code
	
	private String locationProvinceName; // 所在地名称
	
	private String accessToken; //登录token
	

}
