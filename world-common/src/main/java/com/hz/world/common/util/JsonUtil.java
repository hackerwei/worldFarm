package com.hz.world.common.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class JsonUtil {
	private static ObjectMapper mapper = new ObjectMapper();

	@SuppressWarnings("unchecked")
	public static Map<String, Object> getMapFromUrl(String url) {
		try {
			return mapper.readValue(HttpClientUtils.get(url, null), Map.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将对象转为JSON字符串
	 *
	 * @param obj
	 * @return
	 */
	public static String objToJsonString(Object obj) {
		String s = null;
		try {
			s = mapper.writeValueAsString(obj);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return s;
	}

	/**
	 * JSON到Map转化
	 *
	 * @param content
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static Map<String, Object> jsonStringToMap(String content) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (content != null)
			try {
				map = (Map<String, Object>) mapper.readValue(content, TypeFactory.rawClass(map.getClass()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		return map;
	}
}
