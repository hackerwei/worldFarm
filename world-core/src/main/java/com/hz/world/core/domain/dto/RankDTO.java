package com.hz.world.core.domain.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class RankDTO  implements Serializable {

	private static final long serialVersionUID = -3766693016354347824L;
	
	private Long userId;
	private Integer rank;
	
	private String nickname;
	
	private String headImg;
	
	private Integer year;
	
	private String text;
	
	
}
