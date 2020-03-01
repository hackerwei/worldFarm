package com.hz.world.core.dao.mapper;

import com.hz.world.core.dao.model.CollectConfig;
import com.hz.world.core.dao.model.CollectConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CollectConfigMapper {
    int countByExample(CollectConfigExample example);

    int deleteByExample(CollectConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CollectConfig record);

    int insertSelective(CollectConfig record);

    List<CollectConfig> selectByExample(CollectConfigExample example);

    CollectConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CollectConfig record, @Param("example") CollectConfigExample example);

    int updateByExample(@Param("record") CollectConfig record, @Param("example") CollectConfigExample example);

    int updateByPrimaryKeySelective(CollectConfig record);

    int updateByPrimaryKey(CollectConfig record);
}