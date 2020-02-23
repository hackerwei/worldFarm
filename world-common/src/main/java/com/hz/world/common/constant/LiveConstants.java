package com.hz.world.common.constant;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 全局常量类
 * <pre>
 * Class Name: LiveConstants.java
 * @author liuxiao
 * Modifications:
 * Modifier liuxiao; 2017年8月10日; Create new Class LiveConstants.java.
 * </pre>
 */
public final class LiveConstants {

	/** 用户tag配置,Map<标签type, 标签名称> */
	public static Map<String, String> TAG_MAP = new HashMap<String, String>();
			
	/** 默认渠道名称 **/
	public static final String DEFAULT_CHANNEL ="default";
	
	/** 应用当前所在环境 开发=dev 测试=tst 预发=pre 正式=prd*/
	public static String CURRENT_ENV = "";
}
