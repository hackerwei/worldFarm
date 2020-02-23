package com.hz.world.api.account.request;

import lombok.Data;

/**
 * @outhor lujian
 * @create 2018-06-28 16:06
 */
@Data
public class FlopRequest {
    
	private Long toId; //翻牌的用户id
   
    private Integer type; //0:看访客，1：看喜欢我的人
   
}