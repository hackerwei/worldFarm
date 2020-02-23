package com.hz.world.api.account.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



import lombok.Data;

@Data
public class NicknameDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String nickname; 
	
	private String userId;
	
	private Integer useCount;

}
