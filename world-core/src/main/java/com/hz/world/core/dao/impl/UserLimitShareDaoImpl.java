package com.hz.world.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hz.world.core.dao.mapper.UserLimitShareMapper;
import com.hz.world.core.dao.model.UserLimitShare;
import com.hz.world.core.dao.model.UserLimitShareExample;


/**  
* <p>Title: UserLimitShareDaoImpl</p>  
* <p>Description: </p>  
* @author linyanchun  
* @date 2018年9月4日  
*/  
@Repository
public class UserLimitShareDaoImpl {
    @Autowired
    private UserLimitShareMapper userLimitShareMapper;
    
    public int getCount(Long userId) {
    	UserLimitShareExample example = new UserLimitShareExample();
    	UserLimitShareExample.Criteria criteria = example.createCriteria();
    	criteria.andUserIdEqualTo(userId);
    	return userLimitShareMapper.countByExample(example);
    }
    public List<UserLimitShare> getList(Long userId, Integer offset, Integer limit) {
    	UserLimitShareExample example = new UserLimitShareExample();
    	UserLimitShareExample.Criteria criteria = example.createCriteria();
    	criteria.andUserIdEqualTo(userId);
    	example.setOrderByClause("add_time desc");
    	example.setMysqlOffset(offset);
    	example.setMysqlLength(limit);
    	return userLimitShareMapper.selectByExample(example);
    }
    public List<UserLimitShare> getUnfinishedList(Long userId) {
    	UserLimitShareExample example = new UserLimitShareExample();
    	UserLimitShareExample.Criteria criteria = example.createCriteria();
    	criteria.andUserIdEqualTo(userId);
    	
    	example.setOrderByClause("add_time desc");
    	
    	return userLimitShareMapper.selectByExample(example);
    }
    public UserLimitShare getLast(Long userId) {
    	UserLimitShareExample example = new UserLimitShareExample();
    	UserLimitShareExample.Criteria criteria = example.createCriteria();
    	criteria.andUserIdEqualTo(userId);
    	example.setOrderByClause("add_time desc");	
    	List<UserLimitShare> list =  userLimitShareMapper.selectByExample(example);
    	if (list != null && list.size() > 0) {
			return list.get(0);
		}
    	return null;
    }
    public boolean update(UserLimitShare userLimitShare) {
        return userLimitShareMapper.updateByPrimaryKeySelective(userLimitShare) > 0;
    }

    public boolean insert(UserLimitShare userLimitShare) {
        return userLimitShareMapper.insertSelective(userLimitShare) > 0;
    }

}
