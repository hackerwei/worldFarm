package com.hz.world.common.util;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtils {

	private static final Logger logger = LoggerFactory.getLogger("http.monitor.logger");

	public static final String URL_CHARSET_UTF8 = "UTF-8";

	public static final String URL_CHARSET_GBK = "GBK";

	private static final String CONNECT_FLAG = "&";

	private static final String EMPTY = "";

	private static final Integer CONNECTION_TIME_OUT = 5000;

	private static final Integer SOCKET_TIME_OUT = 30000;

	private static final Integer MAX_CONN_TOTAL = 200;

	private static final Integer MAX_CONN_PER_ROUTE = 20;

	private static final Integer MAX_CONN_PER_SPECIAL_ROUTE = 100;

	public static CloseableHttpClient getHttpClient() {
		return getHttpClient(null, null);
	}

	public static CloseableHttpClient getHttpClient(String host, Integer port) {

		ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
		LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();

		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create()
				.register("http", plainsf).register("https", sslsf).build();
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
		// 将最大连接数增加
		connectionManager.setMaxTotal(MAX_CONN_TOTAL);
		// 将每个路由基础的连接增加
		connectionManager.setDefaultMaxPerRoute(MAX_CONN_PER_ROUTE);

		if (StringUtils.isNotEmpty(host)) {
			// 目标主机
			HttpHost httpHost = new HttpHost(host, port != null ? port : 80);
			// 将目标主机的最大连接数增加
			connectionManager.setMaxPerRoute(new HttpRoute(httpHost), MAX_CONN_PER_SPECIAL_ROUTE);
		}

		// 请求重试处理
		HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
			public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
				if (executionCount >= 3) {// 如果已经重试了3次，就放弃
					return false;
				}
				if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
					return true;
				}
				if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
					return false;
				}
				if (exception instanceof InterruptedIOException) {// 超时
					return false;
				}
				if (exception instanceof UnknownHostException) {// 目标服务器不可达
					return false;
				}
				if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
					return false;
				}
				if (exception instanceof SSLException) {// SSL握手异常
					return false;
				}

				HttpClientContext clientContext = HttpClientContext.adapt(context);
				HttpRequest request = clientContext.getRequest();
				// 如果请求是幂等的，就再次尝试
				// put post 是非幂等的.HttpPut,
				// HttpPost他们继承自HttpEntityEnclosingRequestBase
				if (!(request instanceof HttpEntityEnclosingRequest)) {
					return true;
				}
				return false;
			}
		};

		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager)
				.setRetryHandler(httpRequestRetryHandler).build();

		return httpClient;
	}

	/**
	 * 
	 * 设置连接超时
	 * 
	 * @param httpRequestBase
	 */
	private static void setRequestConfig(HttpRequestBase httpRequestBase) {

		/**
		 * ConnectTimeout : 请求http服务器建立连接的超时时间 ConnectionRequestTimeout :
		 * 从连接管理器中获取一个连接的超时时间 SocketTimeout : 连接建立后获取读取数据响应的超时时间
		 * 
		 */

		// 配置请求的超时设置
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(1000)
				.setConnectTimeout(CONNECTION_TIME_OUT).setSocketTimeout(SOCKET_TIME_OUT).build();
		httpRequestBase.setConfig(requestConfig);
	}

	/**
	 * 
	 * 设置POST参数
	 * 
	 * @param httpost
	 * @param params
	 */
	private static void setPostParams(HttpPost httpost, Map<String, String> params) {
		if (params == null)
			return;

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			nvps.add(new BasicNameValuePair(key, StringUtils.defaultIfEmpty(params.get(key), "")));
		}
		try {
			httpost.setEntity(new UrlEncodedFormEntity(nvps, URL_CHARSET_UTF8));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * POST请求URL获取内容
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 * 
	 */
	public static String post(String url, Map<String, String> params) {
		if (url == null)
			return null;
		CloseableHttpResponse response = null;
		try {

			HttpPost httppost = new HttpPost(url);
			setRequestConfig(httppost);
			setPostParams(httppost, params);

			response = getHttpClient().execute(httppost, HttpClientContext.create());

			// 输出Http日志
			if (response != null && response.getStatusLine() != null) {
				logger.info("[POST-" + response.getStatusLine().getStatusCode() + "] "
						+ getUrl(url, params, URL_CHARSET_UTF8));
			} else {
				logger.info("[POST-UNKNOW] " + getUrl(url, params, URL_CHARSET_UTF8));
			}

			// 判断响应是否正常
			if (response == null || response.getStatusLine() == null || (response.getStatusLine().getStatusCode() != 200
					&& response.getStatusLine().getStatusCode() != 400)) {
				return null;
			}
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity, URL_CHARSET_UTF8);

			logger.info("[RESP] " + result);

			// 关闭底层流
			EntityUtils.consume(entity);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[POST-EXCEPTION] " + getUrl(url, params, URL_CHARSET_UTF8), e);
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * GET请求URL获取内容
	 * 
	 * @param url
	 * @return
	 */
	public static String get(String url, Map<String, String> params) {
		if (url == null)
			return null;

		CloseableHttpResponse response = null;
		try {

			HttpGet httpget = new HttpGet(getUrl(url, params, URL_CHARSET_UTF8));
			setRequestConfig(httpget);

			response = getHttpClient().execute(httpget, HttpClientContext.create());

			// 输出Http日志
			if (response != null && response.getStatusLine() != null) {
				logger.info("[GET-" + response.getStatusLine().getStatusCode() + "]  "
						+ getUrl(url, params, URL_CHARSET_UTF8));
			} else {
				logger.info("[GET-UNKNOW] " + getUrl(url, params, URL_CHARSET_UTF8));
			}

			// 判断响应是否正常
			if (response == null || response.getStatusLine() == null || (response.getStatusLine().getStatusCode() != 200
					&& response.getStatusLine().getStatusCode() != 400)) {
				return null;
			}
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity, URL_CHARSET_UTF8);

			logger.info("[RESP] " + result);

			// 关闭底层流
			EntityUtils.consume(entity);
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("[GET-EXCEPTION] " + getUrl(url, params, URL_CHARSET_UTF8), e);
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * POST请求URL获取内容
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 * 
	 */
	public static String postData(String url, String dataParam) {
		if (url == null)
			return null;
		if (dataParam == null)
			return null;

		HttpPost httppost = new HttpPost(url);
		setRequestConfig(httppost);

		StringEntity reqEntity = new StringEntity(dataParam, URL_CHARSET_UTF8);
		reqEntity.setContentType("application/json");

		// 设置Post entity
		httppost.setEntity(reqEntity);

		CloseableHttpResponse response = null;
		try {
			response = getHttpClient().execute(httppost, HttpClientContext.create());

			// 输出Http日志
			if (response != null && response.getStatusLine() != null) {
				logger.info("[POST-" + response.getStatusLine().getStatusCode() + "] " + url + "@" + dataParam);
			} else {
				logger.info("[POST-UNKNOW] " + url + "@" + dataParam);
			}

			// 判断响应是否正常
			if (response == null || response.getStatusLine() == null
					|| response.getStatusLine().getStatusCode() != 200) {
				return null;
			}
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity, URL_CHARSET_UTF8);

			logger.info("[RESP] " + result);

			// 关闭底层流
			EntityUtils.consume(entity);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[POST-EXCEPTION] " + url + "@" + dataParam, e);
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * POST请求URL获取内容，仅用于七牛，因为请求头配置不一样
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 * 
	 */
	public static String postDataToQiNiu(String url, String params, String contentType, String authorization) {
		if (url == null) {
			return null;
		}
		CloseableHttpResponse response = null;
		try {

			HttpPost httppost = new HttpPost(url);
			setRequestConfig(httppost);
			
			StringEntity reqEntity = new StringEntity(params, URL_CHARSET_UTF8);
			reqEntity.setContentType(contentType);
			httppost.setHeader("Authorization", authorization);
			
			// 设置Post entity
			httppost.setEntity(reqEntity);
			
			response = getHttpClient().execute(httppost, HttpClientContext.create());
			
			// 判断响应是否正常
			if (response == null || response.getStatusLine() == null || (response.getStatusLine().getStatusCode() != 200
					&& response.getStatusLine().getStatusCode() != 400)) {
				return null;
			}
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity, URL_CHARSET_UTF8);

			// 关闭底层流
			EntityUtils.consume(entity);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static String getUrl(String domain, Map<String, String> paramMap, String charset) {
		if (StringUtils.isEmpty(domain))
			return EMPTY;

		StringBuilder fullUrl = new StringBuilder(domain);

		if (paramMap != null && paramMap.size() > 0) {
			if (domain.indexOf("?") == -1) {
				fullUrl.append("?").append(getParamterUrl(paramMap, charset));
			} else {
				fullUrl.append("&").append(getParamterUrl(paramMap, charset));
			}
		}
		return fullUrl.toString();
	}

	public static String getParamterUrl(Map<String, String> paramMap, String charset) {
		if (null == paramMap || paramMap.keySet().size() == 0)
			return (EMPTY);

		StringBuilder url = new StringBuilder(EMPTY);
		for (String key : paramMap.keySet()) {
			String val = StringUtils.defaultIfEmpty(paramMap.get(key).toString(), EMPTY);
			if (StringUtils.isNotBlank(charset)) {
				try {
					val = URLDecoder.decode(val, charset);
					val = URLEncoder.encode(val, charset);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}

			if (url.toString().length() > 0) {
				url.append(CONNECT_FLAG);
			}
			url.append(key).append("=").append(val);
		}
		return url.toString();
	}
}
