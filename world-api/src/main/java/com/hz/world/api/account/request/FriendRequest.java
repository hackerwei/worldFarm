package com.hz.world.api.account.request;

import com.hz.world.api.common.domain.PageRequest;

import lombok.Data;

/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Mar 1, 2020 
 */
@Data
public class FriendRequest extends PageRequest{
    
	private Long friendId; //好友id
   
	private Integer type; //0:好友，1:间接好友，2:未激活
   
}