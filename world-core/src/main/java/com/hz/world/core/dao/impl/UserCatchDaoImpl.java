package com.hz.world.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hz.world.core.dao.mapper.UserCatchMapper;
import com.hz.world.core.dao.model.UserCatch;
import com.hz.world.core.dao.model.UserCatchExample;


/**  
* <p>Title: UserCatchDaoImpl</p>  
* <p>Description: </p>  
* @author linyanchun  
* @date 2018年9月4日  
*/  
@Repository
public class UserCatchDaoImpl {
    @Autowired
    private UserCatchMapper userCatchMapper;
    
  
    public UserCatch findByUserId(Long userId,Integer year) {
        return userCatchMapper.selectByPrimaryKey(userId, year);
    }
   
    public List<UserCatch> getUserCatchList(Long userId){
    	UserCatchExample example = new UserCatchExample();
    	UserCatchExample.Criteria criteria = example.createCriteria();
    	criteria.andUserIdEqualTo(userId);
    	return userCatchMapper.selectByExample(example);
    	
    }
    public boolean update(UserCatch userCatch) {
        return userCatchMapper.updateByPrimaryKeySelective(userCatch) > 0;
    }

    public boolean insert(UserCatch userCatch) {
        return userCatchMapper.insertSelective(userCatch) > 0;
    }

}
