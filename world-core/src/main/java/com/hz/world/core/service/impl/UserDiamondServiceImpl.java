package com.hz.world.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hz.world.common.enums.DiamondChangeType;
import com.hz.world.common.ids.IDGenerator;
import com.hz.world.core.dao.impl.UserDiamondChangeLogDaoImpl;
import com.hz.world.core.dao.model.UserDiamondChangeLog;
import com.hz.world.core.service.UserDIamondService;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 22, 2020 
 */
@Slf4j
@Service
public class UserDiamondServiceImpl implements UserDIamondService {

	@Autowired
	private UserDiamondChangeLogDaoImpl userDiamondChangeLogDao;

	@Override
	public boolean createDiamondChangeLog(Long userId,Integer num, Integer afterNum, Integer type) {
		UserDiamondChangeLog record = new UserDiamondChangeLog();
		record.setId(IDGenerator.getUniqueId());
		record.setNum(num+"");
		record.setAfterNum(afterNum+"");
		record.setUserId(userId);
		record.setRelatedType(DiamondChangeType.SHOP.getCode());
		userDiamondChangeLogDao.insert(record);
		return true;
	}
	
}
