package com.hz.world.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hz.world.core.dao.mapper.UserUnionIncomeLogMapper;
import com.hz.world.core.dao.model.UserUnionIncomeLog;
import com.hz.world.core.dao.model.UserUnionIncomeLogExample;


/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 22, 2020 
 */
@Repository
public class UserUnionIncomeLogDaoImpl {
    @Autowired
    private UserUnionIncomeLogMapper userUnionIncomeLogMapper;
    
  
    public boolean insert(UserUnionIncomeLog record) {
		return userUnionIncomeLogMapper.insertSelective(record) > 0;
    }
    public List<UserUnionIncomeLog> getLogList(Long userId, Integer offset, Integer limit){
    	UserUnionIncomeLogExample example = new UserUnionIncomeLogExample();
    	UserUnionIncomeLogExample.Criteria criteria = example.createCriteria();
    	criteria.andUserIdEqualTo(userId);
    	example.setOrderByClause("date desc");
    	example.setMysqlOffset(offset);
    	example.setMysqlLength(limit);
    	return userUnionIncomeLogMapper.selectByExample(example);
    }


}
