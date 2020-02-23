package com.hz.world.account.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户基本信息DTO
 * <pre>
 * Class Name: UserInfoDTO.java
 * @author liuxiao
 * Modifications:
 * Modifier liuxiao; 2017年8月14日; Create new Class UserBaseInfoDTO.java.
 * </pre>
 */
@Data
public class UserBaseDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long userId; //用户ID

	private String nickname; //用户昵称

	private String realName; //用户真实姓名

	private String mobile; //手机号码

	private java.util.Date regTime; // 注册时间

	private String regChannelId; // 注册渠道

	private String gender; // 性别

	private String headImg; // 头像完整地址

	private java.util.Date birthday; // 出生日期

	private String signature; // 签名

	private Integer locationProvinceId; // 所在地

	private Integer professionCode; // 职业code
	
}