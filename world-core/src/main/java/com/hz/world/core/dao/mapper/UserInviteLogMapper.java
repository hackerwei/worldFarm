package com.hz.world.core.dao.mapper;

import com.hz.world.core.dao.model.UserInviteLog;
import com.hz.world.core.dao.model.UserInviteLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserInviteLogMapper {
    int countByExample(UserInviteLogExample example);

    int deleteByExample(UserInviteLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserInviteLog record);

    int insertSelective(UserInviteLog record);

    List<UserInviteLog> selectByExample(UserInviteLogExample example);

    UserInviteLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserInviteLog record, @Param("example") UserInviteLogExample example);

    int updateByExample(@Param("record") UserInviteLog record, @Param("example") UserInviteLogExample example);

    int updateByPrimaryKeySelective(UserInviteLog record);

    int updateByPrimaryKey(UserInviteLog record);
}