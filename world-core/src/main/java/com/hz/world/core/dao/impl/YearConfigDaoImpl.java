package com.hz.world.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hz.world.core.dao.mapper.YearConfigMapper;
import com.hz.world.core.dao.model.YearConfig;
import com.hz.world.core.dao.model.YearConfigExample;


/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 22, 2020
 */
@Repository
public class YearConfigDaoImpl {
    @Autowired
    private YearConfigMapper catchMapper;
    
  
    public List<YearConfig> findAll() {
        return catchMapper.selectByExample(new YearConfigExample());
    }
   
    

}
