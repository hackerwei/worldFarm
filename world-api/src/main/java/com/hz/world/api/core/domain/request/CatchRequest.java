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
public class CatchRequest  implements Serializable{
	
	private static final long serialVersionUID = 1L;

    
    private Integer year;
    private Integer element;
    
    
}