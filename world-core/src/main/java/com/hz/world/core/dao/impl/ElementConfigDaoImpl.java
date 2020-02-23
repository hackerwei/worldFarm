package com.hz.world.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hz.world.core.dao.mapper.ElementConfigMapper;
import com.hz.world.core.dao.model.ElementConfig;
import com.hz.world.core.dao.model.ElementConfigExample;


/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 22, 2020 
 */
@Repository
public class ElementConfigDaoImpl {
    @Autowired
    private ElementConfigMapper elementMapper;
    
  
    public List<ElementConfig> findAll() {
        return elementMapper.selectByExample(new ElementConfigExample());
    }
   
    

}
