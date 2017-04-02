package com.sitequesttech.social.watcher.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sitequesttech.social.watcher.web.support.LoginContextUtil;


/**
 * Expose the 'userContext' bean to the view
 * 
 * @author sivaprasad.vindula@icentris.com
 * 
 */
@Service
public class LoginContextInterceptor implements HandlerInterceptor {

	@Autowired
	private LoginContextUtil loginContext;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		request.setAttribute("userContext", loginContext);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
