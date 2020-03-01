package com.hz.world.core.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


public interface UserElementExtMapper {
	
	@Select("select sum(level) from user_element where user_id = #{userId}")
	public int getTotalWeight(@Param("userId")Long userId);
   
}