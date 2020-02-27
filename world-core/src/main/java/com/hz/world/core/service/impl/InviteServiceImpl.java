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
import com.hz.world.common.dto.ResultCodeEnum;
import com.hz.world.common.dto.ResultDTO;
import com.hz.world.common.enums.AchievementCodeEnum;
import com.hz.world.common.enums.DiamondChangeType;
import com.hz.world.common.ids.IDGenerator;
import com.hz.world.common.util.DateUtil;
import com.hz.world.core.common.util.CoreCacheUtil;
import com.hz.world.core.dao.impl.UserInviteLogDaoImpl;
import com.hz.world.core.dao.model.UserInviteLog;
import com.hz.world.core.domain.dto.UserInviteDTO;
import com.hz.world.core.service.InviteService;
import com.hz.world.core.service.TaskService;
import com.hz.world.core.service.UserDiamondService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InviteServiceImpl implements InviteService {

	@Autowired
	private UserInviteLogDaoImpl userInviteLogDao;

	@Autowired
	private TaskService taskService;

	@Autowired
	private UserBaseInfoService userBaseInfoService;
	@Autowired
	private UserDiamondService userDiamondService;

	@Override
	/**
	 * 分享邀请
	 * 
	 * @param userId
	 * @param inviteUserId
	 * @return
	 */
	public ResultDTO<String> inviteUser(Long userId, Long invited) {
		ResultDTO<String> resultDTO = new ResultDTO<String>();
		// 是否已经邀请了
		if (userInviteLogDao.checkIsInvited(userId, invited)) {
			log.error("已经邀请过了，重复邀请,{},{}", userId, invited);
			resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "已经邀请过了");
			return resultDTO;
		}

		UserInviteLog record = new UserInviteLog();
		record.setId(IDGenerator.getUniqueId());
		record.setUserId(userId);
		record.setInviteUserId(invited);
		record.setStatus(0);
		userInviteLogDao.insert(record);
		int inviteCount = userInviteLogDao.getTotalCount(userId) ;
		taskService.processTask(userId, AchievementCodeEnum.INVITE_USER.getTaskCode(), (long) inviteCount);
		resultDTO.set(ResultCodeEnum.SUCCESS, "ok");
		return resultDTO;
	}

	@Override
	public ResultDTO<String> getInviteReward(Long userId, Long invited) {
		ResultDTO<String> resultDTO = new ResultDTO<String>();
		UserInviteLog record = userInviteLogDao.getInviteLog(userId, invited);
		if (record == null) {
			log.error("还未被邀请，不能获得奖励,{},{}", userId, invited);
			resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "还未被邀请，不能获得奖励");
			return resultDTO;
		}
		if (record.getStatus() == 1) {
			log.error("奖励已经被领取，不能重复领取,{},{}", userId, invited);
			resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "奖励已经被领取，不能重复领取");
			return resultDTO;
		}

		if (!userBaseInfoService.updateUserDiamond(userId, 50)) {
			log.error("邀请奖励发送失败{},{},{},", userId);
			resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "领取奖励失败");
			return resultDTO;
		}
		UserBaseInfoDTO user = userBaseInfoService.getByUserId(userId);
		userDiamondService.createDiamondChangeLog(userId, 50, user.getDiamond(), DiamondChangeType.INVITE.getCode());

		record.setStatus(1);
		userInviteLogDao.update(record);
		resultDTO.set(ResultCodeEnum.SUCCESS, "ok");
		return resultDTO;

	}

	@Override
	public List<UserInviteDTO> getUserInviteList(Long userId, Integer count) {
		List<UserInviteDTO> resultList = new ArrayList<UserInviteDTO>();
		List<UserInviteLog> inviteList = userInviteLogDao.getUserInviteList(userId, count);
		if (inviteList != null && inviteList.size() > 0) {
			
			

			for (UserInviteLog userInviteLog : inviteList) {
				UserBaseInfoDTO user = userBaseInfoService.getByUserId(userInviteLog.getInviteUserId());
				if (user != null) {
					UserInviteDTO dto = new UserInviteDTO();
					dto.setUserId(userInviteLog.getInviteUserId() + "");
					dto.setStatus(userInviteLog.getStatus());
					dto.setNickname(user.getNickname());
					dto.setHeadImg(user.getHeadImg());
					resultList.add(dto);
				}
				
			}

		}
		return resultList;

	}

	@Override
	public int getInviteCount(Long userId) {
		return userInviteLogDao.getTotalCount(userId);
	}

}
