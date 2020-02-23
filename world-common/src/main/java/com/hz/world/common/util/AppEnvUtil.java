package com.hz.world.common.util;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.hz.world.common.constant.LiveConstants;
import com.hz.world.common.enums.AppEnvEnum;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class AppEnvUtil implements EnvironmentAware {

    @Override
    public void setEnvironment(Environment environment) {
        LiveConstants.CURRENT_ENV = environment.getProperty("spring.profiles.active");
        log.info("应用当前环境为:" + LiveConstants.CURRENT_ENV);
    }

    /**
     * 判断当前是否是预发或生产环境
     * <pre>
     * @return
     * Modifications:
     * Modifier liuxiao; 2018年1月29日; Create new Method isProduct
     * </pre>
     */
    public static boolean isPrdEnv() {
        if (AppEnvEnum.PRE.getCode().equals(LiveConstants.CURRENT_ENV.toLowerCase()) ||
                AppEnvEnum.PRD.getCode().equals(LiveConstants.CURRENT_ENV.toLowerCase())) {
            return true;
        }
        return false;
    }
}
