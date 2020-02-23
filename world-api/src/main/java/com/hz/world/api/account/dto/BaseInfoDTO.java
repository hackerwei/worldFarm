package com.hz.world.api.account.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



import lombok.Data;

@Data
public class BaseInfoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String weixin; 
	
	private String work; 
	
	private String haunt; 
	
	private String industry; 
	
	private String location; 

	private String company;
	
	private String school;
	
	private String profession;

}
