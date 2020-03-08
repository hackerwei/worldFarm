package com.hz.world.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hz.world.core.dao.mapper.UserElementManagerMapper;
import com.hz.world.core.dao.model.UserElementManager;
import com.hz.world.core.dao.model.UserElementManagerExample;


/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 22, 2020 
 */
@Repository
public class UserElementManagerDaoImpl {
    @Autowired
    private UserElementManagerMapper userElementManagerMapper;
  
    public UserElementManager findUserElementManager(Long userId,Integer element) {
        return userElementManagerMapper.selectByPrimaryKey(element, userId);
    }
    public UserElementManager findBossManager(Long userId,Long bossId) {
    	UserElementManagerExample example = new UserElementManagerExample();
    	UserElementManagerExample.Criteria criteria = example.createCriteria();
    	criteria.andUserIdEqualTo(bossId);
    	criteria.andManagerEqualTo(userId);
    	List<UserElementManager> list = userElementManagerMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
			return list.get(0);
		}
    	return null;
    }
    public boolean update(UserElementManager userElementManager) {
        return userElementManagerMapper.updateByPrimaryKeySelective(userElementManager) > 0;
    }

    public boolean insert(UserElementManager userElementManager) {
        return userElementManagerMapper.insertSelective(userElementManager) > 0;
    }
    public List<UserElementManager> getElementManagerList(Long userId){
    	UserElementManagerExample example = new UserElementManagerExample();
    	UserElementManagerExample.Criteria criteria = example.createCriteria();
    	criteria.andUserIdEqualTo(userId);
    	return  userElementManagerMapper.selectByExample(example);
    }
    public List<UserElementManager> getBossList(Long userId){
    	UserElementManagerExample example = new UserElementManagerExample();
    	UserElementManagerExample.Criteria criteria = example.createCriteria();
    	criteria.andManagerEqualTo(userId);
    	return  userElementManagerMapper.selectByExample(example);
    }
    public boolean isLimited(Long userId) {
    	UserElementManagerExample example = new UserElementManagerExample();
    	UserElementManagerExample.Criteria criteria = example.createCriteria();
    	criteria.andManagerEqualTo(userId);
    	return userElementManagerMapper.countByExample(example) >0;
    }
    
    public boolean delete(Long userId,Integer element) {
    	return userElementManagerMapper.deleteByPrimaryKey(element, userId) > 0;
    }
    
   
}
