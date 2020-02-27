package com.hz.world.core.dao.mapper;

import com.hz.world.core.dao.model.UserRewardLog;
import com.hz.world.core.dao.model.UserRewardLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRewardLogMapper {
    int countByExample(UserRewardLogExample example);

    int deleteByExample(UserRewardLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserRewardLog record);

    int insertSelective(UserRewardLog record);

    List<UserRewardLog> selectByExample(UserRewardLogExample example);

    UserRewardLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserRewardLog record, @Param("example") UserRewardLogExample example);

    int updateByExample(@Param("record") UserRewardLog record, @Param("example") UserRewardLogExample example);

    int updateByPrimaryKeySelective(UserRewardLog record);

    int updateByPrimaryKey(UserRewardLog record);
}