package com.hz.world.core.dao.mapper;

import com.hz.world.core.dao.model.UserTotalAdd;
import com.hz.world.core.dao.model.UserTotalAddExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserTotalAddMapper {
    int countByExample(UserTotalAddExample example);

    int deleteByExample(UserTotalAddExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(UserTotalAdd record);

    int insertSelective(UserTotalAdd record);

    List<UserTotalAdd> selectByExample(UserTotalAddExample example);

    UserTotalAdd selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") UserTotalAdd record, @Param("example") UserTotalAddExample example);

    int updateByExample(@Param("record") UserTotalAdd record, @Param("example") UserTotalAddExample example);

    int updateByPrimaryKeySelective(UserTotalAdd record);

    int updateByPrimaryKey(UserTotalAdd record);
}