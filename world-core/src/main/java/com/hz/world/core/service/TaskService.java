/**
Ø * Java Framework Auto Generator Tools
 * Copyright (c) 2015-2020 www.wifi.com
 *
 * @company wifi
 */
package com.hz.world.core.service;

import java.util.List;

import com.hz.world.core.dao.model.TaskDetailInfo;
import com.hz.world.core.dao.model.UserTaskLog;
import com.hz.world.core.domain.dto.TaskOutDTO;


/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 27, 2020 
 */
public interface TaskService {


    

    /**  
     * <p>Title: getTaskByParentCode</p>  
     * <p>Description: 根据父编码获取子任务列表</p>  
     * @param parentCode
     * @return  
     */  
    List<TaskDetailInfo> getTaskByParentCode(String parentCode);
    

    /**
     * 获取今天完成的任务数量
     * @param userId
     * @return
     */
    int getTodayFinishTaskCount(Long userId);
    

    /**  
     * <p>Title: getTaskByCode</p>  
     * <p>Description: 获取任务信息</p>  
     * @param code
     * @return  
     */  
    TaskDetailInfo getTaskByCode(String code);
    
   
    /**  
     * <p>Title: getTaskById</p>  
     * <p>Description:根据主键获取任务详情 </p>  
     * @param id
     * @return  
     */  
    TaskDetailInfo getTaskById(Integer id);
    
    /**  
     * <p>Title: finishTask</p>  
     * <p>Description: </p>  
     * @param userId
     * @param code
     * @return  
     */  
    boolean finishTask(Long userId, String code);
    

    /**
     * 完成梯度奖励
     * @param userId
     * @param code
     * @param toNum
     * @return
     */
    boolean finishProcessTask(Long userId, Integer taskId, Long toNum);
    
    /**  
     * <p>Title: processTask</p>  
     * <p>Description:更新任务进度 </p>  
     * @param userId
     * @param parentCode
     * @param toNum
     * @return  
     */  
    boolean processTask(Long userId, String parentCode, Long toNum);
    

    /**  
     * <p>Title: findTaskProcess</p>  
     * <p>Description: 查询用户的任务进度</p>  
     * @param userId
     * @param parentCode
     * @return  
     */  
    List<UserTaskLog> findTaskProcess(Long userId, String parentCode);
    

    /**  
     * <p>Title: findUserTaskLog</p>  
     * <p>Description: 获取用户任务状态</p>  
     * @param userId
     * @param taskId
     * @return  
     */  
    UserTaskLog findUserTaskLog(Long userId, Integer taskId);
    
    /**
     * 获取用户当天是否获取奖励
     * @param userId
     * @param taskId
     * @return
     */
    UserTaskLog findUserTaskLogToday(Long userId, Integer taskId);
    

    /**  
     * <p>Title: recvReward</p>  
     * <p>Description: 领取奖励</p>  
     * @param taskDetailInfo
     * @param userId
     * @return  
     */  
    boolean recvReward(TaskDetailInfo taskDetailInfo, Long userId);
    

    /**  
     * <p>Title: updateTaskStatus</p>  
     * <p>Description: 更新用户任务状态</p>  
     * @param id
     * @param status
     * @return  
     */  
    boolean updateTaskStatus(Long id, Integer status);
    

    /**  
     * <p>Title: getTodayTaskCount</p>  
     * <p>Description:获取今天完成的任务总量 </p>  
     * @param userId
     * @param taskId
     * @return  
     */  
    int getTodayTaskCount(Long userId, Integer taskId);
    

    /**
     * 批量清除相关任务
     * @param parentCode
     * @return
     */
    boolean batchDeleteTaskByCode( String parentCode);
    

    /**
     * 梯度任务，获取当前梯度
     * @param parentCode
     * @param num
     * @return
     */
    TaskOutDTO getCurrentTaskByCode(Long userId, String parentCode);
    

    /**
     * 获取任务完成详情列表
     * @param userId
     * @param parentCode
     * @return
     */
    List<TaskOutDTO> getTaskListByCode(Long userId, String parentCode);
    

    
    /**
     * 是否完成了某个任务
     * @param userId
     * @param taskId
     * @return
     */
    boolean isFinishTask( Long userId, Integer taskId);
    

    /**
     * 获取任务完成详情
     * @param userId
     * @param taskId
     * @return
     */
    TaskOutDTO getTaskById(Long userId, Integer taskId);
    

  


    

}
