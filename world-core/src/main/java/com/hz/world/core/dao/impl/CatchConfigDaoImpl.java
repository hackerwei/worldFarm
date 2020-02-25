package com.hz.world.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hz.world.core.dao.mapper.CatchConfigMapper;
import com.hz.world.core.dao.model.CatchConfig;
import com.hz.world.core.dao.model.CatchConfigExample;


/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 22, 2020
 */
@Repository
public class CatchConfigDaoImpl {
    @Autowired
    private CatchConfigMapper catchMapper;
    
  
    public List<CatchConfig> findAll() {
        return catchMapper.selectByExample(new CatchConfigExample());
    }
   
    

}
