package com.hz.world.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hz.world.core.dao.mapper.TitleConfigMapper;
import com.hz.world.core.dao.model.TitleConfig;
import com.hz.world.core.dao.model.TitleConfigExample;


/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 22, 2020 
 */
@Repository
public class TitleConfigDaoImpl {
    @Autowired
    private TitleConfigMapper elementMapper;
    
  
    public List<TitleConfig> findAll() {
        return elementMapper.selectByExample(new TitleConfigExample());
    }
   
    

}
