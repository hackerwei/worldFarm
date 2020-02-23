package com.hz.world.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hz.world.account.common.enums.AccountSource;
import com.hz.world.account.common.util.AccountCacheUtil;
import com.hz.world.account.dao.impl.UserBaseInfoDaoImpl;
import com.hz.world.account.dao.model.UserBaseInfo;
import com.hz.world.account.domain.dto.UserBaseInfoDTO;
import com.hz.world.account.domain.dto.UserLoginDTO;
import com.hz.world.account.domain.dto.UserLoginResultDTO;
import com.hz.world.account.service.AccountService;
import com.hz.world.common.ids.IDGenerator;
import com.hz.world.common.util.AccessToken;
import com.hz.world.core.service.UserCoinService;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Title: AccountServiceImpl
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author linyanchun
 * @date 2018年8月19日
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private UserBaseInfoDaoImpl userBaseInfoDao;
	
	@Autowired
	private UserCoinService userCoinService;
	@Autowired
	private AccountCacheUtil accountCacheUtil;


	/**
	 * 微信登录接口,用户不存在进行注册，否则登录
	 *
	 * @param appId
	 * @param uuid
	 * @param userLoginDTO
	 * @return
	 */
	public UserLoginResultDTO thirdLogin(UserLoginDTO userLoginDTO) {
		log.info("dance-account,thirdLogin,start,userLoginDTO:{}", userLoginDTO);
		UserLoginResultDTO result = null;
		if (AccountSource.AUTO.getCode() == userLoginDTO.getType()) {// 万能钥匙登录
			result = this.autoLogin(userLoginDTO.getUserId());
		}
		return result;
	}
	public UserLoginResultDTO autoLogin(Long userId) {
		UserLoginResultDTO loginResult = new UserLoginResultDTO();
		UserBaseInfoDTO user = accountCacheUtil.getUserBaseInfo(userId);
		if (user == null) {
			return createUser("测试",1,userId);
		}else {
			String loginToken = accountCacheUtil.getLoginToken(userId); 
			loginResult.setAccessToken(loginToken);
			loginResult.setUserId(userId + "");
			loginResult.setLoginType(2);
		}
		return loginResult;
	}

	public UserLoginResultDTO wechatLogin(UserLoginDTO userLoginDTO) {
		UserLoginResultDTO loginResult = new UserLoginResultDTO();

		return loginResult;
	}

	@Override
	public UserLoginResultDTO createUser(String nickname, Integer gender, Long uid) {
		UserLoginResultDTO loginResult = new UserLoginResultDTO();

		boolean result = false;
		String headImg = "http://thirdqq.qlogo.cn/g?b=oidb&k=VRc66zPfnDqBZp5CVr2Yhg&s=140&t=1561518998";
		result = register(uid, nickname, gender,headImg);
		if (result) {
			
			String loginToken = AccessToken.encrtyToken("", uid + "", System.currentTimeMillis());
			accountCacheUtil.setUserLoginToken(uid, loginToken); // 保存登录token

			loginResult.setAccessToken(loginToken);
			loginResult.setUserId(uid + "");
			loginResult.setLoginType(1);
			
			loginResult.setNickname(nickname);
			loginResult.setHeadImg(headImg);
			
			return loginResult;
		} else {
			return null;
		}

	}

	public boolean register(Long userId, String nickname, Integer gender, String headImg) {
		boolean result = false;
		try {

			headImg = "http://thirdqq.qlogo.cn/g?b=oidb&k=VRc66zPfnDqBZp5CVr2Yhg&s=140&t=1561518998";

			
			UserBaseInfo userBaseInfo = new UserBaseInfo();
			userBaseInfo.setUserId(userId);
			userBaseInfo.setNickname(nickname); // nickName
			userBaseInfo.setHeadImg(headImg); // headImg
			userBaseInfo.setGender(gender);
			
			result = userBaseInfoDao.insert(userBaseInfo);
			if (result) {
				 userCoinService.createUserCoin(userBaseInfo.getUserId());
			}

			log.info("thirdRegister,userBaseInfo,userId:{},userBaseInfo:{},r3:{}", userId, userBaseInfo, result);
		} catch (Exception e) {
			log.error("thirdRegister,userId:{}", userId, e);
		}
		return result;

	}

}
