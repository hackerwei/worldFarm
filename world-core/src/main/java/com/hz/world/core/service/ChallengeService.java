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
 * date Mar 1, 2020 
 */
public interface ChallengeService {


  
    /**
     * 挑战
     * @param userId
     * @param element  元素id
     * @param elementWeight   元素当前体重
     * @param totalWeight    总的体重
     * @return
     */
    ResultDTO<String> challange(Long userId, Integer element,Integer elementWeight, Integer totalWeight);
    
    
    
 
  
}
