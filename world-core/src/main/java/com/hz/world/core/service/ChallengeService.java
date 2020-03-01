/**
 * Java Framework Auto Generator Tools
 * Copyright (c) 2015-2020 www.wifi.com
 *
 * @company wifi
 */
package com.hz.world.core.service;

import com.hz.world.common.dto.ResultDTO;
import com.hz.world.core.domain.dto.ChallengeDTO;

import java.util.List;


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
    ResultDTO<String> challenge(Long userId, Integer element,Integer elementWeight, Integer totalWeight);

    public List<ChallengeDTO> userChallengeElementList(Long userId, Integer element);
    public List<Integer> userAchivedChallenge(Long userId);
}
