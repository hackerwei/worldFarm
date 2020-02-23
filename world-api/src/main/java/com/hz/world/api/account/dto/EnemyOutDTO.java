package com.hz.world.api.account.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * @outhor lujian
 * @create 2018-06-29 16:10
 */
@Data
public class EnemyOutDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId; //用户ID
    private String nickname; //用户昵称
    private Integer  userLevel; //猫爪数
    private Integer gender; // 性别
    private String headImg; // 头像完整地址
    private String info; //提示
    private Integer breakCount; //玩家捣乱次数
    private boolean isOffer; //是否处于悬赏状态
    
   
    
   
}
