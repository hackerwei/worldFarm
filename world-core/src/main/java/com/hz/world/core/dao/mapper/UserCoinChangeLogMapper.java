package com.hz.world.core.dao.mapper;

import com.hz.world.core.dao.model.UserCoinChangeLog;
import com.hz.world.core.dao.model.UserCoinChangeLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserCoinChangeLogMapper {
    int countByExample(UserCoinChangeLogExample example);

    int deleteByExample(UserCoinChangeLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserCoinChangeLog record);

    int insertSelective(UserCoinChangeLog record);

    List<UserCoinChangeLog> selectByExample(UserCoinChangeLogExample example);

    UserCoinChangeLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserCoinChangeLog record, @Param("example") UserCoinChangeLogExample example);

    int updateByExample(@Param("record") UserCoinChangeLog record, @Param("example") UserCoinChangeLogExample example);

    int updateByPrimaryKeySelective(UserCoinChangeLog record);

    int updateByPrimaryKey(UserCoinChangeLog record);
}