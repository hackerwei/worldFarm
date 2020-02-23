package com.hz.world.core.domain.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserCoinDTO  implements Serializable {

	private static final long serialVersionUID = -3766693016354347824L;
	
	private Long userId;
	
	private Long updateTime;
	
	private String coin;
	
	private String icomeRate;
	
	
}
