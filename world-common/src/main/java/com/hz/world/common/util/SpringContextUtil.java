package com.hz.world.common.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

/**
 * @outhor lujian
 * @create 2018-05-25 10:40
 */
@Service
public class SpringContextUtil implements ApplicationListener<ContextRefreshedEvent> {
    private static ApplicationContext applicationContext = null;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (applicationContext == null) {
            applicationContext = event.getApplicationContext();
        }
    }

    public static Object getBean(String serviceName) {
        return applicationContext.getBean(serviceName);
    }
    public static <T> T getBean(Class<T> var1) {
        return applicationContext.getBean(var1);
    }
}