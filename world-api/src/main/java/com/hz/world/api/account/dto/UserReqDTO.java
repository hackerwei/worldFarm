package com.hz.world.api.account.dto;

import com.hz.world.api.common.domain.PageRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**  
* <p>Title: UserReqDTO</p>  
* <p>Description: </p>  
* @author linyanchun  
* @date 2018年8月27日  
*/  
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReqDTO extends PageRequest{
	
	private Double lon; // 经度

	private Double lat; // 纬度 
	
	private Long toId; // 被查看人ID
	
}