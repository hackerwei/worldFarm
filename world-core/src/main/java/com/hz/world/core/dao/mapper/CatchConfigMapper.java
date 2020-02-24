package com.hz.world.core.dao.mapper;

import com.hz.world.core.dao.model.CatchConfig;
import com.hz.world.core.dao.model.CatchConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CatchConfigMapper {
    int countByExample(CatchConfigExample example);

    int deleteByExample(CatchConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CatchConfig record);

    int insertSelective(CatchConfig record);

    List<CatchConfig> selectByExample(CatchConfigExample example);

    CatchConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CatchConfig record, @Param("example") CatchConfigExample example);

    int updateByExample(@Param("record") CatchConfig record, @Param("example") CatchConfigExample example);

    int updateByPrimaryKeySelective(CatchConfig record);

    int updateByPrimaryKey(CatchConfig record);
}