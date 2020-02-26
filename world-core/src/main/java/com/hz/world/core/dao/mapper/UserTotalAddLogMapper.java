package com.hz.world.core.dao.mapper;

import com.hz.world.core.dao.model.UserTotalAddLog;
import com.hz.world.core.dao.model.UserTotalAddLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserTotalAddLogMapper {
    int countByExample(UserTotalAddLogExample example);

    int deleteByExample(UserTotalAddLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserTotalAddLog record);

    int insertSelective(UserTotalAddLog record);

    List<UserTotalAddLog> selectByExample(UserTotalAddLogExample example);

    UserTotalAddLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserTotalAddLog record, @Param("example") UserTotalAddLogExample example);

    int updateByExample(@Param("record") UserTotalAddLog record, @Param("example") UserTotalAddLogExample example);

    int updateByPrimaryKeySelective(UserTotalAddLog record);

    int updateByPrimaryKey(UserTotalAddLog record);
}