package com.sitequesttech.social.watcher.common.config;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.sitequesttech.social.watcher.common.support.SocialWatcherConstants;

@Component
public class ConnectionConfig {
	
	private static final Logger logger = Logger
			.getLogger(ConnectionConfig.class);
	
	public FacebookClient getUserFacebookConnection(String accessToken) {
		final String METHOD_NAME = "getFacebookConnection(String accessToken) - ";
		logger.debug(METHOD_NAME+accessToken);
    	return new DefaultFacebookClient(accessToken);
    }
	
	public FacebookClient getFacebookConnection(String appToken) {
		final String METHOD_NAME = "getFacebookConnection(String appToken) - ";
		logger.debug(METHOD_NAME+appToken);
    	return new DefaultFacebookClient(appToken);
    }
	
	public FacebookClient getPublicFacebookConnection() {
		final String METHOD_NAME = "getPublicFacebookConnection() - ";
		logger.debug(METHOD_NAME);
    	return new DefaultFacebookClient();
    }
	
	
	/**
	 * 
	 * @return
	 */
	public Twitter getTwitterConnection() {
		 final String METHOD_NAME = "getTwitterConnection() - ";	
		logger.debug(METHOD_NAME);
		 
   	 ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
          .setOAuthConsumerKey(SocialWatcherConstants.TWITTER_OAUTH_CONSUMER_KEY)
          .setOAuthConsumerSecret(SocialWatcherConstants.TWITTER_OAUTH_CONSUMER_SECRET)
          .setOAuthAccessToken(SocialWatcherConstants.TWITTER_OAUTH_ACCESSTOKEN_KEY)
          .setOAuthAccessTokenSecret(SocialWatcherConstants.TWITTER_OAUTH_ACCESSTOKEN_SECRET);
        TwitterFactory tf = new TwitterFactory(cb.build());
        return tf.getInstance();
   }
	
	/**
	 * 
	 * @return
	 */
	public TwitterStream getTwitterStreamConnection(){
		final String METHOD_NAME = "getTwitterStreamConnection() - ";	
		logger.debug(METHOD_NAME);
		 ConfigurationBuilder cb = new ConfigurationBuilder();
         cb.setDebugEnabled(true)
           .setOAuthConsumerKey(SocialWatcherConstants.TWITTER_OAUTH_CONSUMER_KEY)
           .setOAuthConsumerSecret(SocialWatcherConstants.TWITTER_OAUTH_CONSUMER_SECRET)
           .setOAuthAccessToken(SocialWatcherConstants.TWITTER_OAUTH_ACCESSTOKEN_KEY)
           .setOAuthAccessTokenSecret(SocialWatcherConstants.TWITTER_OAUTH_ACCESSTOKEN_SECRET);
		return new TwitterStreamFactory(cb.build()).getInstance();
	}

}
