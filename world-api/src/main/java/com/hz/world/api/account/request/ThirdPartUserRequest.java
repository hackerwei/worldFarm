package com.hz.world.api.account.request;

import lombok.Data;

/**
 * @outhor lujian
 * @create 2018-06-28 16:06
 */
@Data
public class ThirdPartUserRequest {
    private String thirdId;
    private String gender;//1为男性，0为女性
    private String nickName;
    private String headImgUrl;
    private String province;
    private Integer regChannelId;
    private String pushId;
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
}