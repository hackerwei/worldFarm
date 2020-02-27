/**
 * Java Framework Auto Generator Tools
 * Copyright (c) 2015-2020 www.wifi.com
 *
 * @company wifi
 */
package com.hz.world.core.service;


/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 27, 2020 
 */
public interface UserRewardService {

	
	/**
	 * 用户领取奖励
	 * @param userId
	 * @param rewardType
	 * @param relatedId
	 * @param rewardCount
	 * @param rewardMessage
	 * @return
	 */
	boolean createUserRewardLog(Long userId, Integer rewardType, Integer relatedId, String rewardCount,String rewardMessage);

}
