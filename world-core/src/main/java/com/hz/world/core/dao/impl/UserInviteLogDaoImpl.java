package com.hz.world.core.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hz.world.common.util.DateUtil;
import com.hz.world.core.dao.mapper.UserInviteLogMapper;
import com.hz.world.core.dao.model.UserInviteLog;
import com.hz.world.core.dao.model.UserInviteLogExample;


/**  
* <p>Title: UseInviteLogDaoImpl</p>  
* <p>Description:用户邀请日志 </p>  
* @author linyanchun  
* @date 2018年8月25日  
*/  
@Repository
public class UserInviteLogDaoImpl {
    @Autowired
    private UserInviteLogMapper userInviteLogMapper;
   

    public UserInviteLog findById(Long id) {
        return userInviteLogMapper.selectByPrimaryKey(id);
    }

    public boolean update(UserInviteLog userInviteLog) {
        return userInviteLogMapper.updateByPrimaryKey(userInviteLog) > 0;
    }

    public boolean insert(UserInviteLog userInviteLog) {
        return userInviteLogMapper.insertSelective(userInviteLog) > 0;
    }
    
    public long getTotalInviteCount(Long userId) {
    	UserInviteLogExample example = new UserInviteLogExample();
		UserInviteLogExample.Criteria  criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		return userInviteLogMapper.countByExample(example);
    } 
   
    public boolean checkIsInvited(Long userId, Long invited) {
    		UserInviteLogExample example = new UserInviteLogExample();
    		UserInviteLogExample.Criteria  criteria = example.createCriteria();
    		criteria.andUserIdEqualTo(userId);
    		criteria.andInviteUserIdEqualTo(invited);
    		return userInviteLogMapper.countByExample(example) > 0;
    }
    public int getTotalCount(Long userId) {
    	UserInviteLogExample example = new UserInviteLogExample();
		UserInviteLogExample.Criteria  criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		return userInviteLogMapper.countByExample(example);
    }
    public UserInviteLog getFirstInviteLog (Long userId) {
    	UserInviteLogExample example = new UserInviteLogExample();
		UserInviteLogExample.Criteria  criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		example.setOrderByClause("add_time asc");
		example.setMysqlLength(1);
		List<UserInviteLog> list = userInviteLogMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}else {
			return null;
		}
    }
    public List<UserInviteLog> getUserInviteList(Long userId,Integer count) {
    	UserInviteLogExample example = new UserInviteLogExample();
		UserInviteLogExample.Criteria  criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		example.setOrderByClause("add_time asc");
		example.setMysqlLength(count);
		return userInviteLogMapper.selectByExample(example);
    }

    public UserInviteLog getInviteLog (Long userId, Long inviteUserId) {
    	UserInviteLogExample example = new UserInviteLogExample();
		UserInviteLogExample.Criteria  criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andInviteUserIdEqualTo(inviteUserId);
		example.setOrderByClause("add_time asc");
		example.setMysqlLength(1);
		List<UserInviteLog> list = userInviteLogMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}else {
			return null;
		}
    }
}
