package com.hz.world.core.dao.mapper;

import com.hz.world.core.dao.model.UserDiamondChangeLog;
import com.hz.world.core.dao.model.UserDiamondChangeLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserDiamondChangeLogMapper {
    int countByExample(UserDiamondChangeLogExample example);

    int deleteByExample(UserDiamondChangeLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserDiamondChangeLog record);

    int insertSelective(UserDiamondChangeLog record);

    List<UserDiamondChangeLog> selectByExample(UserDiamondChangeLogExample example);

    UserDiamondChangeLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserDiamondChangeLog record, @Param("example") UserDiamondChangeLogExample example);

    int updateByExample(@Param("record") UserDiamondChangeLog record, @Param("example") UserDiamondChangeLogExample example);

    int updateByPrimaryKeySelective(UserDiamondChangeLog record);

    int updateByPrimaryKey(UserDiamondChangeLog record);
}