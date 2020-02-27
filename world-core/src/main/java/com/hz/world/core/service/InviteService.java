/**
 * Java Framework Auto Generator Tools
 * Copyright (c) 2015-2020 www.wifi.com
 *
 * @company wifi
 */
package com.hz.world.core.service;

import java.util.List;

import com.hz.world.common.dto.ResultDTO;
import com.hz.world.core.domain.dto.UserInviteDTO;


/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 27, 2020 
 */
public interface InviteService {


    
 
    /**
     * 分享邀请
     * @param userId
     * @param invited
     * @return
     */
    ResultDTO<String> inviteUser(Long userId, Long invited);
    
    

  
    /**
     * 获得用户邀请列表
     * @param userId
     * @return
     */
    List<UserInviteDTO> getUserInviteList(Long userId, Integer count);
    

    /**
     * 获取用户邀请的用户数
     * @param userId
     * @return
     */
    int getInviteCount(Long userId);
    

    

    /**
     * 获取邀请奖励
     * @param userId
     * @param invited
     * @return
     */
    ResultDTO<String> getInviteReward(Long userId, Long invited);
    
 
  
}
