package com.hz.world.core.dao.mapper;

import com.hz.world.core.dao.model.UserUnionStep;
import com.hz.world.core.dao.model.UserUnionStepExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserUnionStepMapper {
    int countByExample(UserUnionStepExample example);

    int deleteByExample(UserUnionStepExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(UserUnionStep record);

    int insertSelective(UserUnionStep record);

    List<UserUnionStep> selectByExample(UserUnionStepExample example);

    UserUnionStep selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") UserUnionStep record, @Param("example") UserUnionStepExample example);

    int updateByExample(@Param("record") UserUnionStep record, @Param("example") UserUnionStepExample example);

    int updateByPrimaryKeySelective(UserUnionStep record);

    int updateByPrimaryKey(UserUnionStep record);
}