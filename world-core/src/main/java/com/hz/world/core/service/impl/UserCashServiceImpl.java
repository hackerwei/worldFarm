package com.hz.world.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hz.world.common.enums.CashChangeType;
import com.hz.world.common.ids.IDGenerator;
import com.hz.world.common.util.BeanUtil;
import com.hz.world.common.util.DateUtil;
import com.hz.world.core.dao.impl.UserCashChangeLogDaoImpl;
import com.hz.world.core.dao.model.UserCashChangeLog;
import com.hz.world.core.domain.dto.UserCashChangeLogDTO;
import com.hz.world.core.service.UserCashService;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 22, 2020 
 */
@Slf4j
@Service
public class UserCashServiceImpl implements UserCashService {


	@Autowired
	private UserCashChangeLogDaoImpl userCashChangeLogDao;
	
	@Override
	public void createCashChangeLog(Long userId, Double num,Double afterNum, Integer relateType, Integer type, String content) {
		UserCashChangeLog record = new UserCashChangeLog();
		record.setId(IDGenerator.getUniqueId());
		record.setNum(num);
		record.setAfterNum(afterNum);
		record.setUserId(userId);
		record.setRelatedType(relateType);
		record.setType(type);
		record.setContent(content);
		userCashChangeLogDao.insert(record);
	}
	@Override
	public List<UserCashChangeLogDTO> getChangeList(Long userId, Integer offset, Integer limit){
		List<UserCashChangeLogDTO> resultList = new ArrayList<UserCashChangeLogDTO>();
		List<UserCashChangeLog> list =  userCashChangeLogDao.getChangeList(userId, offset, limit);
		if (list != null && list.size() > 0) {
			for (UserCashChangeLog userCashChangeLog : list) {
				UserCashChangeLogDTO dto = new UserCashChangeLogDTO();
				dto = BeanUtil.copyProperties(userCashChangeLog, UserCashChangeLogDTO.class);
				dto.setDate(DateUtil.format(userCashChangeLog.getAddTime(), DateUtil.DATE_FORMAT_24HOUR_PATTERN));
				resultList.add(dto);
			}
		}
		return resultList;
	}
}
