package com.hz.world.common.util;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.apache.http.HttpHost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @outhor lujian
 * @create 2018-06-11 15:20
 */
@Configuration
public class HttpClientUtil {
    @Value("${http.use.proxy:false}")
    private boolean isUseProxy;
    @Value("${http.proxy.host:}")
    private String proxyHost;
    @Value("${http.proxy.port:}")
    private Integer proxyPort;
    @Value("${http.conn.timeout:500}")
    private int connTimeout;
    @Value("${http.read.timeout:2000}")
    private int readTimeout;

    @Primary
    @Bean
    public RestTemplate httpRestTemplate() throws Exception {
        CloseableHttpClient httpClient;

        if (isUseProxy) {
            HttpHost proxy = new HttpHost(proxyHost, proxyPort);
            httpClient = HttpClients.custom().setProxy(proxy).build();
        } else {
            httpClient = HttpClients.createDefault();
        }
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        factory.setConnectionRequestTimeout(connTimeout);
        factory.setConnectTimeout(connTimeout);
        factory.setReadTimeout(readTimeout);

        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.setRequestFactory(factory);
        restTemplate.getMessageConverters().add(new FastJsonHttpMessageConverter());
        return restTemplate;
    }
}