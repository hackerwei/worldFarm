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
public class InviterDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long userId; 

	private String nickname;
	
	private String headImg;
	
	private String qq;
	
	private String weixin;
	
	private Integer level;
	
	private Double todayIncome;
	
	private Integer inviteNum;
	
	private Double totalIncome;
	
	
}
