package com.hz.world.account.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hz.world.account.common.util.AccountCacheUtil;
import com.hz.world.account.dao.impl.UserBaseInfoDaoImpl;
import com.hz.world.account.dao.model.UserBaseInfo;
import com.hz.world.account.domain.dto.UserBaseDTO;
import com.hz.world.account.service.FriendService;
import com.hz.world.common.util.BeanUtil;
import com.hz.world.common.util.DateUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: Description: author linyanchun date Mar 1, 2020
 */
@Slf4j
@Service
public class FriendServiceImpl implements FriendService {

	@Autowired
	private UserBaseInfoDaoImpl userBaseInfoDao;

	@Autowired
	private AccountCacheUtil accountCacheUtil;

	public List<UserBaseDTO> getUserFriendList(Long userId, Integer offset, Integer limit){
		List<UserBaseDTO> result = new ArrayList<UserBaseDTO>();
		List<UserBaseInfo> userList = userBaseInfoDao.getUserFriendList(userId, offset, limit);
		if (userList != null && userList.size() > 0) {
			for (UserBaseInfo userBaseInfo : userList) {
				UserBaseDTO userBaseDTO = BeanUtil.copyProperties(userBaseInfo, UserBaseDTO.class);
				userBaseDTO.setRegTime(DateUtil.format(userBaseInfo.getCreateTime(), "yyyy/MM/dd HH:mm"));

				result.add(userBaseDTO);
			}
		
		}
		return result;
		
	}

	
	public List<UserBaseDTO> getFriendFriendList(Long userId, Integer offset, Integer limit){
		List<UserBaseDTO> result = new ArrayList<UserBaseDTO>();
		List<Long> uids = new ArrayList<Long>();
		List<UserBaseInfo> userList = userBaseInfoDao.getUserFriendList(userId, 0, 1000);
		if (userList != null && userList.size() > 0) {
			for (UserBaseInfo userBaseInfo : userList) {
				uids.add(userBaseInfo.getUserId());
			}
		}
		userList = userBaseInfoDao.getFriendFriendList(uids, offset, limit);
		if (userList != null && userList.size() > 0) {
			for (UserBaseInfo userBaseInfo : userList) {
				UserBaseDTO userBaseDTO = BeanUtil.copyProperties(userBaseInfo, UserBaseDTO.class);
				userBaseDTO.setRegTime(DateUtil.format(userBaseInfo.getCreateTime(), "yyyy/MM/dd HH:mm"));
				result.add(userBaseDTO);
			}
		
		}
		return result;
	}

	
	public List<UserBaseDTO> getUnActiveFriendList(Long userId, Integer offset, Integer limit){
		List<UserBaseDTO> result = new ArrayList<UserBaseDTO>();
		List<UserBaseInfo> userList = userBaseInfoDao.getUnActiveFriendList(userId, offset, limit);
		if (userList != null && userList.size() > 0) {
			for (UserBaseInfo userBaseInfo : userList) {
				UserBaseDTO userBaseDTO = BeanUtil.copyProperties(userBaseInfo, UserBaseDTO.class);
				userBaseDTO.setRegTime(DateUtil.format(userBaseInfo.getCreateTime(), "yyyy/MM/dd HH:mm"));
				result.add(userBaseDTO);
			}
		
		}
		return result;
	}
	
	@Override
	public int getFriendCount(Long userId) {
		return userBaseInfoDao.getFriendCount(userId);
	}
	@Override
	public int getTodayFriendCount(Long userId) {
		return userBaseInfoDao.getTodayFriendCount(userId);
	}
	
	@Override
	public int getFriendWeight(Long userId) {
		return userBaseInfoDao.getFriendWeight(userId);
	}
	
	@Override
	public int getUnAuthFriendCount(Long userId) {
		return userBaseInfoDao.getUnAuthFriendCount(userId);
	}
}
