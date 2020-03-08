package com.hz.world.core.dao.mapper;

import com.hz.world.core.dao.model.UserElementManager;
import com.hz.world.core.dao.model.UserElementManagerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserElementManagerMapper {
    int countByExample(UserElementManagerExample example);

    int deleteByExample(UserElementManagerExample example);

    int deleteByPrimaryKey(@Param("element") Integer element, @Param("userId") Long userId);

    int insert(UserElementManager record);

    int insertSelective(UserElementManager record);

    List<UserElementManager> selectByExample(UserElementManagerExample example);

    UserElementManager selectByPrimaryKey(@Param("element") Integer element, @Param("userId") Long userId);

    int updateByExampleSelective(@Param("record") UserElementManager record, @Param("example") UserElementManagerExample example);

    int updateByExample(@Param("record") UserElementManager record, @Param("example") UserElementManagerExample example);

    int updateByPrimaryKeySelective(UserElementManager record);

    int updateByPrimaryKey(UserElementManager record);
}