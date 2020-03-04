package com.hz.world.account.service;


import java.util.List;

import com.hz.world.account.dao.model.UserBaseInfo;
import com.hz.world.account.domain.dto.UserBaseInfoDTO;

public interface UserBaseInfoService {

    boolean create(UserBaseInfoDTO entity);

    boolean update(UserBaseInfoDTO entity);

    UserBaseInfoDTO getByUserId(Long userId);
    

  

    /**
     * 判断用户的昵称是否可以存在，true：存在，false：不存在
     * <pre>
     * @param nickname
     * @return
     * Modifications:
     * Modifier linyanchun; 2018年6月3日; Create new Method isExistNickname
     * </pre>
     */
    boolean isExistNickname(String nickname);
    



    /**
     * 检查昵称是否存在，存在加三个随机数
     * <pre>
     * @param nickname
     * @return
     * Modifications:
     * Modifier linyanchun; 2018年6月3日; Create new Method checkAndReturnNickname
     * </pre>
     */
    String checkAndReturnNickname(String nickname);

    
    

    List<UserBaseInfo> findByNickname(String nickname);
   
 
    boolean addUserLevel(Long userId);

    /**
     * 获取用户邀请好友数
     * @param userId
     * @return
     */
    int getInviteCount(Long userId);
    
    /**
     * 更新用户现金
     * @param userId
     * @param num
     * @return
     */
    boolean updateUserCash(Long userId, Double num);
    
    /**
     * 更新用户钻石
     * @param userId
     * @param num
     * @return
     */
    boolean updateUserDiamond(Long userId, Integer num);
    
    /**
     * 更新用户体重
     * @param userId
     * @param num
     * @return
     */
    boolean updateUserWeight(Long userId, Integer num);
}
