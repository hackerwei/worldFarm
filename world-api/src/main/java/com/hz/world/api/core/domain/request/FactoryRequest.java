package com.hz.world.api.core.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 23, 2020 
 */
@Data
public class FactoryRequest  implements Serializable{
	
	private static final long serialVersionUID = 1L;

    
    private Long toUserId;
    
    private Integer elementId;
    
    private Integer shareType;
}