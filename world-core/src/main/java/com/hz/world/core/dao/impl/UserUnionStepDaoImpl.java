package com.hz.world.core.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hz.world.core.dao.mapper.UserUnionStepMapper;
import com.hz.world.core.dao.model.UserUnionStep;


/**  
* <p>Title: UserUnionStepDaoImpl</p>  
* <p>Description: </p>  
* @author linyanchun  
* @date 2018年9月4日  
*/  
@Repository
public class UserUnionStepDaoImpl {
    @Autowired
    private UserUnionStepMapper userUnionStepMapper;
    
  
    public UserUnionStep findByUserId(Long userId) {
        return userUnionStepMapper.selectByPrimaryKey(userId);
    }
   

    public boolean update(UserUnionStep userUnionStep) {
        return userUnionStepMapper.updateByPrimaryKeySelective(userUnionStep) > 0;
    }

    public boolean insert(UserUnionStep userUnionStep) {
        return userUnionStepMapper.insertSelective(userUnionStep) > 0;
    }

}
