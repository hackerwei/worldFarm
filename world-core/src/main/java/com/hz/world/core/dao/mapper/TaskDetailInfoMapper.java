package com.hz.world.core.dao.mapper;

import com.hz.world.core.dao.model.TaskDetailInfo;
import com.hz.world.core.dao.model.TaskDetailInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskDetailInfoMapper {
    int countByExample(TaskDetailInfoExample example);

    int deleteByExample(TaskDetailInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TaskDetailInfo record);

    int insertSelective(TaskDetailInfo record);

    List<TaskDetailInfo> selectByExample(TaskDetailInfoExample example);

    TaskDetailInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TaskDetailInfo record, @Param("example") TaskDetailInfoExample example);

    int updateByExample(@Param("record") TaskDetailInfo record, @Param("example") TaskDetailInfoExample example);

    int updateByPrimaryKeySelective(TaskDetailInfo record);

    int updateByPrimaryKey(TaskDetailInfo record);
}