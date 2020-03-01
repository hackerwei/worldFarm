package com.hz.world.core.service.impl;

import com.hz.world.common.dto.ResultCodeEnum;
import com.hz.world.common.dto.ResultDTO;
import com.hz.world.common.enums.ElementAdd;
import com.hz.world.core.common.util.ConfigCacheUtil;
import com.hz.world.core.dao.impl.UserChallengeLogDaoImpl;
import com.hz.world.core.dao.model.ChallengeConfig;
import com.hz.world.core.dao.model.UserChallengeLog;
import com.hz.world.core.domain.dto.ChallengeDTO;
import com.hz.world.core.service.ChallengeService;
import com.hz.world.core.service.UserElementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 22, 2020 
 */
@Slf4j
@Service
@Transactional
public class ChallengeServiceImpl implements ChallengeService {

	@Autowired
	private ConfigCacheUtil configCacheUtil;
	@Autowired
	private UserChallengeLogDaoImpl userChallengeLogDao;
	
    @Autowired
	private UserElementService userElementService;

	@Override
	public ResultDTO<String> challenge(Long userId, Integer element,Integer elementWeight, Integer totalWeight) {
		ResultDTO<String> resultDTO = new ResultDTO<String>();
		try {
			//1-1 获取整个challenge列表, 获取用户的element挑战列表
			List<ChallengeConfig> configList = configCacheUtil.getChallengeList();
			List<UserChallengeLog> userChallengeLogList = userChallengeLogDao.getUserFinishedChallenges(userId, element);
			//1-2 查找目前能到达的成就
			if(configList != null && configList.size() > 0 )
			{
				for(ChallengeConfig config:configList) {
					if(config.getElement().equals(element) && config.getWeight()<=elementWeight) {
						Integer reward_kind = config.getRewardElement();
						Integer power = config.getPower();
						// 2. log里面判断该成就是否已经领取
						//3. 如果达到新的成就没有领取，则获取成就，
						// 增加元素的总体收益，UserElementService里的addElementAdd方法，然后记录日志
						if(!isInList(config.getId(), userChallengeLogList)){
							//全体收益单独考虑
							if(config.getRewardElement().equals(11)) {
								userElementService.addTotalAdd(userId, ElementAdd.CHALLENGE.getCode(), config.getPower());
							}
							// 单元素收益
							else{
								userElementService.addElementAdd(userId, config.getRewardElement(), ElementAdd.CHALLENGE.getCode(), config.getPower()+"");
							}
							// 增加日志记录
							UserChallengeLog record  = new UserChallengeLog();
							record.setUserId(userId);
							record.setChallengeId(config.getId());
							record.setElement(config.getElement());
							userChallengeLogDao.insert(record);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultDTO.set(ResultCodeEnum.SUCCESS, "ok");
		return resultDTO;
	}

	private boolean isInList(Integer configId,List<UserChallengeLog> list) {
		if (list != null && list.size() > 0) {
			for (UserChallengeLog userChallengeLog : list) {
				if (userChallengeLog.getChallengeId().equals(configId)) {
					return true;
				}
			}
		}
		return false;
	}
	// 某个element的挑战情况
	public List<ChallengeDTO> userChallengeElementList(Long userId, Integer element){
		List<ChallengeDTO> resultList = new ArrayList<ChallengeDTO>();
		List<ChallengeConfig> configList = configCacheUtil.getChallengeList();
		List<UserChallengeLog> userChallengeList = userChallengeLogDao.getUserFinishedChallenges(userId, element);
		for (ChallengeConfig config : configList) {
			ChallengeDTO challengeDTO = new ChallengeDTO();
			challengeDTO.setId(config.getId());
			challengeDTO.setFinish(false);
			if (isInList(config.getId(), userChallengeList)) {
				challengeDTO.setFinish(true);
			}
			resultList.add(challengeDTO);
		}
		return resultList;
	}
	// 已经达成挑战数据
	// Returns: [achivedCnt, totalCnt]
	public List<Integer> userAchivedChallenge(Long userId)
	{
		List<Integer> resultList = new ArrayList<Integer>();
		List<ChallengeConfig> configList = configCacheUtil.getChallengeList();
		int userAchivedChallengesCnt = userChallengeLogDao.getTotalCnt(userId);
		Integer totalCnt = configList.size();
		Integer achivedCnt = Math.max(0, userAchivedChallengesCnt);
		resultList.add(achivedCnt);
		resultList.add(totalCnt);
		return resultList;

	}
}
