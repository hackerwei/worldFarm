package com.hz.world.api.core.domain.dto;

import java.io.Serializable;
import java.util.List;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendIncomeDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Double totalIncome; 

	private Double targetIncome;
	
	private Integer step;
	
	private Double rate;
	
	
}
