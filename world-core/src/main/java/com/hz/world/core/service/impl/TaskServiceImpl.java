/**
 * Java Framework Auto Generator Tools
 * Copyright (c) 2015-2020 www.wifi.com
 *
 * @company wifi
 */
package com.hz.world.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hz.world.account.domain.dto.UserBaseInfoDTO;
import com.hz.world.account.service.UserBaseInfoService;
import com.hz.world.common.enums.DiamondChangeType;
import com.hz.world.common.enums.RewardType;
import com.hz.world.common.enums.TaskCode;
import com.hz.world.common.enums.TaskStatus;
import com.hz.world.common.ids.IDGenerator;
import com.hz.world.common.util.BeanUtil;
import com.hz.world.common.util.DateUtil;
import com.hz.world.core.dao.impl.TaskDetailInfoDaoImpl;
import com.hz.world.core.dao.impl.UserTaskLogDaoImpl;
import com.hz.world.core.dao.model.TaskDetailInfo;
import com.hz.world.core.dao.model.UserRewardLog;
import com.hz.world.core.dao.model.UserTaskLog;
import com.hz.world.core.domain.dto.TaskOutDTO;
import com.hz.world.core.service.TaskService;
import com.hz.world.core.service.UserDiamondService;
import com.hz.world.core.service.UserRewardService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	UserTaskLogDaoImpl userTaskLogDao;

	@Autowired
	TaskDetailInfoDaoImpl taskDetailInfoDao;
	@Autowired
	UserBaseInfoService userBaseInfoService;

	@Autowired
	UserRewardService userRewardService;
	@Autowired
	UserDiamondService userDiamondService;

	@Override
	public List<TaskDetailInfo> getTaskByParentCode(String parentCode) {
		List<TaskDetailInfo> list = taskDetailInfoDao.findByParentCodeOrder(parentCode);
		if (list != null && list.size() > 0) {
			return BeanUtil.copyProperties(list, TaskDetailInfo.class);
		}
		return null;
	}

	@Override
	public int getTodayFinishTaskCount(Long userId) {
		return userTaskLogDao.getTodayCountByParentCode(userId, TaskCode.EVERY_DAY_TASK.getCode());
	}

	@Override
	public TaskDetailInfo getTaskByCode(String code) {
		TaskDetailInfo info = taskDetailInfoDao.findByCode(code);
		if (info != null) {
			return BeanUtil.copyProperties(info, TaskDetailInfo.class);
		}
		return null;
	}

	@Override
	public TaskDetailInfo getTaskById(Integer id) {
		TaskDetailInfo info = taskDetailInfoDao.findById(id);
		if (info != null) {
			return BeanUtil.copyProperties(info, TaskDetailInfo.class);
		}
		return null;
	}

	@Override
	public int getTodayTaskCount(Long userId, Integer taskId) {
		return userTaskLogDao.getTodayTaskCount(userId, taskId);
	}

	@Override
	public boolean finishTask(Long userId, String code) {
		TaskDetailInfo taskDetailInfo = taskDetailInfoDao.findByCode(code);
		UserTaskLog log = new UserTaskLog();
		log.setUserId(userId);
		log.setTaskId(taskDetailInfo.getId());
		log.setParentCode(taskDetailInfo.getParentCode());
		log.setDate(DateUtil.getTodayDate());
		log.setStatus(1);
		log.setId(IDGenerator.getUniqueId());
		userTaskLogDao.insert(log);
		return true;

	}

	@Override
	public boolean isFinishTask(Long userId, Integer taskId) {
		UserTaskLog taskLog = userTaskLogDao.getUserTask(userId, taskId);
		if (taskLog != null) {
			return true;
		}
		return false;
	}

	@Override
	public TaskOutDTO getTaskById(Long userId, Integer taskId) {
		TaskDetailInfo taskDetailInfo = taskDetailInfoDao.findById(taskId);
		if (taskDetailInfo != null) {
			TaskDetailInfo taskDetailInfoDTO = BeanUtil.copyProperties(taskDetailInfo, TaskDetailInfo.class);
			return getTaskOut(userId, taskDetailInfoDTO);
		}
		return null;

	}

	@Override
	public boolean finishProcessTask(Long userId, Integer taskId, Long toNum) {
		UserTaskLog taskLog = userTaskLogDao.getUserTask(userId, taskId);
		if (taskLog != null) {
			return true;
		}
		TaskDetailInfo taskDetailInfo = taskDetailInfoDao.findById(taskId);
		if (toNum >= taskDetailInfo.getToNum()) {
			UserTaskLog log = new UserTaskLog();
			log.setUserId(userId);
			log.setTaskId(taskDetailInfo.getId());
			log.setParentCode(taskDetailInfo.getParentCode());
			log.setDate(DateUtil.getTodayDate());
			log.setStatus(1);
			log.setId(IDGenerator.getUniqueId());
			userTaskLogDao.insert(log);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateTaskStatus(Long id, Integer status) {
		return userTaskLogDao.updateStatusById(id, status);
	}

	@Override
	public boolean processTask(Long userId, String parentCode, Long toNum) {
		if (toNum <= 0) {
			return false;
		}
		List<TaskDetailInfo> taskList = taskDetailInfoDao.findByParentCode(parentCode);
		if (taskList != null) {
			List<UserTaskLog> finishedTask = userTaskLogDao.getUserFinishedTask(userId, parentCode);
			for (TaskDetailInfo taskDetailInfo : taskList) {
				// 完成相关任务
				if (toNum >= taskDetailInfo.getToNum()) {
					if (!checkIsInList(finishedTask, taskDetailInfo.getId())) {
						// 插入任务完成日志
						UserTaskLog record = new UserTaskLog();
						record.setId(IDGenerator.getUniqueId());
						record.setUserId(userId);
						record.setDate(DateUtil.getTodayDate());
						record.setTaskId(taskDetailInfo.getId());
						record.setParentCode(parentCode);
						record.setStatus(TaskStatus.FINISHED.getCode());
						userTaskLogDao.insert(record);
						// return true;
					}
				}
			}
		}
		return true;
	}

	@Override
	public UserTaskLog findUserTaskLog(Long userId, Integer taskId) {
		UserTaskLog log = userTaskLogDao.getUserTask(userId, taskId);
		if (log != null) {
			return BeanUtil.copyProperties(log, UserTaskLog.class);
		}
		return null;
	}

	@Override
	public UserTaskLog findUserTaskLogToday(Long userId, Integer taskId) {
		UserTaskLog log = userTaskLogDao.getUserTaskByDate(userId, taskId, DateUtil.getTodayDate());
		if (log != null) {
			return BeanUtil.copyProperties(log, UserTaskLog.class);
		}
		return null;
	}

	@Override
	public boolean recvReward(TaskDetailInfo taskDetailInfo, Long userId) {

		UserRewardLog record = new UserRewardLog();
		Long rewardLogId = IDGenerator.getUniqueId();
		record.setId(rewardLogId);
		record.setUserId(userId);
		record.setRewardCount(taskDetailInfo.getDiamondCount() + "");
		record.setRewardType(RewardType.TASK_REWARD.getCode());
		record.setRelatedId(taskDetailInfo.getId());

		if (!userBaseInfoService.updateUserDiamond(userId, taskDetailInfo.getDiamondCount())) {
			return false;
		}
		UserBaseInfoDTO user = userBaseInfoService.getByUserId(userId);
		
		userDiamondService.createDiamondChangeLog(userId, taskDetailInfo.getDiamondCount(), user.getDiamond(), DiamondChangeType.TASK.getCode());

		record.setRewardMessage(taskDetailInfo.getName()+"获取奖励");
		userRewardService.createUserRewardLog(record.getUserId(),record.getRewardType(),record.getRelatedId(),record.getRewardCount(),record.getRewardMessage());
		return true;

	}

	@Override
	public List<UserTaskLog> findTaskProcess(Long userId, String parentCode) {
		List<UserTaskLog> list = userTaskLogDao.getUserFinishedTask(userId, parentCode);
		if (list != null && list.size() > 0) {
			return BeanUtil.copyProperties(list, UserTaskLog.class);
		}
		return null;
	}

	@Override
	public boolean batchDeleteTaskByCode(String parentCode) {
		return userTaskLogDao.batchDeleteTaskByCode(parentCode);
	}

	@Override
	public TaskOutDTO getCurrentTaskByCode(Long userId, String parentCode) {
		List<UserTaskLog> finishedTask = userTaskLogDao.getUserRewardedTask(userId, parentCode);
		List<TaskDetailInfo> taskList = getTaskByParentCode(parentCode);
		if (taskList != null && taskList.size() >= 0) {
			for (TaskDetailInfo taskDetailInfo : taskList) {
				if (!isInList(taskDetailInfo, finishedTask)) {
					return getTaskOut(userId, taskDetailInfo);
				}
			}
			return getTaskOut(userId, taskList.get(taskList.size() - 1));
		}
		return null;

	}

	@Override
	public List<TaskOutDTO> getTaskListByCode(Long userId, String parentCode){
		List<TaskOutDTO>  outList = new ArrayList<TaskOutDTO>();
		List<TaskDetailInfo> taskList = getTaskByParentCode(parentCode);
		if (taskList != null && taskList.size() >= 0) {
			for (TaskDetailInfo taskDetailInfo : taskList) {
				TaskOutDTO outTask = getTaskOut(userId, taskDetailInfo);
				outList.add(outTask);
			}
		}
		return outList;
		
	}

	private  TaskOutDTO getTaskOut(Long userId, TaskDetailInfo task) {
		TaskOutDTO result = new TaskOutDTO();	
		result.setGetNum(0);
		result.setRewardNum(task.getDiamondCount());
		result.setNeedNum(task.getToNum());
		result.setId(task.getId());
		result.setTitle(task.getName());
		UserTaskLog taskFinish = findUserTaskLog(userId, task.getId());
		if (taskFinish == null ) {
			result.setTaskStatus(TaskStatus.UN_FINISHED.getCode());
		}else {
			result.setTaskStatus(taskFinish.getStatus());
		}
		
		return result;
	}

	private boolean isInList(TaskDetailInfo taskDetailInfo, List<UserTaskLog> finishedTask) {
		if (finishedTask == null || finishedTask.size() == 0) {
			return false;
		} else {
			for (UserTaskLog userTaskLog : finishedTask) {
				if (taskDetailInfo.getId().equals(userTaskLog.getTaskId())) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkIsInList(List<UserTaskLog> list, Integer taskId) {
		if (list != null) {
			for (UserTaskLog userTaskLog : list) {
				if (userTaskLog.getTaskId().equals(taskId)) {
					return true;
				}
			}
		}
		return false;
	}
}
