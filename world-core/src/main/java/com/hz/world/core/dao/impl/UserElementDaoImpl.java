package com.hz.world.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hz.world.core.dao.mapper.UserElementExtMapper;
import com.hz.world.core.dao.mapper.UserElementMapper;
import com.hz.world.core.dao.model.UserElement;
import com.hz.world.core.dao.model.UserElementExample;


/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 22, 2020 
 */
@Repository
public class UserElementDaoImpl {
    @Autowired
    private UserElementMapper userElementMapper;
    @Autowired
    private UserElementExtMapper userElementExtMapper;
  
    public UserElement findUserElement(Long userId,Integer element) {
        return userElementMapper.selectByPrimaryKey(element, userId);
    }
   
    public boolean update(UserElement userElement) {
        return userElementMapper.updateByPrimaryKeySelective(userElement) > 0;
    }
    public boolean delete(Long userId) {
    	UserElementExample example = new UserElementExample();
    	UserElementExample.Criteria criteria = example.createCriteria();
    	criteria.andUserIdEqualTo(userId);
    	return userElementMapper.deleteByExample(example) > 0;
    }

    public boolean insert(UserElement userElement) {
        return userElementMapper.insertSelective(userElement) > 0;
    }
    public List<UserElement> getElementList(Long userId){
    	UserElementExample example = new UserElementExample();
    	UserElementExample.Criteria criteria = example.createCriteria();
    	criteria.andUserIdEqualTo(userId);
    	return  userElementMapper.selectByExample(example);
    }
    //获取用户总的体重
    public int getTotalWeight(Long userId) {
    	return  userElementExtMapper.getTotalWeight(userId);
    }
    public boolean isAllUnlock(Long userId) {
    	UserElementExample example = new UserElementExample();
    	UserElementExample.Criteria criteria = example.createCriteria();
    	criteria.andUserIdEqualTo(userId);
    	return userElementMapper.countByExample(example) >= 10;
    }
}
