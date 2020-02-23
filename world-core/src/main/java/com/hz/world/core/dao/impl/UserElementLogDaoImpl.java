package com.hz.world.core.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hz.world.core.dao.mapper.UserElementLogMapper;
import com.hz.world.core.dao.model.UserElementLog;


/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 22, 2020 
 */
@Repository
public class UserElementLogDaoImpl {
    @Autowired
    private UserElementLogMapper userElementLogMapper;
    
  
    public boolean insert(UserElementLog record) {
		return userElementLogMapper.insertSelective(record) > 0;
    }

    


}
