package br.com.libshare.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

public class InterceptorLibShare implements HandlerInterceptor{
	private final static Logger LOGGER = LoggerFactory.getLogger(InterceptorLibShare.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String postData;
		HttpServletRequest requestCacheWrapperObject = null;

		try {
			requestCacheWrapperObject = new ContentCachingRequestWrapper(request);
		    requestCacheWrapperObject.getParameterMap();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			postData = RequestLoggingUtil.readPayload(requestCacheWrapperObject);
            LOGGER.info("REQUEST DATA: " + postData);
		}

		injectHeaders(response);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		injectHeaders(response);
		String postDate;
		HttpServletResponse responseCacheWrapperObject = null;
		
		try {
			responseCacheWrapperObject = new ContentCachingResponseWrapper(response);
			responseCacheWrapperObject.getOutputStream();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			String postData = RequestLoggingUtil.readPayload(responseCacheWrapperObject);
			LOGGER.info("RESPONSE DATA: " + postData);			
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		injectHeaders(response);

		 LOGGER.info("RESPONSE: " + response.getStatus());

	}

	private void injectHeaders(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "X-XSRF-TOKEN,accept, authorization, content-type, x-requested-with");
	}

}
