package com.hz.world.core.dao.mapper;

import com.hz.world.core.dao.model.ChallengeConfig;
import com.hz.world.core.dao.model.ChallengeConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChallengeConfigMapper {
    int countByExample(ChallengeConfigExample example);

    int deleteByExample(ChallengeConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ChallengeConfig record);

    int insertSelective(ChallengeConfig record);

    List<ChallengeConfig> selectByExample(ChallengeConfigExample example);

    ChallengeConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ChallengeConfig record, @Param("example") ChallengeConfigExample example);

    int updateByExample(@Param("record") ChallengeConfig record, @Param("example") ChallengeConfigExample example);

    int updateByPrimaryKeySelective(ChallengeConfig record);

    int updateByPrimaryKey(ChallengeConfig record);
}