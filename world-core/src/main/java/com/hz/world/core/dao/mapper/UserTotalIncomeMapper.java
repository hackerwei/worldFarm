package com.hz.world.core.dao.mapper;

import com.hz.world.core.dao.model.UserTotalIncome;
import com.hz.world.core.dao.model.UserTotalIncomeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserTotalIncomeMapper {
    int countByExample(UserTotalIncomeExample example);

    int deleteByExample(UserTotalIncomeExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(UserTotalIncome record);

    int insertSelective(UserTotalIncome record);

    List<UserTotalIncome> selectByExample(UserTotalIncomeExample example);

    UserTotalIncome selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") UserTotalIncome record, @Param("example") UserTotalIncomeExample example);

    int updateByExample(@Param("record") UserTotalIncome record, @Param("example") UserTotalIncomeExample example);

    int updateByPrimaryKeySelective(UserTotalIncome record);

    int updateByPrimaryKey(UserTotalIncome record);
}