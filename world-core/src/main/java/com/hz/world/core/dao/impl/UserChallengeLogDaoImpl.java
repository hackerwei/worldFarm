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

    public List<UserChallengeLog> getUserFinishedChallenges(Long userID, Integer element)
    {
        UserChallengeLogExample example = new UserChallengeLogExample();
        UserChallengeLogExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userID);
        criteria.andElementEqualTo(element);
        return userChallengeLogMapper.selectByExample(example);
    }


}
