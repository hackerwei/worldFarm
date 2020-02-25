package com.hz.world.account.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface UserBaseInfoExtMapper {
	@Update("update user_base_info set level = level+1 where user_id = #{userId }")
	int addUserLevel(@Param("userId")Long userId);
	@Update("update user_base_info set cash = cash+#{num} where user_id = #{userId } and cash >= -#{num}")
	int updateUserCash(@Param("userId")Long userId, @Param("num")Integer num);
	@Update("update user_base_info set diamond = diamond+#{num} where user_id = #{userId } and diamond >= -#{num}")
    int updateUserDiamond(@Param("userId")Long userId, @Param("num")Integer num) ;
}