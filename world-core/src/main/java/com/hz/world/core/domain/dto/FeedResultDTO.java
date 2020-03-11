package com.hz.world.core.domain.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class FeedResultDTO  implements Serializable {

	private static final long serialVersionUID = -3766693016354347824L;
	
	private Integer weight;
	
	private Integer  type; //0:限时分红小龙虾，1:年份升级
	
	private Integer minute; //收益时间
	

	
	
}
