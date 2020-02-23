package com.hz.world.api.common.util;

import java.util.HashSet;
import java.util.Set;


/**  
* <p>Title: DynamicUtil</p>  
* <p>Description:动态相关工具类 </p>  
* @author linyanchun  
* @date 2018年8月24日  
*/  
public class LabelUtil {

	public static Set<String> changeStringToList(String string) {
		Set<String> result = new HashSet<String>();
		if (string != null) {
			String[] tmps = string.split(",");
			for (String tmp : tmps) {
				result.add(tmp);
			}
		}
		return result;
	}

}
