package com.hz.world.core.domain.response;

import com.hz.world.common.dto.ServiceCommonResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class SignResponse extends ServiceCommonResponse {

	
	private Integer resourceId;
	
	private Integer resourceNum;
	
	private Integer itemId;
	
	private Integer itemNum;
}
