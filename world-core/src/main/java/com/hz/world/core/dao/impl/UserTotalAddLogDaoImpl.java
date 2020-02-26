package com.hz.world.core.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hz.world.core.dao.mapper.UserTotalAddLogMapper;
import com.hz.world.core.dao.model.UserTotalAddLog;


/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 22, 2020 
 */
@Repository
public class UserTotalAddLogDaoImpl {
    @Autowired
    private UserTotalAddLogMapper userTotalAddLogMapper;
    
  
    public boolean insert(UserTotalAddLog record) {
		return userTotalAddLogMapper.insertSelective(record) > 0;
    }



}
