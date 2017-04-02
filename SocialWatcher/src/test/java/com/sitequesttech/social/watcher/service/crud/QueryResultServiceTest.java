package com.sitequesttech.social.watcher.service.crud;

import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sitequesttech.social.watcher.common.exception.SocialWatcherException;
import com.sitequesttech.social.watcher.common.support.SocialWatcherConstants;
import com.sitequesttech.social.watcher.domain.entity.Query;
import com.sitequesttech.social.watcher.domain.entity.QueryResult;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath:socialwatcher-spring-application-context-test.xml"})
public class QueryResultServiceTest {
	
	private static final Logger logger = Logger
			.getLogger(QueryResultServiceTest.class);
	
	@Autowired
	QueryResultService queryResultService;
	
	@Autowired
	QueryService queryService;
	
	
	@Test
	public void testGetQueryResults() throws SocialWatcherException {
		Query query = queryService.getById(5L);
		logger.debug(query.getId());
		Set<QueryResult> queryResults = queryResultService.getQueryResponse(SocialWatcherConstants.TWITTER_SEARCH_ENGINE, query);
		logger.debug("Total results found:" +queryResults.size());
		/*for (QueryResult queryResult : queryResults) {
			logger.debug(queryResult);
		}*/
	}

}
