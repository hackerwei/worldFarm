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
public class MessageDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long userId;
	
	private String nickname;
	
	private String headImg;
	
	private Integer messageType;
	
	private String content;
	
	
}
