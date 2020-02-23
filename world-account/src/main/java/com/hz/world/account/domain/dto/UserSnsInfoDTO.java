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
 public class UserSnsInfoDTO implements Serializable {

     private static final long serialVersionUID = 1L;

     public static final String PACKAGE_NAME = "com.wifi.dance.account.domain.vo.UserSnsInfo";

     private Long userId;

     private String openId;

     private String token;

     private Integer expiresToken;

     private String creator;

     private java.util.Date addTime;

     private java.util.Date updateTime;

     private String isDeleted;
     
     private String sig;

 }