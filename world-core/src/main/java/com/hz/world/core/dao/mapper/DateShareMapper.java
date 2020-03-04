package com.hz.world.core.dao.mapper;

import com.hz.world.core.dao.model.DateShare;
import com.hz.world.core.dao.model.DateShareExample;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DateShareMapper {
    int countByExample(DateShareExample example);

    int deleteByExample(DateShareExample example);

    int deleteByPrimaryKey(Date date);

    int insert(DateShare record);

    int insertSelective(DateShare record);

    List<DateShare> selectByExample(DateShareExample example);

    DateShare selectByPrimaryKey(Date date);

    int updateByExampleSelective(@Param("record") DateShare record, @Param("example") DateShareExample example);

    int updateByExample(@Param("record") DateShare record, @Param("example") DateShareExample example);

    int updateByPrimaryKeySelective(DateShare record);

    int updateByPrimaryKey(DateShare record);
}