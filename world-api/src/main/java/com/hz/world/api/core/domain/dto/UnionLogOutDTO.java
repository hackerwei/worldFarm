package com.hz.world.api.core.domain.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnionLogOutDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String content;
	
	private String date;
	
	private Double income;
}
