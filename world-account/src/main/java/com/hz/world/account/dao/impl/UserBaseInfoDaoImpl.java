package com.hz.world.account.dao.impl;

import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hz.world.account.dao.mapper.UserBaseInfoExtMapper;
import com.hz.world.account.dao.mapper.UserBaseInfoMapper;
import com.hz.world.account.dao.model.UserBaseInfo;
import com.hz.world.account.dao.model.UserBaseInfoExample;


/**  
* <p>Title: UserBaseInfoDaoImpl</p>  
* <p>Description: </p>  
* @author linyanchun  
* @date 2018年9月4日  
*/  
@Repository
public class UserBaseInfoDaoImpl {
    @Autowired
    private UserBaseInfoMapper userBaseInfoMapper;
    
    @Autowired
    private UserBaseInfoExtMapper userBaseInfoExtMapper;
  

    public UserBaseInfo findByUserId(Long userId) {
        return userBaseInfoMapper.selectByPrimaryKey(userId);
    }
   
    public List<UserBaseInfo> findByNickname(String nickname) {
        UserBaseInfoExample example = new UserBaseInfoExample();
        UserBaseInfoExample.Criteria criteria = example.createCriteria();
        criteria.andNicknameEqualTo(nickname);
        return userBaseInfoMapper.selectByExample(example);
    }

    public List<UserBaseInfo> findByUserIds(List<Long> userIds) {
        UserBaseInfoExample example = new UserBaseInfoExample();
        UserBaseInfoExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdIn(userIds);
        return userBaseInfoMapper.selectByExample(example);
    }

    public boolean isExistNickname(String nickname) {
        UserBaseInfoExample example = new UserBaseInfoExample();
        UserBaseInfoExample.Criteria criteria = example.createCriteria();
        criteria.andNicknameEqualTo(nickname);
        return userBaseInfoMapper.countByExample(example) > 0;
    }
    
    public boolean addUserLevel(Long userId) {
    		return userBaseInfoExtMapper.addUserLevel(userId) > 0;
    }
    public boolean update(UserBaseInfo userBaseInfo) {
        return userBaseInfoMapper.updateByPrimaryKeySelective(userBaseInfo) > 0;
    }

    public boolean insert(UserBaseInfo userBaseInfo) {
        return userBaseInfoMapper.insertSelective(userBaseInfo) > 0;
    }

    public String checkAndReturnNickname(String nickname) {
        if (nickname != null && nickname.trim().length() > 20) {
            nickname = nickname.trim().substring(0, 20);
        }
        UserBaseInfoExample example = new UserBaseInfoExample();
        UserBaseInfoExample.Criteria criteria = example.createCriteria();
        criteria.andNicknameEqualTo(nickname);
        boolean isUsed = userBaseInfoMapper.countByExample(example) > 0 ? true : false; // 检查昵称是否存在，true：是，false：否
        String result = nickname;
        if (isUsed) { // 昵称如果存在，在昵称上加3位随机数
            result = result + RandomUtils.nextInt(100, 999); // 随机产生3位数字
        }
        return result;
    }
    
  
    public int getInviteCount(Long userId) {
    		UserBaseInfoExample example = new UserBaseInfoExample();
        UserBaseInfoExample.Criteria criteria = example.createCriteria();
        criteria.andFromUserIdEqualTo(userId);
        return userBaseInfoMapper.countByExample(example);
    }
    public boolean updateUserCash(Long userId, Integer num) {
    	return userBaseInfoExtMapper.updateUserCash(userId, num) >0;
    }
    

    public boolean updateUserDiamond(Long userId, Integer num) {
    	return userBaseInfoExtMapper.updateUserDiamond(userId, num) >0;
    }
    public List<UserBaseInfo> getFriendFriendList(List<Long> userList, Integer offset, Integer limit) {
        UserBaseInfoExample example = new UserBaseInfoExample();
        UserBaseInfoExample.Criteria criteria = example.createCriteria();
        criteria.andFromUserIdIn(userList);
        criteria.andActiveEqualTo(1);
        example.setOrderByClause("create_time desc");
        example.setMysqlOffset(offset);
        example.setMysqlLength(limit);
        return userBaseInfoMapper.selectByExample(example);
    }
    public List<UserBaseInfo> getUserFriendList(Long userId, Integer offset, Integer limit) {
        UserBaseInfoExample example = new UserBaseInfoExample();
        UserBaseInfoExample.Criteria criteria = example.createCriteria();
        criteria.andFromUserIdEqualTo(userId);
        criteria.andActiveEqualTo(1);
        example.setOrderByClause("create_time desc");
        example.setMysqlOffset(offset);
        example.setMysqlLength(limit);
        return userBaseInfoMapper.selectByExample(example);
    }
    public List<UserBaseInfo> getUnActiveFriendList(Long userId, Integer offset, Integer limit) {
        UserBaseInfoExample example = new UserBaseInfoExample();
        UserBaseInfoExample.Criteria criteria = example.createCriteria();
        criteria.andFromUserIdEqualTo(userId);
        criteria.andActiveEqualTo(0);
        example.setOrderByClause("create_time asc");
        example.setMysqlOffset(offset);
        example.setMysqlLength(limit);
        return userBaseInfoMapper.selectByExample(example);
    }
}
