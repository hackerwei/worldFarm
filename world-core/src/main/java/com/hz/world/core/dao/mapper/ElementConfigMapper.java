package com.hz.world.core.dao.mapper;

import com.hz.world.core.dao.model.ElementConfig;
import com.hz.world.core.dao.model.ElementConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ElementConfigMapper {
    int countByExample(ElementConfigExample example);

    int deleteByExample(ElementConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ElementConfig record);

    int insertSelective(ElementConfig record);

    List<ElementConfig> selectByExample(ElementConfigExample example);

    ElementConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ElementConfig record, @Param("example") ElementConfigExample example);

    int updateByExample(@Param("record") ElementConfig record, @Param("example") ElementConfigExample example);

    int updateByPrimaryKeySelective(ElementConfig record);

    int updateByPrimaryKey(ElementConfig record);
}