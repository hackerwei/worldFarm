package com.hz.world.common.util;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

/**
 * 
 * 对象工具类
 * 
 * <pre>
 * Class Name: BeanUtil.java
 * @author lanchen
 * Modifications:
 * Modifier lanchen; 2017年8月23日; Create new Class BeanUtil.java.
 * </pre>
 */
public class BeanUtil {

	/**
	 * 通用对象复制
	 * @param source
	 * @param targetClazz
	 * @param <T>
	 * @param <M>
	 * @return
	 */
	public static <T extends Object,M extends Object> T copyProperties(M source,Class<T> targetClazz){
		T target = null;
		if(source!=null){
			target = newObject(targetClazz);
			if(target!=null){
				BeanUtils.copyProperties(source, target);
			}
		}
		return target;
	}
	
	/**
	 * 
	 * 通用对象List复制,支持不同java类相同属性间属性复制
	 * 
	 * <pre>
	 * @param sourceList
	 * @param targetClazz
	 * @return
	 * Modifications:
	 * Modifier lanchen; 2017年8月23日; Create new Method po2dto
	 * </pre>
	 */
	public static <T extends Object,M extends Object> List<T> copyProperties(List<M> sourceList,Class<T> targetClazz){
		List<T> targetList = null;
		if(CollectionUtils.isNotEmpty(sourceList)){
			targetList = new ArrayList<T>();
			for(M source : sourceList){
				T target = newObject(targetClazz);
				if(target!=null){
					BeanUtils.copyProperties(source, target);
					targetList.add(target);
				}
			}
		}
		return targetList;
	}
	
	
	/**
	 * 类反射,通过Class基于无参构造函数创建类的实例对象
	 * 
	 * <pre>
	 * @param clazz
	 * @return
	 * Modifications:
	 * Modifier lanchen; 2017年8月23日; Create new Method newObject
	 * </pre>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> T newObject(Class<T> clazz){
		T t = null;
		try{
			Class targetClass = Class.forName(clazz.getName());
			Constructor[] constructors = targetClass.getDeclaredConstructors();
			AccessibleObject.setAccessible(constructors, true);
			for (Constructor con : constructors) {
				if(con.getParameterCount() == 0 && con.isAccessible()) {
					t = (T)con.newInstance();
				}
			}
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(InvocationTargetException e){
			e.printStackTrace();
		}catch(IllegalAccessException e){
			e.printStackTrace();
		}catch(InstantiationException e){
			e.printStackTrace();
		}
	   return t;
	}

}
