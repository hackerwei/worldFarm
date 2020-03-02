package com.hz.world.api.account.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



import lombok.Data;

@Data
public class ChallengeOutDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer element; //元素
	
	private Integer challengeId; //挑战id

}
