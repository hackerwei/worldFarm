package com.hz.world.core.domain.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtherElementDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer catchId;
	
	private Integer num;
	
	private String name;
	
	private Integer incomeAd; //首页增加
		
}
