package com.hz.world.core.domain.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserCashChangeLogDTO  implements Serializable {

	private static final long serialVersionUID = -3766693016354347824L;
	

    private Double num;

    /**
     * 变动类型，0：消费，1：收益
     */
    private Integer relatedType;

    private String content;

    private String date;
	
	
}
