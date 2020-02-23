package com.hz.world.core.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hz.world.core.dao.mapper.UserCoinChangeLogMapper;
import com.hz.world.core.dao.model.UserCoinChangeLog;


/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 22, 2020 
 */
@Repository
public class UserCoinChangeLogDaoImpl {
    @Autowired
    private UserCoinChangeLogMapper userCoinChangeLogMapper;
    
  
    public boolean insert(UserCoinChangeLog record) {
		return userCoinChangeLogMapper.insertSelective(record) > 0;
    }



}
