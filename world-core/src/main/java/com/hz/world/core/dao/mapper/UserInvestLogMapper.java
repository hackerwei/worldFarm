package com.hz.world.core.dao.mapper;

import com.hz.world.core.dao.model.UserInvestLog;
import com.hz.world.core.dao.model.UserInvestLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserInvestLogMapper {
    int countByExample(UserInvestLogExample example);

    int deleteByExample(UserInvestLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserInvestLog record);

    int insertSelective(UserInvestLog record);

    List<UserInvestLog> selectByExample(UserInvestLogExample example);

    UserInvestLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserInvestLog record, @Param("example") UserInvestLogExample example);

    int updateByExample(@Param("record") UserInvestLog record, @Param("example") UserInvestLogExample example);

    int updateByPrimaryKeySelective(UserInvestLog record);

    int updateByPrimaryKey(UserInvestLog record);
}