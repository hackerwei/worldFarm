package com.hz.world.account.service;


import com.hz.world.account.domain.dto.UserLoginDTO;
import com.hz.world.account.domain.dto.UserLoginResultDTO;

public interface AccountService {


	/**
	 * 微信登录接口,用户不存在进行注册，否则登录
	 * @param appId
	 * @param uuid
	 * @param userLoginDTO
	 * @return
	 */
	public UserLoginResultDTO thirdLogin(UserLoginDTO userLoginDTO);
	

	/**
	 * 人工创建用户
	 * @param nickname
	 * @param gender
	 * @param userId
	 * @return
	 */
	public UserLoginResultDTO createUser(String nickname, Integer gender, Long userId);
	

	
	
	
}
