package com.sitequesttech.social.watcher.service.search;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.restfb.Connection;
import com.restfb.Parameter;
import com.restfb.types.Post;
import com.sitequesttech.social.watcher.common.config.ConnectionConfig;
import com.sitequesttech.social.watcher.domain.entity.Query;
import com.sitequesttech.social.watcher.domain.entity.QueryResult;
import com.sitequesttech.social.watcher.service.helper.QueryHelper;
import com.sitequesttech.social.watcher.common.exception.SocialWatcherException;
import com.sitequesttech.social.watcher.common.support.SocialWatcherConstants;
import com.sitequesttech.social.watcher.common.support.SocialWatcherUtil;

@Component("FacebookSearcher")
public class FacebookSearcher extends SocialSearcher {
	
	private static final Logger logger = Logger
			.getLogger(FacebookSearcher.class);
	
	@Autowired
	private ConnectionConfig config;
	
	@Autowired
	QueryHelper queryHelper;
	
	/**
	 * The following changes will go into effect on July 10, 2013:
	 * Graph API search changes
	 * App access tokens will be required for all search Graph API calls except Places and Pages. 
	 * Search for application will no longer be supported.
	 * @param queryString
	 * @return
	 */
	public Connection<Post> getPublicNewsFeeds(String queryString) {
		final String METHOD_NAME = "getPublicNewsFeeds - ";
		logger.debug(METHOD_NAME+"for Query " +queryString);
		
		if (null == queryString || "".equals(queryString))
			throw new IllegalArgumentException("query text should not by empty or null");
		
		
		/*return config.getPublicFacebookConnection().fetchConnection("search", Post.class,
				Parameter.with("q", queryString), 
				Parameter.with("type", "post"));*/
		
		/*return config.getFacebookConnection(SocialWatcherConstants.FACEBOOK_CLIENT_ACCESS_TOKEN).fetchConnection("search", Post.class,
				Parameter.with("q", queryString), 
				Parameter.with("type", "post"));*/
		
		return config.getFacebookConnection(SocialWatcherConstants.FACEBOOK_CLIENT_APPTOKEN).fetchConnection("search", Post.class,
				Parameter.with("q", queryString), 
				Parameter.with("type", "post"));
		
	}
	
	/**
	 * 
	 * @param queryString
	 * @param since - fromDate
	 * @param until - toDate
	 * @return
	 */
	public Connection<Post> getPublicNewsFeedsByDate(String queryString, Date fromDate, Date toDate) {
		final String METHOD_NAME = "getPublicNewsFeedsByDate - ";
		logger.debug(METHOD_NAME+"from date :" +fromDate+"/to date:" +toDate);
		
		if (null == queryString || "".equals(queryString))
			throw new IllegalArgumentException("query should not by empty or null");
		
		if (null == fromDate)
			throw new IllegalArgumentException("from Date should not by empty or null");
		
		if (null == toDate)
			throw new IllegalArgumentException("to Date should not by empty or null");
		
		return config.getPublicFacebookConnection().fetchConnection("search", Post.class,
			    Parameter.with("q", queryString), 
			    Parameter.with("type", "post"),  
				Parameter.with("limit", SocialWatcherConstants.FACEBOOK_POSTS_LIMIT), 
				Parameter.with("since", fromDate),
				Parameter.with("until", toDate)
				);
		
	}
	
	public Set<QueryResult> getQueryResults(Query query) throws SocialWatcherException {
    	
    	final String METHOD_NAME = "getQueryResults(\""+query.getQueryText()+"\") - ";
		logger.debug(METHOD_NAME);
		Set<QueryResult> feeds = new HashSet<QueryResult>();
		
		try {
			Connection<Post> feed = getPublicNewsFeeds(query.getQueryText());
		
			long postIndex = 1L;
			for (List<Post> feedConnectionPage : feed) {
				  for (Post post : feedConnectionPage) {
					  
					  /*if (null != post.getPlace()) {
						  if (null != post.getPlace().getLocation())
						  logger.debug("Place :" +post.getPlace().getLocation().getCountry()+","+post.getPlace().getLocation().getState()+","+post.getPlace().getLocation().getCity()); 
					  }*/
						 
					 // logger.debug("@Post["+(postIndex++)+ "] created at "+post.getCreatedTime()+ "/message: " + post.getMessage()+"/picture:" +post.getPicture()+"/icon:" +post.getIcon());
					  QueryResult result = prepareQueryResultObjectFromPostObject(query,post);
					  result.setQuery(queryHelper.copyFrom(query));
					  result.setRank(postIndex++);
					  if (logger.isDebugEnabled())
					  	logger.debug(result);
					  feeds.add(result);
				  }
			}
		} catch(Exception ex) {
			logger.error(METHOD_NAME, ex);
			//TO DO: just log the error and check possibility of returning feeds instead of throwing exception.
			throw new SocialWatcherException(ex);
		}
    	
    	return feeds;
    }
	
	private QueryResult prepareQueryResultObjectFromPostObject(Query query, Post post) {
		
		QueryResult result = new QueryResult();
		
		String profileUrl = "";
		
		profileUrl = post.getPicture();
		
		if (SocialWatcherUtil.isEmptyOrNullString(profileUrl) )
			profileUrl = post.getIcon();
		
		if (SocialWatcherUtil.isEmptyOrNullString(profileUrl) )
			profileUrl = SocialWatcherConstants.NO_IMAGE_URL;
		
		String url = SocialWatcherUtil.extractUrlFromText(post.getMessage());
		result.setUrl(SocialWatcherUtil.isNotEmptyAndNotNullString(url) ? url : "###");
		result.setProfileImageUrl(profileUrl);
		result.setDescription(post.getMessage());
		result.setTitle(post.getFrom().getName());
		result.setCreatedDate(new Date());
		result.setCreatedBy(Long.valueOf(SocialWatcherConstants.DEFAULT_ROLE_ADMIN));
		result.setSourceId(post.getId());
		result.setSourceCreatedDate(post.getCreatedTime());
		result.setQuery(query);
		result.setPlace(getPlace(post));
		
		return result;
		
	}
	
	private String getPlace(Post post) {
		
		String placeStr = "";
		
		if (SocialWatcherUtil.isNullObject(post.getPlace()))
			return "-";
		
		if (SocialWatcherUtil.isNullObject(post.getPlace().getLocation()))
			return "-";
		
		if (SocialWatcherUtil.isNotNullObject(post.getPlace().getLocation().getCountry()))
			placeStr += post.getPlace().getLocation().getCountry()+",";
		else 
			placeStr += "-,";
		
		if (SocialWatcherUtil.isNotNullObject(post.getPlace().getLocation().getState()))
			placeStr += post.getPlace().getLocation().getState()+",";
		else 
			placeStr += "-,";
		
		if (SocialWatcherUtil.isNotNullObject(post.getPlace().getLocation().getCity()))
			placeStr += post.getPlace().getLocation().getCity();
		else 
			placeStr += "-";
		
		if (placeStr.equals("-,-,-"))			
			return "-";
		
		if (logger.isDebugEnabled())
			logger.debug("placeStr:" +placeStr);
		
		return placeStr;
	}
	
}
