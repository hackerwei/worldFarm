package com.hz.world.core.dao.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hz.world.common.util.DateUtil;
import com.hz.world.core.dao.mapper.UserTaskLogMapper;
import com.hz.world.core.dao.model.UserTaskLog;
import com.hz.world.core.dao.model.UserTaskLogExample;

/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 27, 2020 
 */
@Repository
public class UserTaskLogDaoImpl {
    @Autowired
    private UserTaskLogMapper userTaskLogMapper;
    
   
   
    
    public boolean insert(UserTaskLog record) {
    		return userTaskLogMapper.insertSelective(record) > 0;
    }
    
    public int getTodayTaskCount(Long userId, Integer taskId) {
    		UserTaskLogExample example = new UserTaskLogExample();
		UserTaskLogExample.Criteria  criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andTaskIdEqualTo(taskId);
		criteria.andDateEqualTo(DateUtil.getTodayDate());
		criteria.andIsDeletedEqualTo("N");
		return userTaskLogMapper.countByExample(example);
    }
    public int getTodayCountByParentCode(Long userId, String parentCode) {
    		UserTaskLogExample example = new UserTaskLogExample();
		UserTaskLogExample.Criteria  criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andParentCodeEqualTo(parentCode);
		criteria.andDateEqualTo(DateUtil.getTodayDate());
		criteria.andIsDeletedEqualTo("N");
		return userTaskLogMapper.countByExample(example);
    }
    public boolean updateStatusById(Long id, Integer status) {
    		UserTaskLog record = new UserTaskLog();
    		record.setId(id);
    		record.setStatus(status);
    		return userTaskLogMapper.updateByPrimaryKeySelective(record) > 0;
    }
    public boolean updateStatus(Long userId, Integer taskId, Integer status) {
    		UserTaskLog record = new UserTaskLog();
    		record.setStatus(status);
    		UserTaskLogExample example = new UserTaskLogExample();
		UserTaskLogExample.Criteria  criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andTaskIdEqualTo(taskId);
		criteria.andIsDeletedEqualTo("N");
    		return userTaskLogMapper.updateByExampleSelective(record, example) > 0;
    }
    public boolean deleteTaskByCode(Long userId, String parentCode) {
    		UserTaskLog record = new UserTaskLog();
		record.setIsDeleted("Y");
		UserTaskLogExample example = new UserTaskLogExample();
		UserTaskLogExample.Criteria  criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andIsDeletedEqualTo("N");
		criteria.andParentCodeEqualTo(parentCode);
		return userTaskLogMapper.updateByExampleSelective(record, example) > 0;
    }
    public boolean batchDeleteTaskByCode( String parentCode) {
    		UserTaskLog record = new UserTaskLog();
		record.setIsDeleted("Y");
		UserTaskLogExample example = new UserTaskLogExample();
		UserTaskLogExample.Criteria  criteria = example.createCriteria();
		criteria.andIsDeletedEqualTo("N");
		criteria.andParentCodeEqualTo(parentCode);
		return userTaskLogMapper.updateByExampleSelective(record, example) > 0;
		
    }
    public List<UserTaskLog> getUserFinishedTask(Long userId, String parentCode) {
    		UserTaskLogExample example = new UserTaskLogExample();
		UserTaskLogExample.Criteria  criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andIsDeletedEqualTo("N");
		criteria.andParentCodeEqualTo(parentCode);
		return userTaskLogMapper.selectByExample(example);
    }
    public UserTaskLog getUserTask(Long userId, Integer taskId) {
    		UserTaskLogExample example = new UserTaskLogExample();
		UserTaskLogExample.Criteria  criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andTaskIdEqualTo(taskId);
		criteria.andIsDeletedEqualTo("N");
		List<UserTaskLog> list = userTaskLogMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
    }
    public UserTaskLog getUserTaskByDate(Long userId, Integer taskId, Date date) {
		UserTaskLogExample example = new UserTaskLogExample();
		UserTaskLogExample.Criteria  criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andTaskIdEqualTo(taskId);
		criteria.andDateEqualTo(date);
		criteria.andIsDeletedEqualTo("N");
		List<UserTaskLog> list = userTaskLogMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
    }

    /**
     * 用户已经获得奖励的任务
     * @param userId
     * @param parentCode
     * @return
     */
    public List<UserTaskLog> getUserRewardedTask(Long userId, String parentCode) {
		UserTaskLogExample example = new UserTaskLogExample();
		UserTaskLogExample.Criteria  criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andIsDeletedEqualTo("N");
		criteria.andStatusEqualTo(2);
		criteria.andParentCodeEqualTo(parentCode);
		return userTaskLogMapper.selectByExample(example);
    }
}
