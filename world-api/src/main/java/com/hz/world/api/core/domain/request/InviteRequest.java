package com.hz.world.api.core.domain.request;

import lombok.Data;

/**
 * Title: 
 * Description: 
 * author linyanchun
 * date 2019年3月6日 
 */
@Data
public class InviteRequest {
   
	
	private Long  inviteUserId; //邀请者id
	private Long  fromUserId; //被邀请者id
}