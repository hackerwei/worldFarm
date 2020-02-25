package com.hz.world.core.dao.mapper;

import com.hz.world.core.dao.model.RechargeConfig;
import com.hz.world.core.dao.model.RechargeConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RechargeConfigMapper {
    int countByExample(RechargeConfigExample example);

    int deleteByExample(RechargeConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RechargeConfig record);

    int insertSelective(RechargeConfig record);

    List<RechargeConfig> selectByExample(RechargeConfigExample example);

    RechargeConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RechargeConfig record, @Param("example") RechargeConfigExample example);

    int updateByExample(@Param("record") RechargeConfig record, @Param("example") RechargeConfigExample example);

    int updateByPrimaryKeySelective(RechargeConfig record);

    int updateByPrimaryKey(RechargeConfig record);
}