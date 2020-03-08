package com.hz.world.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hz.world.common.util.DateUtil;
import com.hz.world.core.dao.mapper.UserRequireCoinLogMapper;
import com.hz.world.core.dao.model.UserRequireCoinLog;
import com.hz.world.core.dao.model.UserRequireCoinLogExample;
import com.hz.world.core.dao.model.UserSendCoinLog;


 
/**  
* <p>Title: UserRequireCoinLogDaoImpl</p>  
* <p>Description:用户奖励获取记录 </p>  
* @author linyanchun  
* @date 2018年8月26日  
*/  
@Repository
public class UserRequireCoinLogDaoImpl {
    @Autowired
    private UserRequireCoinLogMapper userRequireCoinLogMapper;
    
   
   
    
    public boolean insert(UserRequireCoinLog record) {
    		return userRequireCoinLogMapper.insertSelective(record) > 0;
    }
    public boolean update(UserRequireCoinLog record) {
		return userRequireCoinLogMapper.updateByPrimaryKeySelective(record) > 0;
    }
    public UserRequireCoinLog findById(Long id) {
    		return userRequireCoinLogMapper.selectByPrimaryKey(id);
    }
    public boolean isRequiredToday(Long userId, Long toUid) {
    	UserRequireCoinLogExample example = new UserRequireCoinLogExample();
    	UserRequireCoinLogExample.Criteria criteria = example.createCriteria();
    	criteria.andUserIdEqualTo(userId);
    	criteria.andToUserIdEqualTo(toUid);
    	criteria.andDateEqualTo(DateUtil.getTodayDate());
    	return userRequireCoinLogMapper.countByExample(example) > 0;
    	
    }
    
    public  UserRequireCoinLog getUnReadToday(Long userId) {
    	UserRequireCoinLogExample example = new UserRequireCoinLogExample();
    	UserRequireCoinLogExample.Criteria criteria = example.createCriteria();
    	criteria.andToUserIdEqualTo(userId);
    	criteria.andDateEqualTo(DateUtil.getTodayDate());
    	criteria.andStatusEqualTo(0);
    	List<UserRequireCoinLog> list = userRequireCoinLogMapper.selectByExample(example) ;
    	if (list != null && list.size() > 0) {
			return list.get(0);
		}
    	return null;
    }
    
}
