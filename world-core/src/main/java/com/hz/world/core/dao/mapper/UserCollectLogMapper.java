package com.hz.world.core.dao.mapper;

import com.hz.world.core.dao.model.UserCollectLog;
import com.hz.world.core.dao.model.UserCollectLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserCollectLogMapper {
    int countByExample(UserCollectLogExample example);

    int deleteByExample(UserCollectLogExample example);

    int deleteByPrimaryKey(@Param("collectId") Integer collectId, @Param("userId") Long userId);

    int insert(UserCollectLog record);

    int insertSelective(UserCollectLog record);

    List<UserCollectLog> selectByExample(UserCollectLogExample example);

    UserCollectLog selectByPrimaryKey(@Param("collectId") Integer collectId, @Param("userId") Long userId);

    int updateByExampleSelective(@Param("record") UserCollectLog record, @Param("example") UserCollectLogExample example);

    int updateByExample(@Param("record") UserCollectLog record, @Param("example") UserCollectLogExample example);

    int updateByPrimaryKeySelective(UserCollectLog record);

    int updateByPrimaryKey(UserCollectLog record);
}