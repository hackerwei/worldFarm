package com.hz.world.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hz.world.core.dao.mapper.UserWorshipMapper;
import com.hz.world.core.dao.model.UserWorship;
import com.hz.world.core.dao.model.UserWorshipExample;


/**  
* <p>Title: UserWorshipDaoImpl</p>  
* <p>Description: </p>  
* @author linyanchun  
* @date 2018年9月4日  
*/  
@Repository
public class UserWorshipDaoImpl {
    @Autowired
    private UserWorshipMapper userWorshipMapper;
   
  
    public UserWorship findByUserId(Long userId) {
        return userWorshipMapper.selectByPrimaryKey(userId);
    }
   
    public List<UserWorship> getUserWorshipList(Long userId){
    	UserWorshipExample example = new UserWorshipExample();
    	UserWorshipExample.Criteria criteria = example.createCriteria();
    	criteria.andUserIdEqualTo(userId);
    	return userWorshipMapper.selectByExample(example);
    	
    }
   
    public boolean update(UserWorship userWorship) {
        return userWorshipMapper.updateByPrimaryKeySelective(userWorship) > 0;
    }

    public boolean insert(UserWorship userWorship) {
        return userWorshipMapper.insertSelective(userWorship) > 0;
    }
    
   

}
