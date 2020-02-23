package com.hz.world.core.dao.mapper;

import com.hz.world.core.dao.model.UserElement;
import com.hz.world.core.dao.model.UserElementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserElementMapper {
    int countByExample(UserElementExample example);

    int deleteByExample(UserElementExample example);

    int deleteByPrimaryKey(@Param("element") Integer element, @Param("userId") Long userId);

    int insert(UserElement record);

    int insertSelective(UserElement record);

    List<UserElement> selectByExample(UserElementExample example);

    UserElement selectByPrimaryKey(@Param("element") Integer element, @Param("userId") Long userId);

    int updateByExampleSelective(@Param("record") UserElement record, @Param("example") UserElementExample example);

    int updateByExample(@Param("record") UserElement record, @Param("example") UserElementExample example);

    int updateByPrimaryKeySelective(UserElement record);

    int updateByPrimaryKey(UserElement record);
}