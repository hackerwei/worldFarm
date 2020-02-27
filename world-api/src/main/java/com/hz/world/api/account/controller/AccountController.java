package com.hz.world.api.account.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hz.world.account.domain.dto.UserBaseInfoDTO;
import com.hz.world.account.domain.dto.UserLoginDTO;
import com.hz.world.account.domain.dto.UserLoginResultDTO;
import com.hz.world.account.service.AccountService;
import com.hz.world.account.service.UserBaseInfoService;
import com.hz.world.api.common.cache.ApiCacheUtil;
import com.hz.world.api.core.domain.dto.GeneralResultMap;
import com.hz.world.api.core.domain.dto.SysReturnCode;
import com.hz.world.common.util.IpUtil;
import com.hz.world.core.service.InviteService;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/account")
@Slf4j
public class AccountController {

    protected static final String X_SECURITY = "X-Security";
    protected static final String X_UUID = "X-UUID";

   
    @Autowired
    private AccountService accountService;

    @Autowired
    private UserBaseInfoService userBaseInfoService;

    @Autowired
    private ApiCacheUtil apiCacheUtil;

    @Autowired
    private InviteService inviteService;
 

   
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public GeneralResultMap thirdLogin(HttpServletRequest request,
                                       @RequestBody UserLoginDTO requestBody) {
        GeneralResultMap generalResultMap = new GeneralResultMap();// 返回结果
        long start = System.currentTimeMillis();
       
        try {
          
            String userIp = IpUtil.getIp(request);  //IP
            requestBody.setIp(userIp);


            UserLoginResultDTO result = accountService.thirdLogin(requestBody); // 注册或登录
            if (result == null || result.getUserId() == null) {
            		generalResultMap.setResult(SysReturnCode.FAIL, "登陆失败");
            		return generalResultMap;
			}
            Long userId = Long.parseLong(result.getUserId());
            if (userId > 0) {// 登录成功
            		
                UserBaseInfoDTO userInfo = userBaseInfoService.getByUserId(userId);
				if (userInfo != null) {
		
					result.setNickname(userInfo.getNickname());
					String headImg = userInfo.getHeadImg();
					
					result.setHeadImg(headImg);
				}
                
				UserLoginDTO userLoginDTO = new UserLoginDTO();
				userLoginDTO.setUserId(userId);
				
				if (result.getLoginType() == 1 && requestBody.getFromUserId() != null) {
					inviteService.inviteUser(requestBody.getFromUserId(), userId);
				}
	            generalResultMap.setResult(SysReturnCode.SUCC, result);
            } else {
                generalResultMap.setResult(SysReturnCode.FAIL, "登陆失败");
            }
	            
        } catch (Exception e) { // 系统内部错误
            generalResultMap.setResult(SysReturnCode.SYSTEM_ERROR);
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        log.info("注册登录,处理完成,start:{},end:{},time:{}",  start, end, (end - start));
        return generalResultMap;
    }

  

    /**
     * 用户退出登录
     * <pre>
     * @param userId
     * @return
     * Modifications:
     * Modifier linyanchun; 2018年6月3日; Create new Method accountExit
     * </pre>
     */
    @RequestMapping(value = "/exit", method = {RequestMethod.GET, RequestMethod.POST})
    public GeneralResultMap accountExit(@RequestHeader("uid") Long userId) {
        GeneralResultMap generalResultMap = new GeneralResultMap();
        Long result = 0L;
        try {
            result = apiCacheUtil.delUserLoginToken(userId);
            if (result > 0) {
                generalResultMap.setResult(SysReturnCode.SUCC.getCode(), SysReturnCode.SUCC.getMsg4Cn());
            } else {
                generalResultMap.setResult(SysReturnCode.SUCC.getCode(), SysReturnCode.SUCC.getMsg4Cn());
            }
        } catch (Exception e) {
            generalResultMap.setResult(SysReturnCode.FAIL.getCode(), SysReturnCode.FAIL.getMsg4Cn());
            e.printStackTrace();
        }

        log.info("accountExit,userId:{},result:{}", userId, result);
        return generalResultMap;

    }



}
