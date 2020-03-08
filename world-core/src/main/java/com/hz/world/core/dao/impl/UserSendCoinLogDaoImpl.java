package com.hz.world.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hz.world.common.util.DateUtil;
import com.hz.world.core.dao.mapper.UserSendCoinLogMapper;
import com.hz.world.core.dao.model.UserSendCoinLog;
import com.hz.world.core.dao.model.UserSendCoinLogExample;


 
/**  
* <p>Title: UserSendCoinLogDaoImpl</p>  
* <p>Description:用户奖励获取记录 </p>  
* @author linyanchun  
* @date 2018年8月26日  
*/  
@Repository
public class UserSendCoinLogDaoImpl {
    @Autowired
    private UserSendCoinLogMapper userSendCoinLogMapper;
    
   
   
    
    public boolean insert(UserSendCoinLog record) {
    		return userSendCoinLogMapper.insertSelective(record) > 0;
    }
    public boolean update(UserSendCoinLog record) {
		return userSendCoinLogMapper.updateByPrimaryKeySelective(record) > 0;
    }
    public UserSendCoinLog findById(Long id) {
    		return userSendCoinLogMapper.selectByPrimaryKey(id);
    }
    public boolean isSenddToday(Long userId, Long toUid) {
    	UserSendCoinLogExample example = new UserSendCoinLogExample();
    	UserSendCoinLogExample.Criteria criteria = example.createCriteria();
    	criteria.andUserIdEqualTo(userId);
    	criteria.andToUserIdEqualTo(toUid);
    	criteria.andDateEqualTo(DateUtil.getTodayDate());
    	return userSendCoinLogMapper.countByExample(example) > 0;
    	
    }
    
    public  UserSendCoinLog getUnReadToday(Long userId) {
    	UserSendCoinLogExample example = new UserSendCoinLogExample();
    	UserSendCoinLogExample.Criteria criteria = example.createCriteria();
    	criteria.andToUserIdEqualTo(userId);
    	criteria.andDateEqualTo(DateUtil.getTodayDate());
    	criteria.andStatusEqualTo(0);
    	List<UserSendCoinLog> list = userSendCoinLogMapper.selectByExample(example) ;
    	if (list != null && list.size() > 0) {
			return list.get(0);
		}
    	return null;
    }
    
}
