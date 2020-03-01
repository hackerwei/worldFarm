package com.hz.world.core.dao.mapper;

import com.hz.world.core.dao.model.UserChallengeLog;
import com.hz.world.core.dao.model.UserChallengeLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserChallengeLogMapper {
    int countByExample(UserChallengeLogExample example);

    int deleteByExample(UserChallengeLogExample example);

    int deleteByPrimaryKey(@Param("challengeId") Integer challengeId, @Param("userId") Long userId);

    int insert(UserChallengeLog record);

    int insertSelective(UserChallengeLog record);

    List<UserChallengeLog> selectByExample(UserChallengeLogExample example);

    UserChallengeLog selectByPrimaryKey(@Param("challengeId") Integer challengeId, @Param("userId") Long userId);

    int updateByExampleSelective(@Param("record") UserChallengeLog record, @Param("example") UserChallengeLogExample example);

    int updateByExample(@Param("record") UserChallengeLog record, @Param("example") UserChallengeLogExample example);

    int updateByPrimaryKeySelective(UserChallengeLog record);

    int updateByPrimaryKey(UserChallengeLog record);
}