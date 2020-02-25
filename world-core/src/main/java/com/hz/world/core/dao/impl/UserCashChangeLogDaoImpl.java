package com.hz.world.core.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hz.world.core.dao.mapper.UserCashChangeLogMapper;
import com.hz.world.core.dao.model.UserCashChangeLog;


/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 22, 2020 
 */
@Repository
public class UserCashChangeLogDaoImpl {
    @Autowired
    private UserCashChangeLogMapper userCashChangeLogMapper;
    
  
    public boolean insert(UserCashChangeLog record) {
		return userCashChangeLogMapper.insertSelective(record) > 0;
    }



}
