package com.hz.world.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hz.world.core.dao.mapper.CollectConfigMapper;
import com.hz.world.core.dao.model.CollectConfig;
import com.hz.world.core.dao.model.CollectConfigExample;


/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 22, 2020
 */
@Repository
public class CollectConfigDaoImpl {
    @Autowired
    private CollectConfigMapper collectMapper;
    
  
    public List<CollectConfig> findAll() {
        return collectMapper.selectByExample(new CollectConfigExample());
    }
   
    

}
