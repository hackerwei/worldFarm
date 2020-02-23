package com.hz.world.account.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface UserBaseInfoExtMapper {
	@Update("update_user_base_info set level = level+1 where user_id = #{userId }")
	int addUserLevel(@Param("userId")Long userId);
}