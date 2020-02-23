package com.hz.world.account.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hz.world.account.common.util.AccountCacheUtil;
import com.hz.world.account.dao.impl.UserBaseInfoDaoImpl;
import com.hz.world.account.dao.model.UserBaseInfo;
import com.hz.world.account.domain.dto.UserBaseInfoDTO;
import com.hz.world.account.service.UserBaseInfoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserBaseInfoServiceImpl implements UserBaseInfoService {

    @Autowired
    private UserBaseInfoDaoImpl userBaseInfoDao;
 
    @Autowired
    private AccountCacheUtil accountCacheUtil;
    
  
 
  

    @Override
    public boolean create(UserBaseInfoDTO entity) {
        boolean result = false;
        try {
            UserBaseInfo userBaseInfo = new UserBaseInfo();
            BeanUtils.copyProperties(entity, userBaseInfo);
            result = userBaseInfoDao.insert(userBaseInfo);
            if (result) {
                //            userMQMessageService.sendUserMsg(new UserMsgDTO(entity.getUserId(), entity.getHeadImg(), entity.getNickname(), OptTypeEnum.ADD));
            }
        } catch (Exception e) {
            log.error("创建用户:{}异常", entity, e);
        }
        return result;
    }

    @Override
    public boolean update(UserBaseInfoDTO entity) {
        boolean result = false;
        try {
            UserBaseInfo userBaseInfo = new UserBaseInfo();
            BeanUtils.copyProperties(entity, userBaseInfo);
            
            result = userBaseInfoDao.update(userBaseInfo);
            if (result) {
            		accountCacheUtil.deleteUserBaeInfo(entity.getUserId());
			}
            
        } catch (Exception e) {
            log.error("更新用户:{}异常", entity, e);
        }

        return result;
    }


    @Override
    public UserBaseInfoDTO getByUserId(Long userId) {
   
        return accountCacheUtil.getUserBaseInfo(userId);
  
    }
   

    public String checkAndReturnNickname(String nickname) {
        return userBaseInfoDao.checkAndReturnNickname(nickname);
    }

    @Override
    public boolean isExistNickname(String nickname) {
        return !userBaseInfoDao.isExistNickname(nickname);
    }

   
    @Override
    public List<UserBaseInfo> findByNickname(String nickname) {
        return userBaseInfoDao.findByNickname(nickname);
    }

    @Override
    public boolean addUserLevel(Long userId) {
    	
    		boolean result =  userBaseInfoDao.addUserLevel(userId);
    		if (result) {
        		accountCacheUtil.deleteUserBaeInfo(userId);
        		UserBaseInfoDTO user = accountCacheUtil.getUserBaseInfo(userId);
        		
    		}	
    		return result;
    }

 
    
    @Override
    public int getInviteCount(Long userId) {
    		return userBaseInfoDao.getInviteCount(userId);
    }
    
}
