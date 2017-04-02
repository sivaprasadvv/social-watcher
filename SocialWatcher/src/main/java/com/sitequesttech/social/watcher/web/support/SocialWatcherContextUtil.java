package com.sitequesttech.social.watcher.web.support;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.sitequesttech.social.watcher.domain.entity.Login;


@Component("SocialWatcherContextUtil")
public class SocialWatcherContextUtil {
	
	private static final Logger logger = Logger
			.getLogger(SocialWatcherContextUtil.class);
	
	public Login getLoginUser(HttpServletRequest request) {
		LoginContextUtil loginContextUtil = (LoginContextUtil)request.getAttribute("userContext");
    	Login user = loginContextUtil.getUser();
    	logger.debug("logged in user id/username:" +user.getId()+"/"+user.getName());
    	return user;    	
	}
	
	public String getLoginUserRole(HttpServletRequest request) {
		LoginContextUtil userContextUtil = (LoginContextUtil)request.getAttribute("userContext");
    	String role = userContextUtil.getRole();
    	logger.debug("logged in user role:" +role);
    	return role;    	
	}

}
