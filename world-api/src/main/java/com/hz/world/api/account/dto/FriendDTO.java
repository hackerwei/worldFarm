package com.hz.world.api.account.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * @outhor lujian
 * @create 2018-06-29 16:10
 */
@Data
public class FriendDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId; //用户ID
    private String nickname; //用户昵称
    private Integer  userLevel; //猫爪数
    private Integer gender; // 性别
    private String headImg; // 头像完整地址
   
    private boolean isDoubleFriend; //是否相互好友
    
    private boolean isOnLine;  //是否在线
    
    private String offLineTime; //离线时间
    
    private String location; //位置
    
    private Integer itemStatus; //icon显示的状态
    
   
}
