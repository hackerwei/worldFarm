package com.hz.world.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hz.world.core.dao.mapper.UserCashChangeLogMapper;
import com.hz.world.core.dao.model.UserCashChangeLog;
import com.hz.world.core.dao.model.UserCashChangeLogExample;


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

    public List<UserCashChangeLog> getChangeList(Long userId, Integer offset, Integer limit){
    	UserCashChangeLogExample example = new UserCashChangeLogExample();
    	UserCashChangeLogExample.Criteria criteria = example.createCriteria();
    	criteria.andUserIdEqualTo(userId);
    	example.setOrderByClause("add_time desc");
    	example.setMysqlOffset(offset);
    	example.setMysqlLength(limit);
    	return userCashChangeLogMapper.selectByExample(example);
    }

}
