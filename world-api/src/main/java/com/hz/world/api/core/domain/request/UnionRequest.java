package com.hz.world.api.core.domain.request;

import lombok.Data;

import java.io.Serializable;

import com.hz.world.api.common.domain.PageRequest;

/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 23, 2020 
 */
@Data
public class UnionRequest  extends PageRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;

    
    private Integer step;

    
    
}