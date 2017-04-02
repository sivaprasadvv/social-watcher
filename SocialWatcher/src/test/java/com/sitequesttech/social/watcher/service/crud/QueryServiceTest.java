package com.sitequesttech.social.watcher.service.crud;


import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sitequesttech.social.watcher.domain.entity.Query;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath:socialwatcher-spring-application-context-test.xml"})
public class QueryServiceTest {
	
	private static final Logger logger = Logger
			.getLogger(QueryServiceTest.class);
	
	@Autowired
	QueryService queryService;
	
	
	@Test
	public void testCreate() {
		logger.info("in testCreate");
		Query query = new Query();
		query.setQueryText("TSFL");
		query.setCreatedBy(1L);
		query.setCreatedDate(new Date());
		query = queryService.create(query);
		logger.debug(query.getId());
		
	}

}
