package com.hz.world.api.common.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.hz.world.api.common.support.AppSignature;
import com.hz.world.api.core.domain.dto.GeneralResultMap;
import com.hz.world.api.core.domain.dto.SysReturnCode;
import com.hz.world.common.config.AuthConfig;

/**
 * appFilter ,校验与appId相关的检测
 * <p>
 * <pre>
 * Class Name: AppFilter.java
 * &#64;author administrator
 * Modifications:
 * Modifier Administrator;2017-07-17; Create new Class AppFilter.java.
 * </pre>
 */
public class AppFilter extends BaseFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        long start = System.currentTimeMillis();
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        GeneralResultMap result = new GeneralResultMap();
        String uri = httpRequest.getRequestURI(); // uri
        if (httpRequest.getMethod().toUpperCase().equals("OPTIONS")) {
            httpResponse.setContentType(APPLICATION_JSON_CHARSET_UTF_8);
            httpResponse.setStatus(202);

            PrintWriter out = httpResponse.getWriter();
            out.print("Accepted");
            out.flush();
            IOUtils.closeQuietly(out);
            IOUtils.closeQuietly(httpRequest.getInputStream());
            return;
        }

        boolean allowedUrl = AuthConfig.NOT_NEED_SIGNATURE_URL.contains(uri);
        if (allowedUrl) {
            F_LOGGER.debug(String.format("AppFilter白名单,allowedPath:%s,uri:%s,time:%sms", allowedUrl, uri, System.currentTimeMillis() - start));
            chain.doFilter(request, response);
            return;
        }

        boolean isNeedCheck = false;// 是否需要签名，不需要的签名的将uri放入map中
        //需要校验授权
        if (isNeedCheck) {
            // API Signature
            String signature = httpRequest.getHeader(X_SECURITY);
            String errorMsg = AppSignature.validate(signature);
            if (null != errorMsg) {
                F_LOGGER.error("AppFilter,Security:{}校验失败的原因:{},uri:{}", signature, errorMsg, uri);
                processError(httpRequest, httpResponse, errorMsg, HttpServletResponse.SC_OK);

                return;
            }

            // UUID 校验
            String uuid = httpRequest.getHeader(X_UUID);
            if (StringUtils.isBlank(uuid)) {
                result.setResult(SysReturnCode.INVALID_UUID_CODE);
                processJsonError(httpRequest, httpResponse, result);
                return;
            }
        }

        F_LOGGER.debug(String.format("AppFilter %sms 完成appSecurity的校验。", System.currentTimeMillis() - start));
        chain.doFilter(request, response);
    }

}
