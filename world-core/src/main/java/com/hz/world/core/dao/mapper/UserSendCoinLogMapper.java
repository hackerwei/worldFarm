package com.hz.world.core.dao.mapper;

import com.hz.world.core.dao.model.UserSendCoinLog;
import com.hz.world.core.dao.model.UserSendCoinLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserSendCoinLogMapper {
    int countByExample(UserSendCoinLogExample example);

    int deleteByExample(UserSendCoinLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserSendCoinLog record);

    int insertSelective(UserSendCoinLog record);

    List<UserSendCoinLog> selectByExample(UserSendCoinLogExample example);

    UserSendCoinLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserSendCoinLog record, @Param("example") UserSendCoinLogExample example);

    int updateByExample(@Param("record") UserSendCoinLog record, @Param("example") UserSendCoinLogExample example);

    int updateByPrimaryKeySelective(UserSendCoinLog record);

    int updateByPrimaryKey(UserSendCoinLog record);
}