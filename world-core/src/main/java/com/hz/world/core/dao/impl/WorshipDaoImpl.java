package com.hz.world.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hz.world.core.dao.mapper.WorshipMapper;
import com.hz.world.core.dao.model.Worship;
import com.hz.world.core.dao.model.WorshipExample;


/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Feb 22, 2020
 */
@Repository
public class WorshipDaoImpl {
    @Autowired
    private WorshipMapper worshipMapper;
    
  
    public List<Worship> findAll() {
        return worshipMapper.selectByExample(new WorshipExample());
    }
   
    

}
