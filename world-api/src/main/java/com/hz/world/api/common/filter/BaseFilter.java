package com.hz.world.api.common.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hz.world.api.core.domain.dto.GeneralResultMap;
import com.hz.world.api.core.domain.dto.SysReturnCode;

import net.sf.json.JSONObject;

/**
 * 过滤器基础类
 * 
 */
public class BaseFilter implements Filter {
	
	protected static final Logger F_LOGGER = LoggerFactory.getLogger("filter");
	protected static final String X_SECURITY = "X-Security";
	protected static final String X_TOKEN = "token";
	protected static final String X_USER_ID = "uid";
	protected static final String X_UUID = "X-UUID";
	protected static final String RESP_ERROR = "{\"code\": %s, \"message\": \"%s\"}";
	protected static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json;charset=UTF-8";
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		chain.doFilter(request, response);
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@SuppressWarnings("rawtypes")
	protected void processError(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String errMsg, int statusCode)
			throws IOException {
		String respMsg = String.format(RESP_ERROR, SysReturnCode.valueOf(errMsg).getCode(), SysReturnCode.valueOf(errMsg).getMsg4Cn());
		Enumeration headerNames = httpRequest.getHeaderNames();
		StringBuilder logInfo = new StringBuilder();
		logInfo.append("httpCode=").append(statusCode);
		logInfo.append('&').append("respBody=").append(respMsg);
		logInfo.append('&').append("url=").append(httpRequest.getRequestURL());
		logInfo.append('&').append("httpMethod=").append(httpRequest.getMethod());
		
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			Enumeration headers = httpRequest.getHeaders(key);
			logInfo.append('&').append(key).append("=");
			while (headers.hasMoreElements()) {
				logInfo.append(headers.nextElement()).append(' ');
			}
			logInfo.setLength(logInfo.length() - 1);
		}
		F_LOGGER.error(logInfo.toString());

		httpResponse.setContentType(APPLICATION_JSON_CHARSET_UTF_8);
		httpResponse.setStatus(statusCode);

		//Writer
		PrintWriter out = httpResponse.getWriter();
		out.print(respMsg);
		out.flush();
		IOUtils.closeQuietly(out);
		IOUtils.closeQuietly(httpRequest.getInputStream());
	}
	@SuppressWarnings("rawtypes")
	protected void processJsonError(HttpServletRequest httpRequest, HttpServletResponse httpResponse,GeneralResultMap result) {
		JSONObject responseJSONObject = JSONObject.fromObject(result);  
		httpResponse.setCharacterEncoding("UTF-8");  
		httpResponse.setContentType("application/json; charset=utf-8");  
	    PrintWriter out = null;  
	    try {  
	        out = httpResponse.getWriter();  
	        out.append(responseJSONObject.toString());  
	      
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } finally {  
	        if (out != null) {  
	            out.close();  
	        }  
	    }  
	} 
	
}
