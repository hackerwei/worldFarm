package com.hz.world.core.dao.impl;

import com.hz.world.core.dao.mapper.UserChallengeLogMapper;
import com.hz.world.core.dao.model.UserChallengeLog;
import com.hz.world.core.dao.model.UserChallengeLogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 22, 2020 
 */
@Repository
public class UserChallengeLogDaoImpl {
    @Autowired
    private UserChallengeLogMapper userChallengeLogMapper;
    
  
    public boolean insert(UserChallengeLog record) {
		return userChallengeLogMapper.insertSelective(record) > 0;
    }

    public int getTotalCnt(Long userID)
    {
        UserChallengeLogExample example = new UserChallengeLogExample();
        UserChallengeLogExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userID);
        return userChallengeLogMapper.countByExample(example);
    }
    // 返回某个element的达成挑战情况
    public List<UserChallengeLog> getUserFinishedChallenges(Long userID, Integer element)
    {
        UserChallengeLogExample example = new UserChallengeLogExample();
        UserChallengeLogExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userID);
        criteria.andElementEqualTo(element);
        return userChallengeLogMapper.selectByExample(example);
    }
    public int getUserFinishedCount(Long userId, Integer element) {
    	 UserChallengeLogExample example = new UserChallengeLogExample();
         UserChallengeLogExample.Criteria criteria = example.createCriteria();
         criteria.andUserIdEqualTo(userId);
         criteria.andElementEqualTo(element);
         return userChallengeLogMapper.countByExample(example);
    }
    // 返回所有达成挑战的情况
    public List<UserChallengeLog> getUserFinishedChallenges(Long userID)
    {
        UserChallengeLogExample example = new UserChallengeLogExample();
        UserChallengeLogExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userID);
        return userChallengeLogMapper.selectByExample(example);
    }
    // 返回当前最大达成成就的最大值
    public UserChallengeLog getUserMaxFinishedChallenge(Long userId, Integer element) {
    	UserChallengeLogExample example = new UserChallengeLogExample();
        UserChallengeLogExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andElementEqualTo(element);
        example.setOrderByClause("challenge_id desc");
        example.setMysqlLength(1);
        List<UserChallengeLog> list = userChallengeLogMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
			return list.get(0);
		}
        return null;
    }

}
