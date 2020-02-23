package com.hz.world.account.dao.mapper;

import com.hz.world.account.dao.model.UserBaseInfo;
import com.hz.world.account.dao.model.UserBaseInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserBaseInfoMapper {
    int countByExample(UserBaseInfoExample example);

    int deleteByExample(UserBaseInfoExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(UserBaseInfo record);

    int insertSelective(UserBaseInfo record);

    List<UserBaseInfo> selectByExample(UserBaseInfoExample example);

    UserBaseInfo selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") UserBaseInfo record, @Param("example") UserBaseInfoExample example);

    int updateByExample(@Param("record") UserBaseInfo record, @Param("example") UserBaseInfoExample example);

    int updateByPrimaryKeySelective(UserBaseInfo record);

    int updateByPrimaryKey(UserBaseInfo record);
}