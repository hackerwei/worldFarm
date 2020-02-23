package com.hz.world.api.common.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hz.world.api.core.domain.dto.GeneralResultMap;
import com.hz.world.api.core.domain.dto.SysReturnCode;
import com.hz.world.common.config.AuthConfig;

public class InterfaceFilter extends BaseFilter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		long start = System.currentTimeMillis();
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String uri = httpRequest.getRequestURI();

		String appId = httpRequest.getHeader(X_SECURITY).split(":")[0];

		boolean isAllow = false;
		for (String str : AuthConfig.API_SCOPE_MAP.get(appId)) {
			if (uri.contains(str)) {
				isAllow = true;
				break;
			}
		}

		if (!isAllow) {
			GeneralResultMap result = new GeneralResultMap();
			result.setResult(SysReturnCode.FORBIDDEN);
			processJsonError(httpRequest, httpResponse, result);
			return;
		}

		F_LOGGER.debug(String.format("InterfaceFilter %sms 完成接口访问权限的校验。", System.currentTimeMillis() - start));

		chain.doFilter(request, response);
	}

}
