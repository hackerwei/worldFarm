package com.hz.world.core.dao.mapper;

import com.hz.world.core.dao.model.InvestConfig;
import com.hz.world.core.dao.model.InvestConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InvestConfigMapper {
    int countByExample(InvestConfigExample example);

    int deleteByExample(InvestConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InvestConfig record);

    int insertSelective(InvestConfig record);

    List<InvestConfig> selectByExample(InvestConfigExample example);

    InvestConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InvestConfig record, @Param("example") InvestConfigExample example);

    int updateByExample(@Param("record") InvestConfig record, @Param("example") InvestConfigExample example);

    int updateByPrimaryKeySelective(InvestConfig record);

    int updateByPrimaryKey(InvestConfig record);
}