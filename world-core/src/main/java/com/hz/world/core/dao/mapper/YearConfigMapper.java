package com.hz.world.core.dao.mapper;

import com.hz.world.core.dao.model.YearConfig;
import com.hz.world.core.dao.model.YearConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YearConfigMapper {
    int countByExample(YearConfigExample example);

    int deleteByExample(YearConfigExample example);

    int deleteByPrimaryKey(Integer year);

    int insert(YearConfig record);

    int insertSelective(YearConfig record);

    List<YearConfig> selectByExample(YearConfigExample example);

    YearConfig selectByPrimaryKey(Integer year);

    int updateByExampleSelective(@Param("record") YearConfig record, @Param("example") YearConfigExample example);

    int updateByExample(@Param("record") YearConfig record, @Param("example") YearConfigExample example);

    int updateByPrimaryKeySelective(YearConfig record);

    int updateByPrimaryKey(YearConfig record);
}