package com.hz.world.api.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "live.signin")
public class SigninProperties {

	private String md5salt;
}
