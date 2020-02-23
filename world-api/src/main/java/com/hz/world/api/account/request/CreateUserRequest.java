package com.hz.world.api.account.request;

import com.hz.world.api.common.domain.PageRequest;

import lombok.Data;

/**
 * @outhor linyanchun
 * @create 2018-06-28 16:06
 */
@Data
public class CreateUserRequest extends PageRequest{
    
    private Integer gender;//1为男性，0为女性
    private String nickname;
   
}