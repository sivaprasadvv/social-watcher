package com.sitequesttech.social.watcher.common.support;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SocialWatcherConstants {
	
	public static String APPLICATION_HOST_NAME;
	public static String APPLICATION_PORT_NUMBER;
	public static String APPLICATION_ROOT_CONTEXT;
	public static String HIGHLIGHT_QUERYTEXT_FORMAT;
	public static String NO_IMAGE_URL;
	public static String DEFAULT_ROLE_ADMIN;
	public static String TWITTER_SEARCH_ENGINE;
	public static String FACEBOOK_SEARCH_ENGINE;
	public static String FACEBOOK_IMAGE_URL;
	public static String TWITTER_IMAGE_URL;
	public static String IMAGE_DIMENSION_WIDTH;
	public static String IMAGE_DIMENSION_HEIGHT;
	public static String SEARCH_QUERYTEXT_INPUT_BOX_NUMBER;
	public static String URL_EXTRACT_FROM_TEXT_PATTERN;	
	
	// Start: Facebook
	public static String FACEBOOK_CLIENT_ID;
	public static String FACEBOOK_CLIENT_SECRET_KEY;
	public static String FACEBOOK_CLIENT_ACCESS_TOKEN;
	public static String FACEBOOK_CLIENT_APPTOKEN;
	public static String FACEBOOK_POSTS_SINCE;
	public static String FACEBOOK_POSTS_UNTIL;
	public static String FACEBOOK_POSTS_LIMIT;
	// End: Facebook
	
	// Start: Twitter
	public static String TWITTER_OAUTH_CONSUMER_KEY;
	public static String TWITTER_OAUTH_CONSUMER_SECRET;
	public static String TWITTER_OAUTH_ACCESSTOKEN_KEY;
	public static String TWITTER_OAUTH_ACCESSTOKEN_SECRET;
	public static String TWITTER_TWEETS_SINCE;
	// End: Twitter
	
	//	Start: Mail Constants
	public static String MAIL_SERVICE_ENABLE;
	public static String MAIL_TO_ADDRESS;
	public static String MAIL_FROM_ADDRESS;
	public static String MAIL_HOST;
	public static String MAIL_PORT;
	public static String MAIL_ACCOUNT_USERNAME;
	public static String MAIL_ACCOUNT_PASSWORD;
	public static String MAIL_TRANSPORT_PROTOCOL;
	public static String MAIL_SMTP_AUTH;
	public static String MAIL_AMTP_STARTTLS_ENABLE;
	public static String MAIL_SUBJECT;
	public static String MAIL_DEBUG;
	public static String MAIL_BODY;
	//	End: Mail Constants
	
	//	Start: Scheduler Constants
	public static String SCHEDULER_QUARTZ_SERVICE_ENABLE;
	//	End: Scheduler Constants
	
	
	@Value("${facebook.client.id}")
	public void setFACEBOOK_CLIENT_ID(String fACEBOOK_CLIENT_ID) {
		FACEBOOK_CLIENT_ID = fACEBOOK_CLIENT_ID;
	}
	
	@Value("${facebook.client.secretkey}")
	public void setFACEBOOK_CLIENT_SECRET_KEY(
			String fACEBOOK_CLIENT_SECRET_KEY) {
		FACEBOOK_CLIENT_SECRET_KEY = fACEBOOK_CLIENT_SECRET_KEY;
	}
	
	@Value("${facebook.client.accesstoken}")
	public void setFACEBOOK_CLIENT_ACCESS_TOKEN(
			String fACEBOOK_CLIENT_ACCESS_TOKEN) {
		FACEBOOK_CLIENT_ACCESS_TOKEN = fACEBOOK_CLIENT_ACCESS_TOKEN;
	}

	@Value("${facebook.client.apptoken}")
	public void setFACEBOOK_CLIENT_APPTOKEN(String fACEBOOK_CLIENT_APPTOKEN) {
		FACEBOOK_CLIENT_APPTOKEN = fACEBOOK_CLIENT_APPTOKEN;
	}
	
	@Value("${facebook.posts.since}")
	public void setFACEBOOK_POSTS_SINCE(String fACEBOOK_POSTS_SINCE) {
		FACEBOOK_POSTS_SINCE = fACEBOOK_POSTS_SINCE;
	}

	@Value("${facebook.posts.until}")
	public void setFACEBOOK_POSTS_UNTIL(String fACEBOOK_POSTS_UNTIL) {
		FACEBOOK_POSTS_UNTIL = fACEBOOK_POSTS_UNTIL;
	}

	@Value("${facebook.posts.limit}")
	public void setFACEBOOK_POSTS_LIMIT(String fACEBOOK_POSTS_LIMIT) {
		FACEBOOK_POSTS_LIMIT = fACEBOOK_POSTS_LIMIT;
	}
	
	@Value("${twitter.oauth.consumer.key}")
	public void setTWITTER_OAUTH_CONSUMER_KEY(
			String tWITTER_OAUTH_CONSUMER_KEY) {
		TWITTER_OAUTH_CONSUMER_KEY = tWITTER_OAUTH_CONSUMER_KEY;
	}

	@Value("${twitter.oauth.consumer.secret}")
	public void setTWITTER_OAUTH_CONSUMER_SECRET(
			String tWITTER_OAUTH_CONSUMER_SECRET) {
		TWITTER_OAUTH_CONSUMER_SECRET = tWITTER_OAUTH_CONSUMER_SECRET;
	}

	@Value("${twitter.oauth.accesstoken.key}")
	public void setTWITTER_OAUTH_ACCESSTOKEN_KEY(
			String tWITTER_OAUTH_ACCESSTOKEN_KEY) {
		TWITTER_OAUTH_ACCESSTOKEN_KEY = tWITTER_OAUTH_ACCESSTOKEN_KEY;
	}

	@Value("${twitter.oauth.accesstoken.secret}")
	public void setTWITTER_OAUTH_ACCESSTOKEN_SECRET(
			String tWITTER_OAUTH_ACCESSTOKEN_SECRET) {
		TWITTER_OAUTH_ACCESSTOKEN_SECRET = tWITTER_OAUTH_ACCESSTOKEN_SECRET;
	}

	@Value("${twitter.tweets.since}")
	public void setTWITTER_TWEETS_SINCE(String tWITTER_TWEETS_SINCE) {
		TWITTER_TWEETS_SINCE = tWITTER_TWEETS_SINCE;
	}

	@Value("${default.role.admin}")
	public void setDEFAULT_ROLE_ADMIN(String dEFAULT_ROLE_ADMIN) {
		DEFAULT_ROLE_ADMIN = dEFAULT_ROLE_ADMIN;
	}

	@Value("${twitter.search.engine.name}")
	public void setTWITTER_SEARCH_ENGINE(String tWITTER_SEARCH_ENGINE) {
		TWITTER_SEARCH_ENGINE = tWITTER_SEARCH_ENGINE;
	}

	@Value("${facebook.search.engine.name}")
	public void setFACEBOOK_SEARCH_ENGINE(String fACEBOOK_SEARCH_ENGINE) {
		FACEBOOK_SEARCH_ENGINE = fACEBOOK_SEARCH_ENGINE;
	}

	@Value("${url.extract.from.text.pattern}")
	public void setURL_EXTRACT_FROM_TEXT_PATTERN(
			String uRL_EXTRACT_FROM_TEXT_PATTERN) {
		URL_EXTRACT_FROM_TEXT_PATTERN = uRL_EXTRACT_FROM_TEXT_PATTERN;
	}

	@Value("${no.image.url}")
	public void setNO_IMAGE_URL(String nO_IMAGE_URL) {
		NO_IMAGE_URL = nO_IMAGE_URL;
	}

	@Value("${highlight.querytext.format}")
	public void setHIGHLIGHT_QUERYTEXT_FORMAT(
			String hIGHLIGHT_QUERYTEXT_FORMAT) {
		HIGHLIGHT_QUERYTEXT_FORMAT = hIGHLIGHT_QUERYTEXT_FORMAT;
	}

	@Value("${application.host.name}")
	public void setAPPLICATION_HOST_NAME(String aPPLICATION_HOST_NAME) {
		APPLICATION_HOST_NAME = aPPLICATION_HOST_NAME;
	}

	@Value("${application.port.number}")
	public void setAPPLICATION_PORT_NUMBER(String aPPLICATION_PORT_NUMBER) {
		APPLICATION_PORT_NUMBER = aPPLICATION_PORT_NUMBER;
	}

	@Value("${application.root.context}")
	public void setAPPLICATION_ROOT_CONTEXT(String aPPLICATION_ROOT_CONTEXT) {
		APPLICATION_ROOT_CONTEXT = aPPLICATION_ROOT_CONTEXT;
	}

	@Value("${facebook.image.url}")
	public void setFACEBOOK_IMAGE_URL(String fACEBOOK_IMAGE_URL) {
		FACEBOOK_IMAGE_URL = fACEBOOK_IMAGE_URL;
	}

	@Value("${twitter.image.url}")
	public void setTWITTER_IMAGE_URL(String tWITTER_IMAGE_URL) {
		TWITTER_IMAGE_URL = tWITTER_IMAGE_URL;
	}

	@Value("${image.dimension.width}")
	public void setIMAGE_DIMENSION_WIDTH(String iMAGE_DIMENSION_WIDTH) {
		IMAGE_DIMENSION_WIDTH = iMAGE_DIMENSION_WIDTH;
	}

	@Value("${image.dimension.height}")
	public void setIMAGE_DIMENSION_HEIGHT(String iMAGE_DIMENSION_HEIGHT) {
		IMAGE_DIMENSION_HEIGHT = iMAGE_DIMENSION_HEIGHT;
	}

	@Value("${search.querytext.input.box.number}")
	public void setSEARCH_QUERYTEXT_INPUT_BOX_NUMBER(
			String sEARCH_QUERYTEXT_INPUT_BOX_NUMBER) {
		SEARCH_QUERYTEXT_INPUT_BOX_NUMBER = sEARCH_QUERYTEXT_INPUT_BOX_NUMBER;
	}

	@Value("${mail.service.enable}")
	public void setMAIL_SERVICE_ENABLE(String mAIL_SERVICE_ENABLE) {
		MAIL_SERVICE_ENABLE = mAIL_SERVICE_ENABLE;
	}

	@Value("${mail.to.address}")
	public void setMAIL_TO_ADDRESS(String mAIL_TO_ADDRESS) {
		MAIL_TO_ADDRESS = mAIL_TO_ADDRESS;
	}

	@Value("${mail.from.address}")
	public void setMAIL_FROM_ADDRESS(String mAIL_FROM_ADDRESS) {
		MAIL_FROM_ADDRESS = mAIL_FROM_ADDRESS;
	}

	@Value("${mail.host}")
	public void setMAIL_HOST(String mAIL_HOST) {
		MAIL_HOST = mAIL_HOST;
	}

	@Value("${mail.port}")
	public void setMAIL_PORT(String mAIL_PORT) {
		MAIL_PORT = mAIL_PORT;
	}

	@Value("${mail.account.username}")
	public void setMAIL_ACCOUNT_USERNAME(String mAIL_ACCOUNT_USERNAME) {
		MAIL_ACCOUNT_USERNAME = mAIL_ACCOUNT_USERNAME;
	}

	@Value("${mail.account.password}")
	public void setMAIL_ACCOUNT_PASSWORD(String mAIL_ACCOUNT_PASSWORD) {
		MAIL_ACCOUNT_PASSWORD = mAIL_ACCOUNT_PASSWORD;
	}

	@Value("${mail.transport.protocol}")
	public void setMAIL_TRANSPORT_PROTOCOL(String mAIL_TRANSPORT_PROTOCOL) {
		MAIL_TRANSPORT_PROTOCOL = mAIL_TRANSPORT_PROTOCOL;
	}

	@Value("${mail.smtp.auth}")
	public void setMAIL_SMTP_AUTH(String mAIL_SMTP_AUTH) {
		MAIL_SMTP_AUTH = mAIL_SMTP_AUTH;
	}

	@Value("${mail.smtp.starttls.enable}")
	public void setMAIL_AMTP_STARTTLS_ENABLE(String mAIL_AMTP_STARTTLS_ENABLE) {
		MAIL_AMTP_STARTTLS_ENABLE = mAIL_AMTP_STARTTLS_ENABLE;
	}

	@Value("${mail.subject}")
	public void setMAIL_SUBJECT(String mAIL_SUBJECT) {
		MAIL_SUBJECT = mAIL_SUBJECT;
	}

	@Value("${mail.debug}")
	public void setMAIL_DEBUG(String mAIL_DEBUG) {
		MAIL_DEBUG = mAIL_DEBUG;
	}

	@Value("${mail.body}")
	public void setMAIL_BODY(String mAIL_BODY) {
		MAIL_BODY = mAIL_BODY;
	}

	@Value("${scheduler.quartz.service.enable}")
	public void setSCHEDULER_QUARTZ_SERVICE_ENABLE(String sCHEDULER_QUARTZ_SERVICE_ENABLE) {
		SCHEDULER_QUARTZ_SERVICE_ENABLE = sCHEDULER_QUARTZ_SERVICE_ENABLE;
	}
	

}
