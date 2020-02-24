package com.hz.world.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hz.world.core.dao.mapper.UserTotalIncomeMapper;
import com.hz.world.core.dao.model.UserTotalIncome;
import com.hz.world.core.dao.model.UserTotalIncomeExample;


/**  
* <p>Title: UserTotalIncomeDaoImpl</p>  
* <p>Description: </p>  
* @author linyanchun  
* @date 2018年9月4日  
*/  
@Repository
public class UserTotalIncomeDaoImpl {
    @Autowired
    private UserTotalIncomeMapper userTotalIncomeMapper;
    
  
    public UserTotalIncome findByUserId(Long userId) {
        return userTotalIncomeMapper.selectByPrimaryKey(userId);
    }
   
    public boolean update(UserTotalIncome userTotalIncome) {
        return userTotalIncomeMapper.updateByPrimaryKeySelective(userTotalIncome) > 0;
    }

    public boolean insert(UserTotalIncome userTotalIncome) {
        return userTotalIncomeMapper.insertSelective(userTotalIncome) > 0;
    }

    public List<UserTotalIncome> getUserList(){
    	UserTotalIncomeExample example = new UserTotalIncomeExample();
    	example.setOrderByClause("income desc");
    	example.setMysqlLength(100);
    	return userTotalIncomeMapper.selectByExample(example);
    }
}
