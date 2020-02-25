package com.hz.world.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hz.world.core.dao.mapper.RechargeConfigMapper;
import com.hz.world.core.dao.model.RechargeConfig;
import com.hz.world.core.dao.model.RechargeConfigExample;


/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 22, 2020
 */
@Repository
public class RechargeConfigDaoImpl {
    @Autowired
    private RechargeConfigMapper catchMapper;
    
  
    public List<RechargeConfig> findAll() {
        return catchMapper.selectByExample(new RechargeConfigExample());
    }
   
    

}
