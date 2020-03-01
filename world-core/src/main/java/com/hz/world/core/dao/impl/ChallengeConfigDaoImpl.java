package com.hz.world.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hz.world.core.dao.mapper.ChallengeConfigMapper;
import com.hz.world.core.dao.model.ChallengeConfig;
import com.hz.world.core.dao.model.ChallengeConfigExample;


/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 22, 2020
 */
@Repository
public class ChallengeConfigDaoImpl {
    @Autowired
    private ChallengeConfigMapper challengeMapper;
    
  
    public List<ChallengeConfig> findAll() {
        return challengeMapper.selectByExample(new ChallengeConfigExample());
    }
   
    

}
