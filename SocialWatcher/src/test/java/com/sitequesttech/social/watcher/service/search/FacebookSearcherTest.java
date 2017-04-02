package com.sitequesttech.social.watcher.service.search;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sitequesttech.social.watcher.common.exception.SocialWatcherException;
import com.sitequesttech.social.watcher.domain.entity.Query;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath:socialwatcher-spring-application-context-test.xml"})
public class FacebookSearcherTest {
	
	private static final Logger logger = Logger
			.getLogger(FacebookSearcherTest.class);
	
	@Autowired
	FacebookSearcher facebookSearcher;
	
	@Test
	public void testGetQueryResults() throws SocialWatcherException {
		logger.info("in testGetQueryResults()");
		Query equery = new Query();
		equery.setQueryText("eSuite");
		facebookSearcher.getQueryResults(equery);
	}

}
