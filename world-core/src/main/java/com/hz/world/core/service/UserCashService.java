package com.hz.world.core.service;

import java.util.List;

import com.hz.world.core.domain.dto.UserCashChangeLogDTO;

public interface UserCashService {


	/**
	 * 创建操作日志
	 * @param userId
	 * @param num
	 * @param relateType
	 * @param type
	 * @param content
	 * @return
	 */
	public void createCashChangeLog(Long userId, Double num, Double afterNum,Integer relateType, Integer type, String content);
	
	/**
	 * 资金变动记录
	 * @param userId
	 * @param offset
	 * @param limit
	 * @return
	 */
	public List<UserCashChangeLogDTO> getChangeList(Long userId, Integer offset, Integer limit);
}
