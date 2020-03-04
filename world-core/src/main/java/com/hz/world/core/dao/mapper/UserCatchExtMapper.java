package com.hz.world.core.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hz.world.core.domain.dto.ForeverShareElementDTO;
import com.hz.world.core.domain.dto.OtherElementDTO;


public interface UserCatchExtMapper {
	
	@Select(" select count(*) as num , catch_id as catchId from user_catch where user_id = #{userId} GROUP BY catch_id")
	public List<OtherElementDTO> getOtherList(@Param("userId")Long userId);
	@Select(" select count(*) as num , user_id as userId from user_catch where catch_id = 1 GROUP BY user_id")
	public List<ForeverShareElementDTO> getForeverList();
   
}