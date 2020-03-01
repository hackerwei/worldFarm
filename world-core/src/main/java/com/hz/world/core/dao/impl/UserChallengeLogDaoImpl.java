package com.hz.world.core.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hz.world.core.dao.mapper.UserChallengeLogMapper;
import com.hz.world.core.dao.model.UserChallengeLog;


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

    


}
