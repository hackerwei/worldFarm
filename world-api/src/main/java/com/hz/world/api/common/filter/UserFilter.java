package com.hz.world.api.common.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hz.world.api.common.cache.ApiCacheUtil;
import com.hz.world.api.core.domain.dto.GeneralResultMap;
import com.hz.world.api.core.domain.dto.SysReturnCode;
import com.hz.world.common.config.AuthConfig;
import com.hz.world.common.util.AccessToken;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户登录过滤器校验
 */
@Slf4j
public class UserFilter extends BaseFilter {

    private ApiCacheUtil apiCacheUtil;
    

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        apiCacheUtil = (ApiCacheUtil) ctx.getBean("apiCacheUtil");
    }

  
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        long start = System.currentTimeMillis();
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        GeneralResultMap result = new GeneralResultMap();
        String uri = httpRequest.getRequestURI(); // uri
        //String method = httpRequest.getMethod();
    

        boolean allowedUrl = AuthConfig.NOT_NEED_SIGNATURE_URL.contains(uri);
        if (allowedUrl) {
            F_LOGGER.debug(String.format("UserFilter白名单,allowedPath:%s,uri:%s,time:%sms", allowedUrl, uri, System.currentTimeMillis() - start));
            chain.doFilter(request, response);
            return;
        }

        String userId = httpRequest.getHeader(X_USER_ID);
        String inputToken = httpRequest.getHeader(X_TOKEN);
        
        
        boolean isNeedCheck = true;// 是否需要登录，不需要登录的将uri放入map中
        String appId = "";
        for (String str : AuthConfig.NO_NEED_LOGIN_URL.get(appId)) {
            if (uri.contains(str)) {
                isNeedCheck = false;
                break;
            }
        }
        //需要校验登录
       
        if (isNeedCheck) {
           
            String loginToken = "";

            if (StringUtils.isNotBlank(userId)) {
                loginToken = apiCacheUtil.getUserLoginToken(Long.parseLong(userId)); // 登录token
            }

            boolean isExit = isExitLogin(userId, loginToken);
            if (isExit) {
                F_LOGGER.debug(String.format("UserFilter用户退出登录或token失效,userId:%s,inputToken:%s,loginToken:%s", userId, inputToken, loginToken));
                result.setResult(SysReturnCode.USER_EXIT_LOGIN);
                processJsonError(httpRequest, httpResponse, result);
                return;
            }

            int ret = AccessToken.decrtyToken(inputToken, userId, loginToken);
            if (ret != 0) {

                if (ret == AccessToken.REDUPLICATE_LOGIN) { // 用户在另外设备登录
                    F_LOGGER.debug(String.format("UserFilter用户在另外设备登录,userId:%s,inputToken:%s,loginToken:%s", userId, inputToken, loginToken));
                }
                result.setResult(SysReturnCode.LOGIN_TOKEN_ERROR);
                processJsonError(httpRequest, httpResponse, result);
                return;
            }
        }
      
        F_LOGGER.info(String.format("UserFilter %sms 完成接口访问权限的校验", System.currentTimeMillis() - start));

        chain.doFilter(request, response);
    }


    /**
     * 判断用户是否退出登录
     * <pre>
     * @param userId
     * @param loginToken
     * @return
     * Modifications:
     * Modifier linyanchun; 2018年5月2日; Create new Method isExitLogin
     * </pre>
     */
    private boolean isExitLogin(String userId, String loginToken) {
        boolean isExit = false;
        try {
            if (StringUtils.isEmpty(loginToken)) { // 已退出登录
                isExit = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isExit;
    }

}
