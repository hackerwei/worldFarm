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
    //总的挑战数目
	public Integer userTotalChallenges();
    // 已经达成挑战数据
	public Integer userAchivedChallenges(Long userId);
	// 找出当前元素的下一个挑战weight
    public Integer nextWeight(Long userId, Integer element);

    /**
     * 当前完成的元素最大的挑战
     * @param userId
     * @param element
     * @return
     */
    public Integer maxFinishedChallenge(Long userId, Integer element);
}
