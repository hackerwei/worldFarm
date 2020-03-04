package com.hz.world.api.account.request;

import com.hz.world.api.common.domain.PageRequest;

import lombok.Data;


/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 23, 2020 
 */
@Data
public class UserInfoRequest  extends PageRequest{
    
	private Long toId; //翻牌的用户id
	private String qq;
	private String weixin;
   
}