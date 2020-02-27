/**
 * Title: UserItemDTO.java
 * Description: 
 * author linyanchun
 * date 2019年3月11日 
 */
package com.hz.world.core.domain.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * Title: Description: author linyanchun date 2019年3月11日
 */
@Data
public class UserInviteDTO implements Serializable {
	private static final long serialVersionUID = -3766693016354347824L;
	
	private String userId;

	private String nickname;
	
	private String headImg;;

	private Integer status;
	
	
}
