package com.hz.world.core.domain.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskOutDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

	private Integer id;
	

	private String title;
	
	private Integer rewardNum;
	
	private Integer needNum; //需要进度
	
	private Integer taskStatus;
	
	private Integer getNum;//当前进度
	
	
}
