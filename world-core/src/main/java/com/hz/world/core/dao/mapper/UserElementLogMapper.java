package com.hz.world.core.dao.mapper;

import com.hz.world.core.dao.model.UserElementLog;
import com.hz.world.core.dao.model.UserElementLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserElementLogMapper {
    int countByExample(UserElementLogExample example);

    int deleteByExample(UserElementLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserElementLog record);

    int insertSelective(UserElementLog record);

    List<UserElementLog> selectByExample(UserElementLogExample example);

    UserElementLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserElementLog record, @Param("example") UserElementLogExample example);

    int updateByExample(@Param("record") UserElementLog record, @Param("example") UserElementLogExample example);

    int updateByPrimaryKeySelective(UserElementLog record);

    int updateByPrimaryKey(UserElementLog record);
}