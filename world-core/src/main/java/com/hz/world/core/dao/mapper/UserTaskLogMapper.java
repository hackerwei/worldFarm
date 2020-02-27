package com.hz.world.core.dao.mapper;

import com.hz.world.core.dao.model.UserTaskLog;
import com.hz.world.core.dao.model.UserTaskLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserTaskLogMapper {
    int countByExample(UserTaskLogExample example);

    int deleteByExample(UserTaskLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserTaskLog record);

    int insertSelective(UserTaskLog record);

    List<UserTaskLog> selectByExample(UserTaskLogExample example);

    UserTaskLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserTaskLog record, @Param("example") UserTaskLogExample example);

    int updateByExample(@Param("record") UserTaskLog record, @Param("example") UserTaskLogExample example);

    int updateByPrimaryKeySelective(UserTaskLog record);

    int updateByPrimaryKey(UserTaskLog record);
}