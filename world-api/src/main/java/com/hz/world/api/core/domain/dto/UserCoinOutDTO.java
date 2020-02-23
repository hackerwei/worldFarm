package com.hz.world.api.core.domain.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCoinOutDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long updateTime; 

	private String coin;
	
	private String output;
	
	
}
