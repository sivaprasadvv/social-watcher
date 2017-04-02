package com.sitequesttech.social.watcher.service.search;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sitequesttech.social.watcher.common.config.ConnectionConfig;
import com.sitequesttech.social.watcher.common.exception.SocialWatcherException;
import com.sitequesttech.social.watcher.common.support.SocialWatcherConstants;
import com.sitequesttech.social.watcher.common.support.SocialWatcherUtil;
import com.sitequesttech.social.watcher.service.helper.QueryHelper;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

@Component("TwitterSearcher")
public class TwitterSearcher extends SocialSearcher {
	
	private static final Logger logger = Logger
			.getLogger(TwitterSearcher.class);
	
	@Autowired
	private ConnectionConfig config;
	
	@Autowired
	private QueryHelper queryHelper;
	
		
		/**
	 * 	
	 * @param equery
	 * @return
	 * @throws SocialWatcherException
	 */
	public Set<com.sitequesttech.social.watcher.domain.entity.QueryResult> getQueryResults(com.sitequesttech.social.watcher.domain.entity.Query equery) throws SocialWatcherException {
		final String METHOD_NAME = "getQueryResults(\""+equery.getQueryText()+"\") - ";
		Set<com.sitequesttech.social.watcher.domain.entity.QueryResult> tweets = new HashSet<com.sitequesttech.social.watcher.domain.entity.QueryResult>();
		
		try {
			Twitter twitter = config.getTwitterConnection();
			QueryResult qresult = null;		
			Query query = new Query();
			query.setQuery(equery.getQueryText());
			query.setCount(100);
			query.setSince(SocialWatcherConstants.TWITTER_TWEETS_SINCE);
			
			int page = 1;		
			
			do {
				logger.debug(METHOD_NAME+"Page: " + (page++));
				logger.debug(METHOD_NAME+"Query: " + query);
				qresult = twitter.search(query);	
				long index = 1L;
				for (Status status : qresult.getTweets()) {		
					/*if (null != status.getPlace())
						logger.debug(status.getPlace().getCountry()+"/"+status.getPlace().getFullName()+"/"+status.getPlace().getName()+"/"+status.getPlace().getPlaceType());*/
					com.sitequesttech.social.watcher.domain.entity.QueryResult result = prepareQueryResultObjectFromStatusObject(equery, status);
					result.setQuery(queryHelper.copyFrom(equery));
					result.setRank(index++);
		        	/*if (logger.isDebugEnabled())
		        		logger.debug(result);*/
		        	tweets.add(result);
				}
			} while ((query = qresult.nextQuery()) != null );
		
			logger.debug(METHOD_NAME+"Query object before returning from getQueryResults method is: " + query);
			
		} catch (TwitterException te) {				
			logger.error(METHOD_NAME, te);
			throw new SocialWatcherException(te);
		} catch(Exception ex) {
			logger.error(METHOD_NAME, ex);
			throw new SocialWatcherException(ex);
		}
			
        return tweets;
	}
	
	private com.sitequesttech.social.watcher.domain.entity.QueryResult prepareQueryResultObjectFromStatusObject(
			com.sitequesttech.social.watcher.domain.entity.Query equery, Status status) {
		
		com.sitequesttech.social.watcher.domain.entity.QueryResult result = new com.sitequesttech.social.watcher.domain.entity.QueryResult();
		
		String name = "";
		String profileUrl = "";
		
		if (SocialWatcherUtil.isNotNullObject(status.getUser())) {
			
			name = status.getUser().getName();
			profileUrl = status.getUser().getProfileImageURL();
			
			if (SocialWatcherUtil.isEmptyOrNullString(profileUrl) )
				profileUrl = status.getUser().getProfileImageURLHttps();
			
			if (SocialWatcherUtil.isEmptyOrNullString(profileUrl) )
				profileUrl = SocialWatcherConstants.NO_IMAGE_URL;
			
		}
		
		result.setTitle(name);
		String url = SocialWatcherUtil.extractUrlFromText(status.getText());
		result.setUrl(SocialWatcherUtil.isNotEmptyAndNotNullString(url) ? url : "###");
		result.setProfileImageUrl(profileUrl);
		result.setDescription(status.getText());
		result.setCreatedDate(new Date());
		result.setCreatedBy(Long.valueOf(SocialWatcherConstants.DEFAULT_ROLE_ADMIN));
		result.setSourceId(String.valueOf(status.getId()));
		result.setSourceCreatedDate(status.getCreatedAt());		
		result.setPlace(SocialWatcherUtil.isNotNullObject(status.getPlace()) ? status.getPlace().getFullName() : "-");
    	
    	return result;
		
	}

}
