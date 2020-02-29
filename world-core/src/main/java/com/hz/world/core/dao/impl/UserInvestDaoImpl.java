package com.hz.world.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hz.world.core.dao.mapper.UserInvestMapper;
import com.hz.world.core.dao.model.UserInvest;
import com.hz.world.core.dao.model.UserInvestExample;


/**  
* <p>Title: UserInvestDaoImpl</p>  
* <p>Description: </p>  
* @author linyanchun  
* @date 2018年9月4日  
*/  
@Repository
public class UserInvestDaoImpl {
    @Autowired
    private UserInvestMapper userInvestMapper;
    
  
    public UserInvest findByUserId(Long userId,Integer investId) {
    	UserInvestExample example = new UserInvestExample();
    	UserInvestExample.Criteria criteria = example.createCriteria();
    	criteria.andUserIdEqualTo(userId);
    	criteria.andInvestIdEqualTo(investId);
    	List<UserInvest> list = userInvestMapper.selectByExample(example);
    	if (list != null && list.size() > 0) {
			return list.get(0);
		}
        return null;
    }
   
    public List<UserInvest> getUserInvestList(Long userId){
    	UserInvestExample example = new UserInvestExample();
    	UserInvestExample.Criteria criteria = example.createCriteria();
    	criteria.andUserIdEqualTo(userId);
    	return userInvestMapper.selectByExample(example);
    	
    }
    public boolean update(UserInvest userInvest) {
    	UserInvestExample example = new UserInvestExample();
    	UserInvestExample.Criteria criteria = example.createCriteria();
    	criteria.andUserIdEqualTo(userInvest.getUserId());
    	criteria.andInvestIdEqualTo(userInvest.getInvestId());
        return userInvestMapper.updateByExampleSelective(userInvest, example)> 0;
    }

    public boolean insert(UserInvest userInvest) {
        return userInvestMapper.insertSelective(userInvest) > 0;
    }

}
