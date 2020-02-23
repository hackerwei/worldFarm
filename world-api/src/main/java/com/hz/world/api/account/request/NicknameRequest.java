package com.hz.world.api.account.request;

import com.hz.world.api.common.domain.PageRequest;

import lombok.Data;

/**
 * @outhor lujian
 * @create 2018-06-28 16:06
 */
@Data
public class NicknameRequest extends PageRequest  {
    
	private Long userId; //用户id
   
    private String nickname; //昵称
   
}