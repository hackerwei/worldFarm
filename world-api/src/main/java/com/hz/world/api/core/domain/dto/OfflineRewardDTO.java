package com.hz.world.api.core.domain.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfflineRewardDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long offlineTime;  //离线时长

	private String income;     //离线收益
	
	
}
