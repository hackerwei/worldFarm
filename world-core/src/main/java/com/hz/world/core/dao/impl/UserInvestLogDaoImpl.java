package com.hz.world.core.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hz.world.core.dao.mapper.UserFortuneLogMapper;
import com.hz.world.core.dao.model.UserFortuneLog;


/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 22, 2020 
 */
@Repository
public class UserInvestLogDaoImpl {
    @Autowired
    private UserFortuneLogMapper userFortuneLogMapper;
    
  
    public boolean insert(UserFortuneLog record) {
		return userFortuneLogMapper.insertSelective(record) > 0;
    }
    

}
