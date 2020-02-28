package com.hz.world.core.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hz.world.account.domain.dto.UserBaseInfoDTO;
import com.hz.world.account.service.UserBaseInfoService;
import com.hz.world.common.dto.ResultCodeEnum;
import com.hz.world.common.dto.ResultDTO;
import com.hz.world.common.enums.CashChangeType;
import com.hz.world.common.enums.CoinChangeType;
import com.hz.world.common.enums.DiamondChangeType;
import com.hz.world.common.enums.ElementAdd;
import com.hz.world.common.ids.IDGenerator;
import com.hz.world.core.common.util.ConfigCacheUtil;
import com.hz.world.core.dao.impl.UserCashChangeLogDaoImpl;
import com.hz.world.core.dao.impl.UserDiamondChangeLogDaoImpl;
import com.hz.world.core.dao.impl.UserTotalAddLogDaoImpl;
import com.hz.world.core.dao.model.RechargeConfig;
import com.hz.world.core.dao.model.ShopConfig;
import com.hz.world.core.dao.model.UserCashChangeLog;
import com.hz.world.core.dao.model.UserDiamondChangeLog;
import com.hz.world.core.dao.model.UserTotalAddLog;
import com.hz.world.core.domain.dto.ShopResultDTO;
import com.hz.world.core.domain.dto.UserCoinDTO;
import com.hz.world.core.service.ShopService;
import com.hz.world.core.service.UserCoinService;
import com.hz.world.core.service.UserElementService;

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
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ConfigCacheUtil configCacheUtil;
	@Autowired
	private UserBaseInfoService userBaseInfoService;
	@Autowired
	private UserCashChangeLogDaoImpl userCashChangeLogDao;
	@Autowired
	private UserDiamondChangeLogDaoImpl userDiamondChangeLogDao;
	@Autowired
	private UserCoinService userCoinService;
	@Autowired
	private UserElementService userElementService;

	@Autowired
	private UserTotalAddLogDaoImpl userTotalAddLogDao;

	@Override
	public ResultDTO<ShopResultDTO> buy(Long userId, Integer productId){
		ResultDTO<ShopResultDTO> resultDTO = new ResultDTO<ShopResultDTO>();
		ShopResultDTO shopResult = new ShopResultDTO();
		shopResult.setCoinReward("0");
		shopResult.setAdd(0);
		try {
			ShopConfig config = configCacheUtil.getShopConfig(productId);
			if (config == null) {
				resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "商品不存在");
				return resultDTO;
			}
			UserBaseInfoDTO user = userBaseInfoService.getByUserId(userId);
			if (user == null) {
				resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "用户不存在");
				return resultDTO;
			}
			if (user.getDiamond() < config.getPrice()) {
				resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "钻石不足");
				return resultDTO;
			}
			//扣除钻石
			if (!userBaseInfoService.updateUserDiamond(userId, -config.getPrice())) {
				resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "钻石扣除失败");
				return resultDTO;
			}
			UserDiamondChangeLog record = new UserDiamondChangeLog();
			record.setId(IDGenerator.getUniqueId());
			record.setNum(user.getDiamond()+"");
			record.setAfterNum(user.getDiamond() + user.getDiamond() - config.getPrice()+"");
			record.setUserId(userId);
			record.setRelatedType(DiamondChangeType.SHOP.getCode());
			userDiamondChangeLogDao.insert(record);
			//获得金币收益
			if (config.getType() == 1) {
				UserCoinDTO coin = userCoinService.getUserCoin(userId);
				if (coin != null) {
					BigDecimal rate = new BigDecimal(coin.getIcomeRate()) ;
					String income = rate.multiply(BigDecimal.valueOf(3600*config.getParam())).toString();
					userCoinService.changeUserCoin(userId, income, CoinChangeType.SHOP_REWARD.getCode(), 0);
					UserCoinDTO coinDTO = userCoinService.getUserCoin(userId);
					shopResult.setCoinReward(income);
					shopResult.setCoin(coinDTO.getCoin());
				}
			}
			//获得永久收益加成
			else if (config.getType() == 2) {
				ResultDTO<String> resultDTO2 = userElementService.addTotalAdd(userId, ElementAdd.SHOP.getCode(), config.getParam());
				if (!resultDTO2.isSuccess()) {
					log.error("商城购买增加永久收益失败:{},{}",userId,config.getParam());
					userBaseInfoService.updateUserDiamond(userId, config.getPrice());
					resultDTO.set(ResultCodeEnum.ERROR_HANDLE, "系统异常");
					return resultDTO;
				}
				int add= userElementService.getUserTotalAddByField(userId, ElementAdd.SHOP.getCode());
				UserTotalAddLog logData = new UserTotalAddLog();
				logData.setId(IDGenerator.getUniqueId());
				logData.setType(ElementAdd.SHOP.getCode());
				logData.setUserId(userId);
				logData.setNum(config.getParam());
				logData.setAfterNum(add);
				logData.setRelationType(0);
				shopResult.setAdd(add);
				userTotalAddLogDao.insert(logData);
			}
			resultDTO.set(ResultCodeEnum.SUCCESS, "ok",shopResult);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultDTO;
	}
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
