package com.hz.world.account.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO implements Serializable {
 
	private static final long serialVersionUID = 1L;

	private String code;
	private Integer type;
	private Long userId;
	private Long fromUserId;
	private Integer fromId;
	private String channelId;
	private String platformInfo;
	private String terminalVersions;
	private String appVersions;
	private String deviceId;
	private String ip;
	private String mac;
	private String longitude;
	private String latitude;
	private String loginDetail;
	private String pushId; //android为dhid,ios为推送token
	private Integer shareType;
}
