package com.hz.world.core.domain.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class TaskInfoDTO  implements Serializable {

	private static final long serialVersionUID = -3766693016354347824L;
	
	private Integer id;
	
	private String name;
	
	private String info;
	
	private String remarks;
	
	private String icon;
	
	private String url;	
	
	private Integer type;
	
	private String code;
	
}
