package com.hz.world.core.domain.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForeverShareElementDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long userId;
	
	private String  nickname;
	
	private String headImg; 
	
	private Integer num; 
	
	private Double income;
	
	
}
