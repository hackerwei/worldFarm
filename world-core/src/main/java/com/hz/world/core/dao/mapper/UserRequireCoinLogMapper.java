package com.hz.world.core.dao.mapper;

import com.hz.world.core.dao.model.UserRequireCoinLog;
import com.hz.world.core.dao.model.UserRequireCoinLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRequireCoinLogMapper {
    int countByExample(UserRequireCoinLogExample example);

    int deleteByExample(UserRequireCoinLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserRequireCoinLog record);

    int insertSelective(UserRequireCoinLog record);

    List<UserRequireCoinLog> selectByExample(UserRequireCoinLogExample example);

    UserRequireCoinLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserRequireCoinLog record, @Param("example") UserRequireCoinLogExample example);

    int updateByExample(@Param("record") UserRequireCoinLog record, @Param("example") UserRequireCoinLogExample example);

    int updateByPrimaryKeySelective(UserRequireCoinLog record);

    int updateByPrimaryKey(UserRequireCoinLog record);
}