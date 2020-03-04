package com.hz.world.core.dao.mapper;

import com.hz.world.core.dao.model.UserUnionIncomeLog;
import com.hz.world.core.dao.model.UserUnionIncomeLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserUnionIncomeLogMapper {
    int countByExample(UserUnionIncomeLogExample example);

    int deleteByExample(UserUnionIncomeLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserUnionIncomeLog record);

    int insertSelective(UserUnionIncomeLog record);

    List<UserUnionIncomeLog> selectByExample(UserUnionIncomeLogExample example);

    UserUnionIncomeLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserUnionIncomeLog record, @Param("example") UserUnionIncomeLogExample example);

    int updateByExample(@Param("record") UserUnionIncomeLog record, @Param("example") UserUnionIncomeLogExample example);

    int updateByPrimaryKeySelective(UserUnionIncomeLog record);

    int updateByPrimaryKey(UserUnionIncomeLog record);
}