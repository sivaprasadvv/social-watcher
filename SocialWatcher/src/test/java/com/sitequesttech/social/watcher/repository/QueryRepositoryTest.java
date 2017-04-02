package com.sitequesttech.social.watcher.repository;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sitequesttech.social.watcher.domain.repository.QueryRepository;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath:socialwatcher-spring-application-context-test.xml"})
public class QueryRepositoryTest {
	
	private static final Logger logger = Logger
			.getLogger(QueryRepositoryTest.class);
	
	@Autowired
	QueryRepository queryRepository;
	
	
	@Test
	public void testFindDistinctQueryText() {
		
		List<String> queryTexts = queryRepository.findDistinctQueryText();
		for (String queryText : queryTexts) {
			logger.info(queryText);
		}
	}

}
