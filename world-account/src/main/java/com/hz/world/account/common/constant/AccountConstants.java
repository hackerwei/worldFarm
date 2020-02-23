package com.hz.world.account.common.constant;

public class AccountConstants {

	/******************************** IM Redis Cache Key ************************************/
	public final static String LIVE_IM_USER_TOKEN ="live:im:user:token:%s";

	
	/**
	 * 用户新增queue
	 */
	public final static String USER_QUEUE = "user_queue";
	/**
	 * 用户邀请queue
	 */
	public final static String USER_INVITE_QUEUE = "user_invite_queue";
	
	/**
	 * 用户更新
	 */
	public final static String USER_UPDATE_QUEUE = "user_update_queue";
	
	/**
	 * 用户更新同步数据
	 */
	public final static String USER_UPDATE_INFO_QUEUE = "user_update_info_queue";
	
}
