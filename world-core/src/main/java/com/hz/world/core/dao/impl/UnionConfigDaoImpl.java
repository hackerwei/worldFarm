package com.hz.world.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hz.world.core.dao.mapper.UnionConfigMapper;
import com.hz.world.core.dao.model.UnionConfig;
import com.hz.world.core.dao.model.UnionConfigExample;


/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 22, 2020
 */
@Repository
public class UnionConfigDaoImpl {
    @Autowired
    private UnionConfigMapper unionMapper;
    
  
    public List<UnionConfig> findAll() {
        return unionMapper.selectByExample(new UnionConfigExample());
    }
   
    

}
