package com.hz.world.account.domain.response;

import com.hz.world.common.dto.ServiceCommonResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class LikeResponse extends ServiceCommonResponse {

	private static final long serialVersionUID = -3766693016354347824L;
	
	
	private Integer rewardNum;
	
	private Integer leftCount;
	
	
}
