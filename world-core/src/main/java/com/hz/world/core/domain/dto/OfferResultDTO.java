package com.hz.world.core.domain.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferResultDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

	private String currentOffer; //当前祭品
	
	private String incomeRate;  //全体收益
	
	
}
