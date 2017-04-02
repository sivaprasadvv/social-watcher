package com.sitequesttech.social.watcher.service.search;

import java.util.Set;

import com.sitequesttech.social.watcher.domain.entity.Query;
import com.sitequesttech.social.watcher.domain.entity.QueryResult;
import com.sitequesttech.social.watcher.common.exception.SocialWatcherException;

public abstract class SocialSearcher {
	
	public abstract Set<QueryResult> getQueryResults(Query query) throws SocialWatcherException;

}
