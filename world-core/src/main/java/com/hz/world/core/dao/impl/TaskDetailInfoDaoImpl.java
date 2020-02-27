package com.hz.world.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hz.world.core.dao.mapper.TaskDetailInfoMapper;
import com.hz.world.core.dao.model.TaskDetailInfo;
import com.hz.world.core.dao.model.TaskDetailInfoExample;


/**  
* <p>Title: TaskDetailInfoDaoImpl</p>  
* <p>Description: </p>  
* @author linyanchun  
* @date 2018年8月24日  
*/  
@Repository
public class TaskDetailInfoDaoImpl {
    @Autowired
    private TaskDetailInfoMapper taskDetailInfoMapper;
   
    public List<TaskDetailInfo> findAll() {
    		TaskDetailInfoExample example = new TaskDetailInfoExample();
        example.setOrderByClause("type asc");
        return taskDetailInfoMapper.selectByExample(example);
    }
    
    public TaskDetailInfo findById(Integer id) {
    		return taskDetailInfoMapper.selectByPrimaryKey(id);
    }
    
    public List<TaskDetailInfo> findByParentCode(String parentCode) {
		TaskDetailInfoExample example = new TaskDetailInfoExample();
		TaskDetailInfoExample.Criteria  criteria = example.createCriteria();
        criteria.andParentCodeEqualTo(parentCode);
        example.setOrderByClause("to_num asc");
	    return taskDetailInfoMapper.selectByExample(example);
    }
    public List<TaskDetailInfo> findByParentCodeOrder(String parentCode) {
		TaskDetailInfoExample example = new TaskDetailInfoExample();
		TaskDetailInfoExample.Criteria  criteria = example.createCriteria();
        criteria.andParentCodeEqualTo(parentCode);
	    return taskDetailInfoMapper.selectByExample(example);
    }
    public TaskDetailInfo findByCode(String code) {
		TaskDetailInfoExample example = new TaskDetailInfoExample();
		TaskDetailInfoExample.Criteria  criteria = example.createCriteria();
        criteria.andCodeEqualTo(code);
        List<TaskDetailInfo> result = taskDetailInfoMapper.selectByExample(example);
        if (result != null && result.size() > 0) {
			return result.get(0);
		}
	    return null;
    }

}
