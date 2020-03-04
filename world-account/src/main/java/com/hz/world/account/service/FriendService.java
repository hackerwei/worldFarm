package com.hz.world.account.service;


import java.util.List;

import com.hz.world.account.domain.dto.UserBaseDTO;

public interface FriendService {

    /**
     * 获取用户直接好友列表
     * @param userId
     * @param offset
     * @param limit
     * @return
     */
    List<UserBaseDTO> getUserFriendList(Long userId, Integer offset, Integer limit);
    
    /**
     * 获取用户间接好友列表
     * @param userId
     * @param offset
     * @param limit
     * @return
     */
    List<UserBaseDTO> getFriendFriendList(Long userId, Integer offset, Integer limit);
    
    /**
     * 未激活好友
     * @param userId
     * @param offset
     * @param limit
     * @return
     */
    List<UserBaseDTO> getUnActiveFriendList(Long userId, Integer offset, Integer limit);
    
    /**
     * 获取我的好友数量
     * @param userId
     * @return
     */
    int getFriendCount(Long userId);
    
    /**
     * 当日邀请好友数量
     * @param userId
     * @return
     */
    int getTodayFriendCount(Long userId);
    
 
    /**
     * 获取好友总重量
     * @param userId
     * @return
     */
    int getFriendWeight(Long userId);
    
    /**
     * 未认证的好友数量
     * @param userId
     * @return
     */
    int getUnAuthFriendCount(Long userId);
}
