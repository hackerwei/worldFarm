package com.hz.world.core.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hz.world.core.dao.mapper.UserRewardLogMapper;
import com.hz.world.core.dao.model.UserRewardLog;


 
/**  
* <p>Title: UserRewardLogDaoImpl</p>  
* <p>Description:用户奖励获取记录 </p>  
* @author linyanchun  
* @date 2018年8月26日  
*/  
@Repository
public class UserRewardLogDaoImpl {
    @Autowired
    private UserRewardLogMapper userRewardLogMapper;
    
   
   
    
    public boolean insert(UserRewardLog record) {
    		return userRewardLogMapper.insertSelective(record) > 0;
    }
    
    public UserRewardLog findById(Long id) {
    		return userRewardLogMapper.selectByPrimaryKey(id);
    }
    
}
