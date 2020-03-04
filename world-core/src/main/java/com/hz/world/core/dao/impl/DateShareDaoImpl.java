package com.hz.world.core.dao.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hz.world.core.dao.mapper.DateShareMapper;
import com.hz.world.core.dao.model.DateShare;

/**
 * <p>
 * Title: DateShareDaoImpl
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author linyanchun
 * @date 2018年9月4日
 */
@Repository
public class DateShareDaoImpl {
	@Autowired
	private DateShareMapper userCatchMapper;

	public DateShare findByDate(Date date) {
		return userCatchMapper.selectByPrimaryKey(date);
	}



	public boolean update(DateShare userCatch) {
		return userCatchMapper.updateByPrimaryKeySelective(userCatch) > 0;
	}

	public boolean insert(DateShare userCatch) {
		return userCatchMapper.insertSelective(userCatch) > 0;
	}

}
