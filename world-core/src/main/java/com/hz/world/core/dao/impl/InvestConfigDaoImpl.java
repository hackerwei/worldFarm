package com.hz.world.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hz.world.core.dao.mapper.InvestConfigMapper;
import com.hz.world.core.dao.model.InvestConfig;
import com.hz.world.core.dao.model.InvestConfigExample;


/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 22, 2020
 */
@Repository
public class InvestConfigDaoImpl {
    @Autowired
    private InvestConfigMapper investMapper;
    
  
    public List<InvestConfig> findAll() {
        return investMapper.selectByExample(new InvestConfigExample());
    }
   
    

}
