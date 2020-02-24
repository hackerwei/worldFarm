package com.hz.world.core.dao.mapper;

import com.hz.world.core.dao.model.UserCatch;
import com.hz.world.core.dao.model.UserCatchExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserCatchMapper {
    int countByExample(UserCatchExample example);

    int deleteByExample(UserCatchExample example);

    int deleteByPrimaryKey(@Param("userId") Long userId, @Param("year") Integer year);

    int insert(UserCatch record);

    int insertSelective(UserCatch record);

    List<UserCatch> selectByExample(UserCatchExample example);

    UserCatch selectByPrimaryKey(@Param("userId") Long userId, @Param("year") Integer year);

    int updateByExampleSelective(@Param("record") UserCatch record, @Param("example") UserCatchExample example);

    int updateByExample(@Param("record") UserCatch record, @Param("example") UserCatchExample example);

    int updateByPrimaryKeySelective(UserCatch record);

    int updateByPrimaryKey(UserCatch record);
}