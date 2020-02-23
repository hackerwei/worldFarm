package com.hz.world.api.core.domain.request;

import java.io.Serializable;

import lombok.Data;

/**
 * @outhor lujian
 * @create 2018-06-28 16:06
 */
@Data
public class ElementRequest  implements Serializable{
	
	private static final long serialVersionUID = 1L;

    
    private Integer elementId;
    
    private Integer proLevel;
    
    private Integer newLevel;
}