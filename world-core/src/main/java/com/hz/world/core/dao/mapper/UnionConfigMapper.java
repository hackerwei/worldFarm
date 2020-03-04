package com.hz.world.core.dao.mapper;

import com.hz.world.core.dao.model.UnionConfig;
import com.hz.world.core.dao.model.UnionConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UnionConfigMapper {
    int countByExample(UnionConfigExample example);

    int deleteByExample(UnionConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UnionConfig record);

    int insertSelective(UnionConfig record);

    List<UnionConfig> selectByExample(UnionConfigExample example);

    UnionConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UnionConfig record, @Param("example") UnionConfigExample example);

    int updateByExample(@Param("record") UnionConfig record, @Param("example") UnionConfigExample example);

    int updateByPrimaryKeySelective(UnionConfig record);

    int updateByPrimaryKey(UnionConfig record);
}