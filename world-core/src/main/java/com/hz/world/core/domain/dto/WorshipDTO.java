package com.hz.world.core.domain.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorshipDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

	private String currentOffer; //当前祭品
	
	private String incomeRate;  //全体收益
	
	private ExpressInfoDTO countDown; //倒计时
	
	private String needCoin;
	
	private String currentCoin;
	
	private String output;
	
	private String rate; //倍率
	
	private String getOffer; //预计获得的祭品
	
	private long finishTime;
	
}
