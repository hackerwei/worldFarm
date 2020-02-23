package com.hz.world.core.dao.mapper;

import com.hz.world.core.dao.model.UserCoin;
import com.hz.world.core.dao.model.UserCoinExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserCoinMapper {
    int countByExample(UserCoinExample example);

    int deleteByExample(UserCoinExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(UserCoin record);

    int insertSelective(UserCoin record);

    List<UserCoin> selectByExample(UserCoinExample example);

    UserCoin selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") UserCoin record, @Param("example") UserCoinExample example);

    int updateByExample(@Param("record") UserCoin record, @Param("example") UserCoinExample example);

    int updateByPrimaryKeySelective(UserCoin record);

    int updateByPrimaryKey(UserCoin record);
}