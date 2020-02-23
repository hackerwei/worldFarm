package com.hz.world.core.domain.response;

import java.util.List;

import com.hz.world.common.dto.ServiceCommonResponse;
import com.hz.world.core.domain.dto.rewardDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ItemUseResponse extends ServiceCommonResponse {

	private static final long serialVersionUID = -3766693016354347824L;
	
	private Integer code;
	
	private String message;
	
	private List<rewardDTO>  resourceList;
	
	private List<rewardDTO>  itemList;
	
	
}
