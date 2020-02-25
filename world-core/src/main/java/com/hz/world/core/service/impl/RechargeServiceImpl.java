package com.hz.world.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hz.world.account.domain.dto.UserBaseInfoDTO;
import com.hz.world.account.service.UserBaseInfoService;
import com.hz.world.common.dto.ResultCodeEnum;
import com.hz.world.common.dto.ResultDTO;
import com.hz.world.common.enums.CashChangeType;
import com.hz.world.common.enums.DiamondChangeType;
import com.hz.world.common.ids.IDGenerator;
import com.hz.world.core.common.util.ConfigCacheUtil;
import com.hz.world.core.dao.impl.UserCashChangeLogDaoImpl;
import com.hz.world.core.dao.impl.UserDiamondChangeLogDaoImpl;
import com.hz.world.core.dao.model.RechargeConfig;
import com.hz.world.core.dao.model.UserCashChangeLog;
import com.hz.world.core.dao.model.UserDiamondChangeLog;
import com.hz.world.core.service.RechargeService;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 22, 2020 
 */
@Slf4j
@Service
@Transactional
public class RechargeServiceImpl implements RechargeService {

	@Autowired
	private ConfigCacheUtil configCacheUtil;
	@Autowired
	private UserBaseInfoService userBaseInfoService;
	@Autowired
	private UserCashChangeLogDaoImpl userCashChangeLogDao;
	@Autowired
	private UserDiamondChangeLogDaoImpl userDiamondChangeLogDao;

	@Override
	public ResultDTO<String> recharge(Long userId, Integer rechargeId){
		ResultDTO<String> resultDTO = new ResultDTO<String>();
		try {
			RechargeConfig config = configCacheUtil.getRechargeConfig(rechargeId);
			if (config == null) {
				resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "配置不存在");
				return resultDTO;
			}
			UserBaseInfoDTO user = userBaseInfoService.getByUserId(userId);
			if (user == null) {
				resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "用户不存在");
				return resultDTO;
			}
			if (user.getCash() < config.getPrice()) {
				resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "现金不足");
				return resultDTO;
			}
			//扣除现金
			if (!userBaseInfoService.updateUserCash(userId, -config.getPrice())) {
				resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "现金不足");
				return resultDTO;
			}
			UserCashChangeLog record = new UserCashChangeLog();
			record.setId(IDGenerator.getUniqueId());
			record.setNum(user.getCash());
			record.setAfterNum(user.getCash() - config.getPrice());
			record.setUserId(userId);
			record.setRelatedType(CashChangeType.RECHARGE.getCode());
			userCashChangeLogDao.insert(record);
			//增加钻石
			int diamond = config.getNum() + config.getGift();
			userBaseInfoService.updateUserDiamond(userId, diamond);
			UserDiamondChangeLog record1 = new UserDiamondChangeLog();
			record1.setId(IDGenerator.getUniqueId());
			record1.setNum(user.getDiamond()+"");
			record1.setAfterNum(user.getDiamond() + diamond+"");
			record1.setUserId(userId);
			record1.setRelatedType(DiamondChangeType.RECHARGE.getCode());
			userDiamondChangeLogDao.insert(record1);
			resultDTO.set(ResultCodeEnum.SUCCESS, "OK");
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		
		return resultDTO;
	}
	
	/**
	 * 充值配置
	 * @return
	 */
	public List<RechargeConfig> getConfigList(){
		return configCacheUtil.getRechargeList();
	}
}
