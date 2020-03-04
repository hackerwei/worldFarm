package com.hz.world.core.dao.mapper;

import com.hz.world.core.dao.model.UserLimitShare;
import com.hz.world.core.dao.model.UserLimitShareExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserLimitShareMapper {
    int countByExample(UserLimitShareExample example);

    int deleteByExample(UserLimitShareExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserLimitShare record);

    int insertSelective(UserLimitShare record);

    List<UserLimitShare> selectByExample(UserLimitShareExample example);

    UserLimitShare selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserLimitShare record, @Param("example") UserLimitShareExample example);

    int updateByExample(@Param("record") UserLimitShare record, @Param("example") UserLimitShareExample example);

    int updateByPrimaryKeySelective(UserLimitShare record);

    int updateByPrimaryKey(UserLimitShare record);
}