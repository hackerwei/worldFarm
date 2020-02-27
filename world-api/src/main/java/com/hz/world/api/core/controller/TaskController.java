/**
 * Title: IndexController.java
 * Description: 
 * author linyanchun
 * date 2019年3月8日 
 */
package com.hz.world.api.core.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hz.world.api.core.domain.dto.GeneralResultMap;
import com.hz.world.api.core.domain.dto.SysReturnCode;
import com.hz.world.api.core.domain.request.TaskRequest;
import com.hz.world.common.enums.TaskStatus;
import com.hz.world.core.dao.model.TaskDetailInfo;
import com.hz.world.core.dao.model.UserTaskLog;
import com.hz.world.core.domain.dto.TaskOutDTO;
import com.hz.world.core.service.TaskService;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: Description: author linyanchun date 2019年3月8日
 */
@RestController
@RequestMapping("/task")
@Slf4j
public class TaskController {

	@Autowired
	private TaskService taskService;

;

	

	/**
	 * 任务领奖
	 * 
	 * @param userId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getReward", method = { RequestMethod.POST })
	public GeneralResultMap getReward(@RequestHeader("uid") Long userId, @RequestBody TaskRequest request) {
		GeneralResultMap outputMap = new GeneralResultMap();

		try {
			if (request == null || request.getTaskId() == null) {
				outputMap.setResult(SysReturnCode.LACK_PARAMS, "请求参数错误");
				return outputMap;
			}

			TaskDetailInfo taskDetailInfo = taskService.getTaskById(request.getTaskId());
			if (taskDetailInfo == null) {
				outputMap.setResult(SysReturnCode.LACK_PARAMS, "任务不存在");
				return outputMap;
			}
			UserTaskLog taskLog = taskService.findUserTaskLog(userId, request.getTaskId());
			if (taskLog == null || !taskLog.getStatus().equals(TaskStatus.FINISHED.getCode())) {
				outputMap.setResult(SysReturnCode.LACK_PARAMS, "状态不合法，不能领取该任务");
				return outputMap;
			}
			// 发送奖励，更新状态
			if (taskService.recvReward(taskDetailInfo, userId)) {
				taskService.updateTaskStatus(taskLog.getId(), TaskStatus.REWARDED.getCode());
				Map<String, Object> data = new HashMap<String, Object>();
				TaskOutDTO task = taskService.getCurrentTaskByCode(userId, taskDetailInfo.getParentCode());
				data.put("task", task);
				outputMap.setResult(SysReturnCode.SUCC, data);
				
				return outputMap;
			}
			outputMap.setResult(SysReturnCode.FAIL, "领奖失败");
		} catch (Exception e) {
			e.printStackTrace();
			outputMap.setResult(SysReturnCode.FAIL, "领奖失败");
		}
		return outputMap;
	}

	

	
}
