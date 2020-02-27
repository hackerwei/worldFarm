package com.hz.world.api.core.domain.request;

import lombok.Data;

/**
 * Title: 
 * Description: 
 * author linyanchun
 * date 2019年3月6日 
 */
@Data
public class TaskRequest {
   
	
	private Integer  taskId; //任务id
	
	private Integer type; //任务类型，1:成就，2:新手目标，3:邀请
}