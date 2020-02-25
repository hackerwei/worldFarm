package com.hz.world.core.dao.mapper;

import com.hz.world.core.dao.model.UserCashChangeLog;
import com.hz.world.core.dao.model.UserCashChangeLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserCashChangeLogMapper {
    int countByExample(UserCashChangeLogExample example);

    int deleteByExample(UserCashChangeLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserCashChangeLog record);

    int insertSelective(UserCashChangeLog record);

    List<UserCashChangeLog> selectByExample(UserCashChangeLogExample example);

    UserCashChangeLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserCashChangeLog record, @Param("example") UserCashChangeLogExample example);

    int updateByExample(@Param("record") UserCashChangeLog record, @Param("example") UserCashChangeLogExample example);

    int updateByPrimaryKeySelective(UserCashChangeLog record);

    int updateByPrimaryKey(UserCashChangeLog record);
}