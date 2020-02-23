package com.hz.world.api.common.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hz.world.api.common.support.BufferedRequestWrapper;
import com.hz.world.api.common.support.BufferedResponseWrapper;


public class LoggingFilter implements Filter {

    private static final Logger LOG_API = LoggerFactory.getLogger("api.log");
    private static final Logger LOG = LoggerFactory.getLogger(LoggingFilter.class);

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        long startTime = System.currentTimeMillis();
        BufferedRequestWrapper request = new BufferedRequestWrapper((HttpServletRequest) servletRequest);
        BufferedResponseWrapper response = new BufferedResponseWrapper((HttpServletResponse) servletResponse);
        StringBuilder requestBuilder = null, responseBuilder = null;
        String error = null;
        try {
            requestBuilder = dumpRequest(request);
            chain.doFilter(request, response);
            responseBuilder = dumpResponse(response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            error = "\"error\":\"" + e.getMessage() + "\"";
            LOG.error(e.getMessage(), e);
        } finally {
            StringBuilder result = new StringBuilder();
            result.append("\"status\":").append(response.getStatus());
            if (null != requestBuilder) {
                result.append(",").append(requestBuilder);
            }
            if (null != responseBuilder) {
                result.append(",").append(responseBuilder);
            }
            if (null != error) {
                result.append(",").append(error);
            }
            result.append(",\"execute_time\":" + (System.currentTimeMillis() - startTime)).append("ms");
            LOG_API.info(result.toString().replaceAll(":", "\\:").replaceAll(",", "\\,").replaceAll("\n", " ")
                    .replaceAll("\r", ""));
        }
    }

    private StringBuilder dumpRequest(BufferedRequestWrapper request) {
        StringBuilder b = new StringBuilder();
        String method = request.getMethod();
        b.append("\"method\":\"").append(method).append('\"');
        b.append(",\"request_url\":\"").append(request.getRequestURL()).append('\"');

        String boundary = null;
        String boundaryFlag = "multipart/form-data; boundary=";
        b.append(",\"request_headers\":{");
        Enumeration<String> hnames = request.getHeaderNames();
        while (hnames.hasMoreElements()) {
            String hname = hnames.nextElement();
            Enumeration<String> hvalues = request.getHeaders(hname);
            while (hvalues.hasMoreElements()) {
                String hvalue = hvalues.nextElement();
                b.append('\"').append(hname).append("\":\"").append(hvalue).append("\",");
                if (null == boundary && null != hvalue && hvalue.indexOf(boundaryFlag) >= 0) {
                    boundary = hvalue.substring(hvalue.indexOf(boundaryFlag) + boundaryFlag.length());
                }
            }
        }
        b.setLength(b.length() - 1);
        b.append('}');

        if (null != boundary) {
            b.append(",\"request_body\":\"... Binary Data ...\"");
        } else if (!"GET".equalsIgnoreCase(method)) {
            b.append(",\"request_body\":" + request.getBody());
        }
        return b;
    }

    private StringBuilder dumpResponse(BufferedResponseWrapper response) {
        StringBuilder b = new StringBuilder();
        b.append("\"response_headers\":{");
        Iterable<String> rhnames = response.getHeaderNames();
        for (String header : rhnames) {
            Iterable<String> rhvalues = response.getHeaders(header);
            for (String value : rhvalues) {
                b.append('\"').append(header).append("\":\"").append(value).append("\",");
            }
        }
        b.setLength(b.length() - 1);
        b.append('}');

        b.append(",\"response_body\":" + response.getBody());
        return b;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

}
