package com.hz.world.core.dao.mapper;

import com.hz.world.core.dao.model.Worship;
import com.hz.world.core.dao.model.WorshipExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WorshipMapper {
    int countByExample(WorshipExample example);

    int deleteByExample(WorshipExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Worship record);

    int insertSelective(Worship record);

    List<Worship> selectByExample(WorshipExample example);

    Worship selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Worship record, @Param("example") WorshipExample example);

    int updateByExample(@Param("record") Worship record, @Param("example") WorshipExample example);

    int updateByPrimaryKeySelective(Worship record);

    int updateByPrimaryKey(Worship record);
}