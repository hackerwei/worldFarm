package com.hz.world.core.dao.mapper;

import com.hz.world.core.dao.model.TitleConfig;
import com.hz.world.core.dao.model.TitleConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TitleConfigMapper {
    int countByExample(TitleConfigExample example);

    int deleteByExample(TitleConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TitleConfig record);

    int insertSelective(TitleConfig record);

    List<TitleConfig> selectByExample(TitleConfigExample example);

    TitleConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TitleConfig record, @Param("example") TitleConfigExample example);

    int updateByExample(@Param("record") TitleConfig record, @Param("example") TitleConfigExample example);

    int updateByPrimaryKeySelective(TitleConfig record);

    int updateByPrimaryKey(TitleConfig record);
}