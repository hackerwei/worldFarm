package com.hz.world.api.common.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LiveFilterConfig {
	
	@Bean
    public FilterRegistrationBean accessControlAllowFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new AccessControlAllowFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(1);
        return registration;
    }
	
	@Bean
    public FilterRegistrationBean appFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new AppFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(2);
        return registration;
    }
	
	/*@Bean
    public FilterRegistrationBean interfaceFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new InterfaceFilter());
        registration.addUrlPatterns("/*");
        return registration;
    }*/
	
	@Bean
    public FilterRegistrationBean userFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new UserFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(3);
        return registration;
    }
	
	@Bean
    public FilterRegistrationBean logFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new LoggingFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(4);
        return registration;
    }


}
