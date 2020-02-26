package com.hz.world.core.dao.mapper;

import com.hz.world.core.dao.model.ShopConfig;
import com.hz.world.core.dao.model.ShopConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopConfigMapper {
    int countByExample(ShopConfigExample example);

    int deleteByExample(ShopConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopConfig record);

    int insertSelective(ShopConfig record);

    List<ShopConfig> selectByExample(ShopConfigExample example);

    ShopConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopConfig record, @Param("example") ShopConfigExample example);

    int updateByExample(@Param("record") ShopConfig record, @Param("example") ShopConfigExample example);

    int updateByPrimaryKeySelective(ShopConfig record);

    int updateByPrimaryKey(ShopConfig record);
}