package com.hz.world.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hz.world.core.dao.mapper.UserCollectLogMapper;
import com.hz.world.core.dao.model.UserCollectLog;
import com.hz.world.core.dao.model.UserCollectLogExample;


/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 22, 2020 
 */
@Repository
public class UserCollectLogDaoImpl {
    @Autowired
    private UserCollectLogMapper userCollectLogMapper;
    
  
    public boolean insert(UserCollectLog record) {
		return userCollectLogMapper.insertSelective(record) > 0;
    }

    public List<UserCollectLog> getUserFinishList(Long userId, Integer type){
    	UserCollectLogExample example = new UserCollectLogExample();
    	UserCollectLogExample.Criteria criteria = example.createCriteria();
    	criteria.andUserIdEqualTo(userId);
    	criteria.andTypeEqualTo(type);
    	return userCollectLogMapper.selectByExample(example);
    }


}
