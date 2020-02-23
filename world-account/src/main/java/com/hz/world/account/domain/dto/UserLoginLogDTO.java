 /**
 * Java Framework Auto Generator Tools
 * Copyright (c) 2015-2020 www.wifi.com
 * @company wifi
 */
package com.hz.world.account.domain.dto;

 import lombok.Data;
 import lombok.EqualsAndHashCode;
 import lombok.NoArgsConstructor;
 import lombok.ToString;

 import java.io.Serializable;

 @Data
 @ToString
 @EqualsAndHashCode
 @NoArgsConstructor
 public class UserLoginLogDTO implements Serializable {

     private static final long serialVersionUID = 1L;

     private Long id;

     private Long userId;

     private Integer fromId;

     private String channelId;

     private String platformInfo;

     private String terminalVersions;

     private String appVersions;

     private String deviceId;

     private String ip;

     private String mac;

     private String gps;

     private String loginDetail;

     private java.util.Date addTime;

 }