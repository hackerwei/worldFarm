package com.hz.world.common.constant;

public class IndexRedisConstants {
	
	/** 首页Tab数据缓存  set, index:tab.list:[%channel%]*/
	public static final String INDEX_TAB_LIST = "index:tab:list:%s";
	
	/** 首页Banner数据缓存  set, index:banner.list:[%terminalType%]:[%position%]*/
	public static final String INDEX_BANNER_LIST = "index:banner:list:%d:%d";
	
	/** 首页Tab展示数据ID缓存  zset, index:tab:data:id:list:[%tabId%]*/
	public static final String INDEX_TAB_DATA_ID_LIST = "index:tab:data:id:list:%s";
	
	/** 首页Tab展示数据缓存  zset, index:tab:data:list:[%tabId%]*/
	public static final String INDEX_TAB_DATA_LIST_TEMP = "index:tab:data:list:%s:temp";
	
	/** 首页Tab展示数据缓存  zset, index:tab:data:list:[%tabId%]*/
	public static final String INDEX_TAB_DATA_LIST = "index:tab:data:list:%s";

}
