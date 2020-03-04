package com.hz.world.core.domain.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class limitShareElementDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private boolean finish;
	
	private Double income;
	
	private Integer time; //需要秒数
	
	private Long countDown; //剩余倒计时
	
	
}
