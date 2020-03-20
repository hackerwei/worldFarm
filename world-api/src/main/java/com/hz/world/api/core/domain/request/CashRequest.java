package com.hz.world.api.core.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * Title: Description: author linyanchun date Feb 23, 2020
 */
@Data
public class CashRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String openid;
	private Integer money;
	private String gameid;
	private String channel;

}