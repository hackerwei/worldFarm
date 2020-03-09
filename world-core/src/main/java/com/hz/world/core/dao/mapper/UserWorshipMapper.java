package com.hz.world.core.dao.mapper;

import com.hz.world.core.dao.model.UserWorship;
import com.hz.world.core.dao.model.UserWorshipExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserWorshipMapper {
    int countByExample(UserWorshipExample example);

    int deleteByExample(UserWorshipExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(UserWorship record);

    int insertSelective(UserWorship record);

    List<UserWorship> selectByExample(UserWorshipExample example);

    UserWorship selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") UserWorship record, @Param("example") UserWorshipExample example);

    int updateByExample(@Param("record") UserWorship record, @Param("example") UserWorshipExample example);

    int updateByPrimaryKeySelective(UserWorship record);

    int updateByPrimaryKey(UserWorship record);
}