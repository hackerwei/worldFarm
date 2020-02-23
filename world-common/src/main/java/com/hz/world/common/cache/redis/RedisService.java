package com.hz.world.common.cache.redis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.annotation.PostConstruct;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisDataException;
import redis.clients.jedis.exceptions.JedisException;

@Setter
@Getter
@Slf4j
@Service
public class RedisService {
	
    @Value("${redis.hostport}")
    private String hostport;
    
    @Value("${redis.password}")
    private String password;
    
    @Value("${redis.maxTotal}")
    private Integer maxTotal;
    
    @Value("${redis.minIdle}")
    private Integer minIdle;
    
    @Value("${redis.maxIdle}")
    private Integer maxIdle;
    
    @Value("${redis.testOnBorrow}")
    private Boolean testOnBorrow = false;
    
    @Value("${redis.testOnReturn}")
    private Boolean testOnReturn = false;
    
    @Value("${redis.testWhileIdle}")
    private Boolean testWhileIdle = true;
    
    @Value("${redis.maxWaitMillis}")
    private Integer maxWaitMillis =3000;
    
    //@Value("${redis.timeBetweenEvictionRunsMillis}")
    private Long timeBetweenEvictionRunsMillis = JedisPoolConfig.DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS;
    
    //@Value("${redis.minEvictableIdleTimeMillis}")
    private Long minEvictableIdleTimeMillis = JedisPoolConfig.DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS;
    
    @Value("${redis.timeout}")
    private Integer timeout = 3000;
	
	private JedisPoolConfig jedisPoolConfig;
		
	private JedisPool jedisPool;
	
    @PostConstruct
    private void init() {
    	
    	if(StringUtils.isEmpty(hostport)){
    		throw new IllegalArgumentException("Paramer 'hostport' is required");
    	}
    	
    	jedisPoolConfig = new JedisPoolConfig();
    	jedisPoolConfig.setMaxIdle(maxIdle);
    	jedisPoolConfig.setMaxTotal(maxTotal);
    	jedisPoolConfig.setMinIdle(minIdle);
    	jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
    	jedisPoolConfig.setTestOnBorrow(testOnBorrow);
    	jedisPoolConfig.setTestOnReturn(testOnReturn);
    	jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
    	jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
    	
    	if(jedisPoolConfig!=null && StringUtils.isNotEmpty(hostport)){
    		String[] nodeArray = hostport.split(",");
    		HostAndPort oneNode = null;
			if(nodeArray!=null && nodeArray.length>0){
				for(String node : nodeArray){
					oneNode = HostAndPort.parseString(node);
					if(oneNode !=null){
						break;
					}
				}
				if(StringUtils.isNotEmpty(getPassword())){
					jedisPool =  new JedisPool(jedisPoolConfig,oneNode.getHost(),oneNode.getPort(), getTimeout(),getPassword());
				}else{
					jedisPool =  new JedisPool(jedisPoolConfig,oneNode.getHost(),oneNode.getPort(), getTimeout());
				}
			}
    	}
    }
    
	
    /**
     * 获取Jedis连接
     * 
     * @return
     */
    public Jedis getJedis() {
        Jedis jedis = null;
        
        //加载
        if(jedisPool == null || jedisPoolConfig == null){
    		synchronized(this){
	    		init();
    		}
        }
        
        if (jedisPool != null && !jedisPool.isClosed()) {
            try {
                jedis = jedisPool.getResource();
            } catch (Exception e) {
                log.error("can not get jedis from JedisPool.", e);
            }
        } else {
        	log.error("JedisPool is closed!");
            throw new RuntimeException("JedisPool is closed!");
        }
        return jedis;
    }
    
    
    
    //******************************************************************//
    
    
	/**
	 *
	 *封装 expire
	 *
	 * <pre>
	 * @param cacheKey
	 * @param exp
	 * Modifications:
	 * Modifier lanchen; 2017年8月23日; Create new Method expire
	 * </pre>
	 */
	public void expire(String cacheKey,Long exp){
		if(exp == null) exp =-1l;
		if(exp>0){
			expire(cacheKey, exp.intValue());
		}
	}
	
	/**
	 * 
	 * 封装set,带过期时间
	 * 
	 * <pre>
	 * @param cacheKey
	 * @param ttl
	 * @param value
	 * Modifications:
	 * Modifier lanchen; 2017年8月23日; Create new Method set
	 * </pre>
	 */
	public void set(String cacheKey, Long ttl,String value){
		if(ttl == null) ttl =-1l;
		if(ttl>0){
			setex(cacheKey, ttl.intValue(), value);
		}else{
			set(cacheKey, value);
		}
	}
	
	/**
	 * 
	 * 通用对象存储,把Json序列化的对象字符串存储在redis中
	 * 
	 * ttl>0 设置过期时间
	 * 
	 * <pre>
	 * @param cacheKey
	 * @param obj
	 * @param ttl
	 * Modifications:
	 * Modifier lanchen; 2017年8月23日; Create new Method putObject
	 * </pre>
	 */
	public void putObject(String cacheKey,Object obj,Long ttl){
		if(StringUtils.isBlank(cacheKey) || obj == null){ return;}
		if(ttl == null) ttl = -1l;
		if(ttl>0){
			setex(cacheKey, ttl.intValue(), new Gson().toJson(obj));
		}else{
			set(cacheKey,new GsonBuilder().disableHtmlEscaping().create().toJson(obj));
		}
	}
	
	/**
	 * 
	 * 通用提取存储对象
	 * 
	 * <pre>
	 * @param cacheKey
	 * @param clazz
	 * @return
	 * Modifications:
	 * Modifier lanchen; 2017年8月23日; Create new Method getObject
	 * </pre>
	 */
	public <T> T getObject(String cacheKey,Class<T> clazz){
		if(StringUtils.isBlank(cacheKey) || clazz == null){ return null;}
		String cacheValue = get(cacheKey);
		if(StringUtils.isNotEmpty(cacheValue)){
			return new GsonBuilder().disableHtmlEscaping().create().fromJson(cacheValue, clazz);
		}
		return null;
	}
	
	/**
	 * 
	 * 把整个List<T>存入Redis List结构
	 * 
	 * 第一次创建key可以设置过期时间
	 * 
	 * <pre>
	 * @param cacheKey
	 * @param objList
	 * @param ttl
	 * Modifications:
	 * Modifier lanchen; 2017年8月23日; Create new Method putObjectList
	 * </pre>
	 */
	public <T> void putObjectList(String cacheKey,List<T> objList,Long ttl){
		if(StringUtils.isBlank(cacheKey) || CollectionUtils.isEmpty(objList)){return;}
		if(ttl == null) ttl = -1l;
		boolean hasExist = false;
		
		if(exists(cacheKey)){
			hasExist = true;
		}
		if(ttl>0){
			for(T obj:objList){
				rpush(cacheKey, new GsonBuilder().disableHtmlEscaping().create().toJson(obj));
			}
			// 第一次添加的时候设置过期时间
			if(!hasExist){
				expire(null, ttl.intValue());
			}
		}else{
			for(T obj:objList){
				rpush(cacheKey, new GsonBuilder().disableHtmlEscaping().create().toJson(obj));
			}
		}
	}
	
	
	/**
	 * 
	 * 获取对象List集合
	 * 
	 * <pre>
	 * @param cacheKey
	 * @param clazz
	 * @return
	 * Modifications:
	 * Modifier lanchen; 2017年8月23日; Create new Method getObjectList
	 * </pre>
	 */
	public <T> List<T> getObjectList(String cacheKey,Class<T> clazz){
		if(StringUtils.isBlank(cacheKey) || clazz == null){ return null;}
		
		List<String> objectSet = lgetAll(cacheKey);
		
		List<T> objectList = null;
		if(objectSet!=null && objectSet.size()>0){
			objectList = new ArrayList<T>();
			for(String objectStr : objectSet){
				objectList.add(new GsonBuilder().disableHtmlEscaping().create().fromJson(objectStr, clazz));
			}
		}
		return objectList;
	}
	
	/**
	 * 
	 * 把整个List<T>存入Redis SET结构,去重,无序
	 * 
	 * 第一次创建key可以设置过期时间
	 * 
	 * <pre>
	 * @param cacheKey
	 * @param objList
	 * @param ttl
	 * Modifications:
	 * Modifier lanchen; 2017年8月23日; Create new Method putObjectList
	 * </pre>
	 */
	public <T> void putObjectSet(String cacheKey,List<T> objList,Long ttl){
		if(StringUtils.isBlank(cacheKey) || CollectionUtils.isEmpty(objList)){return;}
		if(ttl == null) ttl = -1l;
		boolean hasExist = false;
		
		if(exists(cacheKey)){
			hasExist = true;
		}
		if(ttl>0){
			for(T obj:objList){
				sadd(cacheKey, new GsonBuilder().disableHtmlEscaping().create().toJson(obj));
			}
			// 第一次添加的时候设置过期时间
			if(!hasExist){
				expire(null, ttl.intValue());
			}
		}else{
			for(T obj:objList){
				sadd(cacheKey, new GsonBuilder().disableHtmlEscaping().create().toJson(obj));
			}
		}
	}
	
	
	/**
	 * 
	 * 获取对象Set集合
	 * 
	 * <pre>
	 * @param cacheKey
	 * @param clazz
	 * @return
	 * Modifications:
	 * Modifier lanchen; 2017年8月23日; Create new Method getObjectSet
	 * </pre>
	 */
	public <T> Set<T> getObjectSet(String cacheKey,Class<T> clazz){
		if(StringUtils.isBlank(cacheKey) || clazz == null){ return null;}
		
		Set<String> objectSet = smembers(cacheKey);
		
		Set<T> objectList = null;
		if(objectSet!=null && objectSet.size()>0){
			objectList = new HashSet<T>();
			for(String objectStr : objectSet){
				objectList.add(new GsonBuilder().disableHtmlEscaping().create().fromJson(objectStr, clazz));
			}
		}
		return objectList;
	}
	
	
	/**
	 * 
	 * 前提: 把实体对象的ID值放入ZSET进行有序存储
	 * 1.通过 sinceId 获得sinceId所在ZSET的索引值
	 * 2.基于获取sinceId的索引值,获取之后rows个实体对象ID,Set<String>返回
	 * 
	 * <pre>
	 * @param zSetCacheKey
	 * @param sinceId
	 * @param rows
	 * @return
	 * Modifications:
	 * Modifier lanchen; 2017年8月23日; Create new Method getEntityIdSetBySinceId
	 * </pre>
	 */
	public Set<String> getEntityIdSetBySinceId(String cacheKey, String sinceId, Integer rows){
		if(StringUtils.isBlank(cacheKey)) return null;
		if(rows==null) rows=20;
		long beginIndex=0;
		long endIndex=0;
		Set<String> entityIdSet = null;
		if(StringUtils.isBlank(sinceId)){
			beginIndex=0;
			endIndex = beginIndex+rows.intValue()-1;
		}else{
			Long curIndex = zrank(cacheKey, sinceId);
			if(curIndex!=null){
				beginIndex = curIndex.longValue()+1;
				endIndex = beginIndex+rows.intValue()-1;				
			}	
		}
		entityIdSet = zrange(cacheKey, beginIndex, endIndex);		
		return entityIdSet==null?new HashSet<String>():entityIdSet;
	}
	
	
	/**
	 * 前提: 把实体对象的ID值放入一个ZSET,实体对象的Json字符存储在另一个ZSET,约定相同实体对象和对象ID它们的Score值是一样的，保证存储的顺序一致
	 * 1.通过 sinceId 获得sinceId所在ZSET的索引值
	 * 2.基于获取sinceId的索引值,获取之后rows个实体对象,转化成Set<T>返回
	 * <pre>
	 * @param idCacheKey
	 * @param entityCacheKey
	 * @param sinceId
	 * @param rows
	 * @param clazz
	 * @return
	 * Modifications:
	 * Modifier lanchen; 2017年8月28日; Create new Method getEntitySetBySinceId
	 * </pre>
	 */
	public <T> Set<T> getEntitySetBySinceId(String idCacheKey,String entityCacheKey, String sinceId, Integer rows, Class<T> clazz){
		if(StringUtils.isBlank(idCacheKey) || StringUtils.isBlank(entityCacheKey)) return null;
		if(rows==null) rows=20;
		long beginIndex=0;
		long endIndex=0;
		Set<String> entityStrSet = null;
		Set<T> entitySet = null;
		if(StringUtils.isBlank(sinceId)){
			beginIndex=0;
			endIndex = beginIndex+rows.intValue()-1;
		}else{
			Long curIndex = zrank(idCacheKey, sinceId);
			if(curIndex!=null){
				beginIndex = curIndex.longValue()+1;
				endIndex = beginIndex+rows.intValue()-1;				
			}	
		}
		
		long idSize = zcard(idCacheKey);
		long entitySize = zcard(entityCacheKey);
		
		if(entitySize >= idSize){
			entityStrSet = zrange(entityCacheKey, beginIndex, endIndex);
		}
		
		
		if(entityStrSet!=null && entityStrSet.size()>0){
			entitySet = new HashSet<T>();
			for(String entityStr : entityStrSet){
				entitySet.add(new GsonBuilder().disableHtmlEscaping().create().fromJson(entityStr, clazz));
			}
		}
		return entitySet==null?new HashSet<T>():entitySet;
	}
	
	
	/**
	 * 
	 * 返回Score在 minScore -> Double.MAX_VALUE 范围的ZSET集合
	 * 
	 * <pre>
	 * @param entityCacheKey
	 * @param minScore
	 * @param rows
	 * @param clazz
	 * @return
	 * Modifications:
	 * Modifier lanchen; 2017年9月12日; Create new Method getEntitySetByScore
	 * </pre>
	 */
	public <T> Set<T> getEntitySetByMinScore(String entityCacheKey, Double minScore, Integer rows, Class<T> clazz){
		if(StringUtils.isBlank(entityCacheKey)) return null;
		if(rows==null) rows=20;
		long beginIndex=0;
		long endIndex=0;
		Set<String> entityStrSet = null;
		Set<T> entitySet = null;
		if(minScore == null){
			beginIndex=0;
			endIndex = beginIndex+rows.intValue()-1;
			
			entityStrSet = this.zrange(entityCacheKey, beginIndex, endIndex);
		}else{
			entityStrSet = this.zrangeByScore(entityCacheKey,minScore,Double.MAX_VALUE,0,rows);
		}
		
		if(entityStrSet!=null && entityStrSet.size()>0){
			entitySet = new HashSet<T>();
			for(String entityStr : entityStrSet){
				entitySet.add(new GsonBuilder().disableHtmlEscaping().create().fromJson(entityStr, clazz));
			}
		}
		return entitySet==null?new HashSet<T>():entitySet;
	}
	

	/**
	 * 
	 * 基于对象实例的ID和排序分值,存入ZSET集合,常用于对象集合排序
	 * 
	 * <pre>
	 * @param cacheKey
	 * @param entityIdDoubleMap
	 * Modifications:
	 * Modifier lanchen; 2017年8月23日; Create new Method zadds
	 * </pre>
	 */
	public void zadds(String cacheKey,Map<String,Double> entityIdDoubleMap){
		if(StringUtils.isBlank(cacheKey) || entityIdDoubleMap == null) return;
		for(String entityId : entityIdDoubleMap.keySet()){
			zadd(cacheKey, entityIdDoubleMap.get(entityId), entityId);
		}
	}
	
	/**
	 * 
	 * 获取ZSET集合所有成员
	 * 
	 * <pre>
	 * @param cacheKey
	 * @return
	 * Modifications:
	 * Modifier lanchen; 2017年8月23日; Create new Method zmemebers
	 * </pre>
	 */
	public Set<String> zmemebers(String cacheKey){
		return zrange(cacheKey, 0l, -1l);
	}
	

	/**
	 * 
	 * 通用Redis原子锁,带过期时间单位是秒
	 * 
	 * <pre>
	 * @param cacheKey
	 * @param ttl
	 * @return
	 * Modifications:
	 * Modifier lanchen; 2017年8月23日; Create new Method getLock
	 * </pre>
	 */
	public boolean getLock(String cacheKey, Long ttl) {
		boolean rltBoolean = false;
		String result = set(cacheKey, "1", "nx", "ex", ttl);
		rltBoolean = "ok".equalsIgnoreCase(result);
		return rltBoolean;
	}
	
	/**
	 * 
	 * 通用Redis原子锁,不带过期时间
	 * 
	 * <pre>
	 * @param cacheKey
	 * @return
	 * Modifications:
	 * Modifier lanchen; 2017年9月15日; Create new Method getLock
	 * </pre>
	 */
	public boolean getLock(String cacheKey) {
		boolean rltBoolean = false;
		String result = set(cacheKey, "1", "nx");
		rltBoolean = "ok".equalsIgnoreCase(result);
		return rltBoolean;
	}
	
	
	/**
	 * 
	 * 原子自增+1,key第一次创建时候会设置过期期时间
	 * 
	 * <pre>
	 * @param cacheKey
	 * @param ttl
	 * Modifications:
	 * Modifier lanchen; 2017年8月23日; Create new Method incr
	 * </pre>
	 */
	public void incr(String cacheKey,int ttl){
		boolean hasExist = false;
		if(exists(cacheKey)){
			hasExist = true;
			incr(cacheKey);
		}
		if(!hasExist){
			setex(cacheKey,ttl,"1");
		}
	}
	
	/**
	 * 
	 * 原子自增+incrCount,key第一次创建时候会设置过期期时间
	 * 
	 * <pre>
	 * @param cacheKey
	 * @param incrCount
	 * @param ttl
	 * Modifications:
	 * Modifier lanchen; 2017年8月23日; Create new Method incr
	 * </pre>
	 */
	public void incr(String cacheKey,Long incrCount,int ttl){
		if(incrCount == null) incrCount = 0l;
		boolean hasExist = false;
		if(exists(cacheKey)){
			hasExist = true;
			incrBy(cacheKey, incrCount);
		}
		if(!hasExist){
			setex(cacheKey,ttl,incrCount.toString());
		}
	}
	
	/**
	 * 
	 * 获取自增或自减当前值,key不存在返回0
	 * 
	 * <pre>
	 * @param cacheCountKey
	 * @return
	 * Modifications:
	 * Modifier lanchen; 2017年8月23日; Create new Method getCount
	 * </pre>
	 */
	public int getCount(String cacheCountKey){
		int count = 0;
		String temp = get(cacheCountKey);
		count = Integer.parseInt(StringUtils.defaultIfEmpty(temp, "0"));
		return count;
	}
    

	//*******************Redis String Operate *********************//
	
	/**
	 * 获取Key
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public String get(String key){
		String rlt ="";
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.get(key);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}

	/**
	 * 存储key,value
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public String set(String key, String value){
		String rlt ="";
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.set(key,value);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 * 
	 * set 全部可填参数
	 * 
	 * @param key
	 * @param value
	 * @param nxxx
	 * @param expx
	 * @param time
	 * @return
	 */
	public String set(String key, String value, String nxxx, String expx, long time){
		String rlt ="";
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.set(key, value, nxxx, expx, time);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 * 
	 * set 不带过期时间
	 * 
	 * @param key
	 * @param value
	 * @param nxxx
	 * @param expx
	 * @param time
	 * @return
	 */
	public String set(String key, String value, String nxxx){
		String rlt ="";
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.set(key, value, nxxx);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 * 获取并更改数据
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public String getSet(String key, String value){
		String rlt ="";
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.getSet(key,value);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 * 若key不存在,则存储
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public Long setnx(String key, String value){
		Long rlt = 0l;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.setnx(key,value);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}

	/**
	 * 追加数据
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public Long append(String key, String value){
		Long rlt = 0l;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.append(key,value);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 * 设置key的有效期,并存储数据
	 * 
	 * @param key
	 * @param exp
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public String setex(String key, Integer exp, String value){
		String rlt ="";
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.setex(key,exp,value);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 * 给Key设置过期时间
	 * 
	 * @param key
	 * @param exp
	 * @return
	 * @throws Exception
	 */
	public Long expire(String key,Integer exp){
		Long rlt = 0l;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.expire(key, exp);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 * 截取value的值
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public String getrange(String key, Integer start, Integer end){
		String rlt ="";
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.getrange(key,start,end);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 * 批量保存
	 * 
	 * @param keys
	 * @param values
	 * @return
	 * @throws Exception
	 */
	public String mset(final List<String> keys, final List<String> values){
		String rlt ="";
		
		if(CollectionUtils.isNotEmpty(keys) && CollectionUtils.isNotEmpty(values) && keys.size() == values.size()){
			Jedis jedis = getJedis();
			for(int i=0;i<keys.size();i++){
				boolean broken = false;
				try{
					if(jedis!=null){
						rlt = jedis.set(keys.get(i),values.get(i));
					}
				}catch(JedisException e){
					broken = handleJedisException(e);
					//throw e;
				}finally{
					this.closeResource(keys.get(i), jedis, broken);
				}
			}
		}
		return rlt;
	}
	
	/**
	 * 自增
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Long incr(String key){
		Long rlt = 0l;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.incr(key);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	public Long incrBy(String key, Long count) {
		Long rlt = 0l;
		if(count == null) count = 0l;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.incrBy(key,count);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}

	public Long decr(String key) {
		Long rlt = 0l;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.decr(key);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	public Long decrBy(String key, Long count) {
		Long rlt = 0l;
		if(count == null) count = 0l;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.decrBy(key,count);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	//*******************Redis List Operate *********************//
	/**
	 * 左添加
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public Long lpush(String key, String value){
		Long rlt = 0l;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.lpush(key,value);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	
	/**
	 *  右添加
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public Long rpush(String key, String value){
		Long rlt = 0l;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.rpush(key,value);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 * 获取列表长度
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Long llen(String key){
		Long rlt = 0l;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.llen(key);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 * 排序
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public List<String> sort(String key){
		List<String> rlt = null;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.sort(key);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		// 处理List中存在null的情况
		if(rlt != null && rlt.size()>0){
			for(int i=0;i<rlt.size();i++){
				if(rlt.get(i) == null){
					rlt.remove(i);
				}
			}
		}
		return (rlt!=null?rlt:new ArrayList<String>());
	}
	
	/**
	 * 获取列表子集
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public List<String> lrange(String key, Integer start, Integer end){
		List<String> rlt = null;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.lrange(key,start,end);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		// 处理List中存在null的情况
		if(rlt != null && rlt.size()>0){
			for(int i=0;i<rlt.size();i++){
				if(rlt.get(i) == null){
					rlt.remove(i);
				}
			}
		}
		return (rlt!=null?rlt:new ArrayList<String>());
	}
	
	/**
	 * 获取列表指定下标的值
	 * 
	 * @param key
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public String lindex(String key, Integer index){
		String rlt ="";
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.lindex(key,index);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 * 删除列表指定下标的值
	 * 
	 * @param key
	 * @param index
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public Long lrem(String key, Integer count,String value){
		Long rlt = 0l;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.lrem(key,count,value);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 * 删除区间以外的数据
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public String ltrim(String key, Integer start, Integer end){
		String rlt ="";
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.ltrim(key,start,end);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 *  弹出列表顶部数据(出栈)
	 *  
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public String lpop(String key){
		String rlt ="";
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.lpop(key);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 * 获取整个列表
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public List<String> lgetAll(String key){
		List<String> rlt = null;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.lrange(key,0,-1);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		// 处理List中存在null的情况
		if(rlt != null && rlt.size()>0){
			for(int i=0;i<rlt.size();i++){
				if(rlt.get(i) == null){
					rlt.remove(i);
				}
			}
		}
		return (rlt!=null?rlt:new ArrayList<String>());
	}
	
	//*******************Redis Set Operate *********************//
	
	/**
	 * 添加
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public Long sadd(String key, String member){
		Long rlt = 0l;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.sadd(key,member);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 * 判断value是否在set中
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public Boolean sismember(String key, String member){
		Boolean rlt = false;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.sismember(key,member);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 * 获取整个set值
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Set<String> smembers(String key){
		Set<String> rlt = null;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.smembers(key);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		// 删除Key为null的情况
		if(rlt!=null && rlt.size()>0){
			if(rlt.contains(null)){
				rlt.remove(null);
			}
		}
		return (rlt!=null?rlt:new HashSet<String>());
	}
	
	/**
	 * 出栈
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public String spop(String key){
		String rlt ="";
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.spop(key);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 * 删除指定元素
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Long srem(String key,String member){
		Long rlt = 0l;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.srem(key,member);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 * set中元素个数
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Long scard(String key){
		Long rlt = 0l;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.scard(key);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 * 返回集合中count个随机元素
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public List<String> srandmember(String key, Integer count){
		List<String> rlt = new ArrayList<String>();
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.srandmember(key, count);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	//*******************Redis ZSet Operate *********************//
	/**
	 * 添加
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public Long zadd(String key, Double score,String member){
		Long rlt = 0l;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.zadd(key,score,member);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 * 元素个数
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Long zcard(String key){
		Long rlt = 0l;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.zcard(key);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 * 获取元素分值
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public Double zscore(String key,String member){
		Double rlt = 0d;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.zscore(key,member);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 * 删除元素
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public Long zrem(String key,String member){
		Long rlt = 0l;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.zrem(key,member);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	/**
	 * 删除元素
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public Long zremRangeByScore(String key,Double from ,Double to){
		Long rlt = 0l;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.zremrangeByScore(key, 0, to);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}

	/**
	 * 集合子集
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public Set<String> zrange(String key,long start,long end){
		Set<String> rlt = null;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.zrange(key,start,end);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		
		// 删除Key为null的情况
		if(rlt!=null && rlt.size()>0){
			if(rlt.contains(null)){
				rlt.remove(null);
			}
		}
		return (rlt!=null?rlt:new HashSet<String>());
	}
	
	public Set<String> zrevrange(String key,long start,long end){
		Set<String> rlt = null;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.zrevrange(key,start,end);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		
		// 删除Key为null的情况
		if(rlt!=null && rlt.size()>0){
			if(rlt.contains(null)){
				rlt.remove(null);
			}
		}
		return (rlt!=null?rlt:new HashSet<String>());
	}
	/**
	 * 
	 * 有序集中指定成员的排名
	 * 
	 * @param key
	 * @param member
	 * @return
	 */
	public Long zrank(String key, String member) {
		Long rlt = 0l;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.zrank(key, member);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	public Long zrevrank(String key, String member) {
		Long rlt = 0l;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.zrevrank(key, member);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 * 获取两个分值间的元素数
	 * 
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 * @throws Exception
	 */
	public Long zcount(String key,Double min,Double max){
		Long rlt = 0l;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.zcount(key,min,max);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 * 获取整个Set
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Set<String> zgetAll(String key){
		Set<String> rlt = null;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.zrange(key,0,-1);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		// 删除Key为null的情况
		if(rlt!=null && rlt.size()>0){
			if(rlt.contains(null)){
				rlt.remove(null);
			}
		}
		return (rlt!=null?rlt:new HashSet<String>());
	}
	
	/**
	 * 
	 * 有序集合中指定成员的分数加上增量 score 
	 * 
	 * @param key
	 * @param mapKey
	 * @param count
	 */
	public Double zincrby(String key, Double score, String member){
		Double rlt = 0d;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.zincrby(key, score, member);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	
	/**
	 * 
	 * 返回有序集中指定分数区间内的成员,带score，分数从低到高排序
	 * 
	 * <pre>
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * Modifications:
	 * Modifier lanchen; 2017年8月29日; Create new Method zrangeWithScores
	 * </pre>
	 */
	public Set<Tuple> zrangeWithScores(String key, long start, long end){
		Set<Tuple> rlt = null;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.zrangeWithScores(key, start, end);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 *  返回有序集合中指定分数区间的成员列表。有序集成员按分数值递增(从小到大)次序排列
	 * 
	 * <pre>
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 * Modifications:
	 * Modifier lanchen; 2017年9月12日; Create new Method zrangeByScore
	 * </pre>
	 */
	public Set<String> zrangeByScore(String key, String min, String max, int offset, int count){
		Set<String> rlt = null;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.zrangeByScore(key, min, max, offset, count);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 * 
	 *  返回有序集合中指定分数区间的成员列表。有序集成员按分数值递增(从小到大)次序排列,默认闭区间[min,max]
	 *  
	 * <pre>
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 * Modifications:
	 * Modifier lanchen; 2017年9月12日; Create new Method zrangeByScore
	 * </pre>
	 */
	public Set<String> zrangeByScore(String key, Double min, Double max, int offset, int count){
		Set<String> rlt = null;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.zrangeByScore(key, min, max, offset, count);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	public Set<String> zrangeByScore(String key, String min, String max){
		Set<String> rlt = null;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.zrangeByScore(key, min, max);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	/**
	 * 
	 *  返回有序集合中指定分数区间的成员列表和Score值。有序集成员按分数值递增(从小到大)次序排列,默认闭区间[min,max]
	 *  
	 *  min = "("+XX 开区间
	 *  max = "("+XX 开区间
	 *  
	 * <pre>
	 * @param key
	 * @param min
	 * @param max
	 * @param offset
	 * @param count
	 * @return
	 * Modifications:
	 * Modifier lanchen; 2017年9月12日; Create new Method zrangeByScoreWithScores
	 * </pre>
	 */
	public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max, int offset, int count){
		Set<Tuple> rlt = null;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.zrangeByScoreWithScores(key, min, max, offset, count);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 * 
	 *  返回有序集合中指定分数区间的成员列表和Score值。有序集成员按分数值递增(从小到大)次序排列,默认闭区间[min,max]
	 * 
	 * <pre>
	 * @param key
	 * @param min
	 * @param max
	 * @param offset
	 * @param count
	 * @return
	 * Modifications:
	 * Modifier lanchen; 2017年9月12日; Create new Method zrangeByScoreWithScores
	 * </pre>
	 */
	public Set<Tuple> zrangeByScoreWithScores(String key, Double min, Double max, int offset, int count){
		Set<Tuple> rlt = null;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.zrangeByScoreWithScores(key, min, max, offset, count);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 * 
	 *  返回有序集合中指定分数区间的成员列表。有序集成员按分数值递增(从大到小)次序排列,默认闭区间[min,max]
	 *  
	 *  min = "("+XX 开区间
	 *  max = "("+XX 开区间
	 * 
	 * <pre>
	 * @param key
	 * @param max
	 * @param min
	 * @return
	 * Modifications:
	 * Modifier lanchen; 2017年9月12日; Create new Method zrevrangeByScore
	 * </pre>
	 */
	public Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count){
		Set<String> rlt = null;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.zrevrangeByScore(key, max, min, offset, count);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 *  返回有序集合中指定分数区间的成员列表。有序集成员按分数值递增(从大到小)次序排列
	 * 
	 * <pre>
	 * @param key
	 * @param max
	 * @param min
	 * @return
	 * Modifications:
	 * Modifier lanchen; 2017年9月12日; Create new Method zrevrangeByScore
	 * </pre>
	 */
	public Set<String> zrevrangeByScore(String key, Double max, Double min, int offset, int count){
		Set<String> rlt = null;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.zrevrangeByScore(key, max, min, offset, count);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 * 
	 *  返回有序集合中指定分数区间的成员列表和Score值。有序集成员按分数值递增(从大到小)次序排列
	 * 
	 * <pre>
	 * @param key
	 * @param max
	 * @param min
	 * @param offset
	 * @param count
	 * @return
	 * Modifications:
	 * Modifier lanchen; 2017年9月12日; Create new Method zrevrangeByScoreWithScores
	 * </pre>
	 */
	public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count){
		Set<Tuple> rlt = null;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 * 
	 *  返回有序集合中指定分数区间的成员列表和Score值。有序集成员按分数值递增(从大到小)次序排列
	 *  
	 * <pre>
	 * @param key
	 * @param max
	 * @param min
	 * @param offset
	 * @param count
	 * @return
	 * Modifications:
	 * Modifier lanchen; 2017年9月12日; Create new Method zrevrangeByScoreWithScores
	 * </pre>
	 */
	public Set<Tuple> zrevrangeByScoreWithScores(String key, Double max, Double min, int offset, int count){
		Set<Tuple> rlt = null;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 * 
	 * 返回有序集中指定分数区间内的成员,带score，分数从高到低排序
	 * 
	 * <pre>
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * Modifications:
	 * Modifier lanchen; 2017年8月29日; Create new Method zrevrangeWithScores
	 * </pre>
	 */
	public Set<Tuple> zrevrangeWithScores(String key, long start, long end){
		Set<Tuple> rlt = null;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.zrevrangeWithScores(key, start, end);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	//*******************Redis Hash Operate *********************//
	
	/**
	 * 往Map中添加键值对
	 * 
	 * @param key
	 * @param pairs
	 * @return
	 * @throws Exception
	 */
	public String hmset(String key, Map<String, String> pairs){
		String rlt ="";
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.hmset(key,pairs);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 * 获取Map中指定key的值集合
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public List<String> hmget(String key, String mapKey){
		List<String> rlt = null;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.hmget(key,mapKey);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		
		// 处理List中存在null的情况
		if(rlt != null && rlt.size()>0){
			for(int i=0;i<rlt.size();i++){
				if(rlt.get(i) == null){
					rlt.remove(i);
				}
			}
		}
		return (rlt!=null?rlt:new ArrayList<String>());
	}
	
	/**
	 * 获取Map中指定key的值集合
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public List<String> hmget(String key, String... mapKey){
		List<String> rlt = null;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.hmget(key,mapKey);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		
		// 处理List中存在null的情况
		if(rlt != null && rlt.size()>0){
			Collection c = new Vector(); 
			c.add(null);
			rlt.removeAll(c);
		}
		return (rlt!=null?rlt:new ArrayList<String>());
	}
	
	public Long hset(String key,  String field, String value){
		Long rlt = 0L;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.hset(key, field, value);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	public String hget(String key,  String field){
		String rlt = "";
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.hget(key, field);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 * 获取删除Map中指定的key数据
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public Long hdel(String key, List<String> keyList){
		Long rlt = 0l;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null && keyList!=null && keyList.size()>0){
				rlt = jedis.hdel(key,keyList.toArray(new String[]{}));
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 * 返回key中存在的键值对的个数
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Long hlen(String key){
		Long rlt = 0l;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.hlen(key);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 * 是否存在key
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Boolean exists(String key){
		Boolean rlt = false;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.exists(key);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 * 返回map对象中的所有key
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Set<String> hkeys(String key){
		Set<String> rlt = null;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.hkeys(key);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		// 删除Key为null的情况
		if(rlt!=null && rlt.size()>0){
			if(rlt.contains(null)){
				rlt.remove(null);
			}
		}
		return (rlt!=null?rlt:new HashSet<String>());
	}
	
	/**
	 *  返回map对象中的所有value
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public List<String> hvals(String key){
		List<String> rlt = null;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.hvals(key);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		
		// 处理List中存在null的情况
		if(rlt != null && rlt.size()>0){
			for(int i=0;i<rlt.size();i++){
				if(rlt.get(i) == null){
					rlt.remove(i);
				}
			}
		}
		return (rlt!=null?rlt:new ArrayList<String>());
	}
	
	/**
	 * 获取整个Hash
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> hgetAll(String key){
		Map<String,String> rlt = null;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.hgetAll(key);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		
		// 删除Key为null的情况
		if(rlt!=null && rlt.size()>0){
			if(rlt.containsKey(null)){
				rlt.remove(null);
			}
		}
		return (rlt!=null?rlt:new HashMap<String,String>());
	}
	
	/**
	 * 
	 * 为哈希表 key中的指定字段的整数值加上增量 count
	 * 
	 * @param key
	 * @param mapKey
	 * @param count
	 */
	public Long hincrBy(String key, String mapKey, Long count){
		Long rlt = 0l;
		if(count == null) count = 0l;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.hincrBy(key,mapKey,count);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	public Double hincrByFloat(String key, String mapKey, Double count){
		Double rlt = 0d;
		if(count == null) count = 0d;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.hincrByFloat(key,mapKey,count);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	//*******************Redis other Operate *********************//
	
	public Long ttl(String key){
		Long rlt = 0l;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
				rlt = jedis.ttl(key);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 * 删除
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Long del(String key){
		Long rlt = 0l;
		boolean broken = false;
		Jedis jedis = getJedis();
		try{
			if(jedis!=null){
					rlt = jedis.del(key);
			}
		}catch(JedisException e){
			broken = handleJedisException(e);
			//throw e;
		}finally{
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	
	/**
	 * 批量删除
	 * 
	 * @param keys
	 * @throws Exception
	 */
	public void del(final List<String> keys){
		if(CollectionUtils.isNotEmpty(keys)){
			Jedis jedis = getJedis();
			for(String rdKey:keys){
				boolean broken = false;
				try{
					if(jedis!=null){
						if(jedis.exists(rdKey)){
							jedis.del(rdKey);
						}
					}
				}catch(JedisException e){
					broken = handleJedisException(e);
					//throw e;
				}finally{
					this.closeResource(rdKey, jedis, broken);
				}
			}
		}
	}
	/**
	 * 重命名
	 * 
	 */
	public boolean rename(String oldkey, String newkey) {
		boolean rlt = false;
		String result;
		boolean broken = false;
		Jedis jedis = getJedis();
		try {
			if (jedis != null) {
				result = jedis.rename(oldkey, newkey);
				rlt = result.equals("OK")?true:false;
			}
		} catch (JedisException e) {
			broken = handleJedisException(e);
		} finally {
			this.closeResource(oldkey, jedis, broken);
			this.closeResource(newkey, jedis, broken);
		}
		return rlt;
	}
	/**
	 * 差集
	 * 
	 */
	public Long sdiffstore(final String dstkey, final String... keys) {
		Long rlt = 0l;
		boolean broken = false;
		Jedis jedis = getJedis();
		try {
			if (jedis != null) {
				rlt = jedis.sdiffstore(dstkey, keys);
			}
		} catch (JedisException e) {
			broken = handleJedisException(e);
		} finally {
			this.closeResource(dstkey, jedis, broken);
		}
		return rlt;
	}
	public boolean hexists(String key, String field) {
		boolean rlt = false;
		boolean broken = false;
		Jedis jedis = getJedis();
		try {
			if (jedis != null) {
				rlt = jedis.hexists(key, field);
			}
		} catch (JedisException e) {
			broken = handleJedisException(e);
		} finally {
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}
	public Long hsetnx(String key, String field, String value) {
		Long rlt = 0L;
		boolean broken = false;
		Jedis jedis = getJedis();
		try {
			if (jedis != null) {
				rlt = jedis.hsetnx(key, field, value);
			}
		} catch (JedisException e) {
			broken = handleJedisException(e);
		} finally {
			this.closeResource(key, jedis, broken);
		}
		return rlt;
	}

	/**
	 * Handle jedisException, write log and return whether the connection is broken.
	 */
	protected boolean handleJedisException(JedisException jedisException) {
	    if (jedisException instanceof JedisConnectionException) {
	        log.error("Redis connection lost.", jedisException);
	    } else if (jedisException instanceof JedisDataException) {
	        if ((jedisException.getMessage() != null) && (jedisException.getMessage().indexOf("READONLY") != -1)) {
	            log.error("Redis connection are read-only slave.", jedisException);
	        } else {
	            // dataException, isBroken=false
	            return false;
	        }
	    } else {
	        log.error("Jedis exception happen.", jedisException);
	    }
	    return true;
	}
	
	/**
	 * Return jedis connection to the pool, call different return methods depends on the conectionBroken status.
	 */
	protected void closeResource(String key,Jedis jedis, boolean connectionBroken) {
	    try {
	    	jedis.close();
	    } catch (Exception e) {
	        log.error("return back jedis failed, will fore close the jedis.", e);
	    }
	}
	public Pipeline  getPipline() {
		Jedis jedis = getJedis();
		return  jedis.pipelined();
	}
}