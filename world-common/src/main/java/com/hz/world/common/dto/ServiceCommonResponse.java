package com.hz.world.common.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ServiceCommonResponse  implements Serializable {

	private static final long serialVersionUID = -3766693016354347824L;
	
	private Integer code;
	
	private String message;
	
	
	
}
