package com.hz.world.core.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hz.world.core.dao.mapper.UserCoinMapper;
import com.hz.world.core.dao.model.UserCoin;


/**  
* <p>Title: UserCoinDaoImpl</p>  
* <p>Description: </p>  
* @author linyanchun  
* @date 2018年9月4日  
*/  
@Repository
public class UserCoinDaoImpl {
    @Autowired
    private UserCoinMapper userCoinMapper;
    
  
    public UserCoin findByUserId(Long userId) {
        return userCoinMapper.selectByPrimaryKey(userId);
    }
   
    public boolean update(UserCoin userCoin) {
        return userCoinMapper.updateByPrimaryKeySelective(userCoin) > 0;
    }

    public boolean insert(UserCoin userCoin) {
        return userCoinMapper.insertSelective(userCoin) > 0;
    }

}
