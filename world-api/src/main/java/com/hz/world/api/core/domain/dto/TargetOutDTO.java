package com.hz.world.api.core.domain.dto;

import java.io.Serializable;
import java.util.List;

import com.hz.world.core.domain.dto.OtherElementDTO;
import com.hz.world.core.domain.dto.limitShareElementDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TargetOutDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer currentWeight;
	
	private Integer targetWeight;
	
	private Double todayTotalShare;
	
	private Integer limitShareCount; //限时分红数量
	
	private Integer limitElementCount; //限时分红小龙虾数量
	
	private Integer foreverElementCount; //永久分红小龙虾数量
	
	private Integer otherElementCount;//5洲小龙虾数量
	
	private List<limitShareElementDTO> limitElementList;//限时分红小龙虾
	
	private List<OtherElementDTO> otherElementList;
}
