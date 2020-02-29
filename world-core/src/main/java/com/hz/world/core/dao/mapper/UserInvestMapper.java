package com.hz.world.core.dao.mapper;

import com.hz.world.core.dao.model.UserInvest;
import com.hz.world.core.dao.model.UserInvestExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserInvestMapper {
    int countByExample(UserInvestExample example);

    int deleteByExample(UserInvestExample example);

    int deleteByPrimaryKey(@Param("investId") Integer investId, @Param("userId") Long userId);

    int insert(UserInvest record);

    int insertSelective(UserInvest record);

    List<UserInvest> selectByExample(UserInvestExample example);

    int updateByExampleSelective(@Param("record") UserInvest record, @Param("example") UserInvestExample example);

    int updateByExample(@Param("record") UserInvest record, @Param("example") UserInvestExample example);
}