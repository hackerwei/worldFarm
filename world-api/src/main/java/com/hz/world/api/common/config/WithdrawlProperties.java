package com.hz.world.api.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**  
* <p>Title: OSSProperties</p>  
* <p>Description: oss配置信息</p>  
* @author linyanchun  
* @date 2018年8月19日  
*/  
@Data
@Component
@ConfigurationProperties(prefix = "withdrawal")
public class WithdrawlProperties {

	private String url;
	
	private String key;
	
	
}
