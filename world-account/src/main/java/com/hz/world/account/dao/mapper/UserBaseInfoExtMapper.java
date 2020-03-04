package com.hz.world.account.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserBaseInfoExtMapper {
	@Update("update user_base_info set level = level+1 where user_id = #{userId }")
	int addUserLevel(@Param("userId")Long userId);
	@Update("update user_base_info set cash = cash+#{num} where user_id = #{userId } and cash >= -#{num}")
	int updateUserCash(@Param("userId")Long userId, @Param("num")Double num);
	@Update("update user_base_info set diamond = diamond+#{num} where user_id = #{userId } and diamond >= -#{num}")
    int updateUserDiamond(@Param("userId")Long userId, @Param("num")Integer num) ;
	
	@Update("update user_base_info set weight = weight+#{num} where user_id = #{userId } and weight >= -#{num}")
    int updateUserWeight(@Param("userId")Long userId, @Param("num")Integer num) ;
	@Select("select sum(weight) from user_base_info where from_user_id = #{userId} ")
	int getFriendWeight(@Param("userId")Long userId);
	@Select("select count(*) from user_base_info where from_user_id = #{userId} and DATE(create_time) = CURRENT_DATE")
	int getTodayFriendCount(@Param("userId")Long userId);
}