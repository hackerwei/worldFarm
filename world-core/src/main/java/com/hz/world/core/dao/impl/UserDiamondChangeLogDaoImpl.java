package com.hz.world.core.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hz.world.core.dao.mapper.UserDiamondChangeLogMapper;
import com.hz.world.core.dao.model.UserDiamondChangeLog;


/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 22, 2020 
 */
@Repository
public class UserDiamondChangeLogDaoImpl {
    @Autowired
    private UserDiamondChangeLogMapper userDiamondChangeLogMapper;
    
  
    public boolean insert(UserDiamondChangeLog record) {
		return userDiamondChangeLogMapper.insertSelective(record) > 0;
    }



}
