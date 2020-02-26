package com.hz.world.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hz.world.core.dao.mapper.ShopConfigMapper;
import com.hz.world.core.dao.model.ShopConfig;
import com.hz.world.core.dao.model.ShopConfigExample;


/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 22, 2020
 */
@Repository
public class ShopConfigDaoImpl {
    @Autowired
    private ShopConfigMapper catchMapper;
    
  
    public List<ShopConfig> findAll() {
        return catchMapper.selectByExample(new ShopConfigExample());
    }
   
    

}
