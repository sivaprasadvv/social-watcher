package com.sitequesttech.social.watcher.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Common pages controller
 * 
 * @author sivaprasad.vindula@icentris.com
 * 
 */
@Controller
public class CommonPagesController {

	private static final Logger logger = Logger
			.getLogger(CommonPagesController.class);
	
	/**
	 * Login page
	 * 
	 * @return login page id
	 */
	@RequestMapping(value = { "/login" })
	public String getLoginPage() {
		logger.info("Calling Login page.");
		return "loginPage";
	}

	/**
	 * Home Page
	 * 
	 * @return home page id
	 */
	@RequestMapping(value = { "/index" , "/"}, method = RequestMethod.GET)
	public String getHomePage() {
		logger.info("Calling home page.");
		return "homePage";
	}
	
	
	/**
	 * Query page
	 * 
	 * @return query page
	 */	
	@RequestMapping(value = { "/query" }, method = RequestMethod.GET)
	public String getQueryPage() {
		logger.info("Calling Query page.");
		return "queryPage";
	}
	
	/** to make auditToReviewListPage as home page
	 * 
	 * @param request
	 * @return
	 */
	/*@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String getToReviewPage(HttpServletRequest request) {
		logger.info("Calling ToReview page.");
		return "auditToReviewListPage";
	}*/
	
}
