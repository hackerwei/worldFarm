package com.hz.world.core.domain.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserElementDTO  implements Serializable {

	private static final long serialVersionUID = -3766693016354347824L;
	
	private Integer element;
	
	private Integer level;
	
	private String output;
	
}
