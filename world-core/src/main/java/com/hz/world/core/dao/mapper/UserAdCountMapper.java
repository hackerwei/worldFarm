package com.hz.world.core.dao.mapper;

import com.hz.world.core.dao.model.UserAdCount;
import com.hz.world.core.dao.model.UserAdCountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserAdCountMapper {
    int countByExample(UserAdCountExample example);

    int deleteByExample(UserAdCountExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserAdCount record);

    int insertSelective(UserAdCount record);

    List<UserAdCount> selectByExample(UserAdCountExample example);

    UserAdCount selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserAdCount record, @Param("example") UserAdCountExample example);

    int updateByExample(@Param("record") UserAdCount record, @Param("example") UserAdCountExample example);

    int updateByPrimaryKeySelective(UserAdCount record);

    int updateByPrimaryKey(UserAdCount record);
}