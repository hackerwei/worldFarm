package com.hz.world.core.domain.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

	private Integer id;
	
	private boolean finish;
	
}
