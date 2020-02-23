package com.hz.world.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.ResourceTransactionManager;

import com.hz.world.core.dao.mapper.UserElementLogMapper;
import com.hz.world.core.dao.model.UserElement;
import com.hz.world.core.dao.model.UserElementExample;
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
