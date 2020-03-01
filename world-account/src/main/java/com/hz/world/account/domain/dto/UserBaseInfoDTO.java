package com.hz.world.account.domain.dto;

import java.io.Serializable;
import java.security.interfaces.RSAMultiPrimePrivateCrtKey;

import lombok.Data;

/**
 * 用户基本信息DTO
 */
@Data
public class UserBaseInfoDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long userId;

    private String nickname;

    private String headImg;

    private Integer year;

    /**
     * 段位等级
     */
    private Integer level;

    private Integer weight;

    private Long fromUserId;

    private String inviteCode;

    /**
     * 钻石
     */
    private Integer diamond;

    /**
     * 0:未认证，1:已认证
     */
    private Integer auth;
    
    private Double cash;

    private Integer active;
    
    private Double score;
    
    private String qq;
    
    private String weixin;
    
}