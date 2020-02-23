package com.hz.world.api.account.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * @outhor lujian
 * @create 2018-06-29 16:10
 */
@Data
public class RankingOutDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId; //用户ID
    private String nickname; //用户昵称
    private Integer gender; // 性别
    private String headImg; // 头像完整地址
    private Integer userLevel; // 用户等级
    private Integer rank;  //用户排名
    private boolean isLike; //是否点赞
    
   
}
