package com.hz.world.core.domain.response;

import com.hz.world.common.dto.ServiceCommonResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DayEnergyResponse extends ServiceCommonResponse {

	
	private Integer energy;
	
	
}
