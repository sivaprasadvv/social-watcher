package com.sitequesttech.social.watcher.service.export;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sitequesttech.social.watcher.domain.entity.Query;
import com.sitequesttech.social.watcher.domain.entity.QueryResult;
import com.sitequesttech.social.watcher.domain.repository.QueryResultRepository;
import com.sitequesttech.social.watcher.service.crud.QueryResultService;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath:socialwatcher-spring-application-context-test.xml"})
public class ExporterTest {
	
	private static final Logger logger = Logger
			.getLogger(ExporterTest.class);
	
	@Autowired
	QueryResultRepository repository;
	
	@Autowired
	QueryResultService service;
	
	@Autowired
	Exporter exporter;
	
	List<QueryResult> qresult = null;
	
	@Before
	public void init() {
		Query query = new Query();
		query.setId(4L);
		//qresult = (List<QueryResult>) repository.findByQueryAndIsReviewedTrue(query);
		qresult = service.getPositiveReviewedQueryResultsByQuery(query);
	}
	
	@Test
	public void testExportToCsv() throws Exception {		
		String result = exporter.exportToCsvFile(qresult);
		logger.debug(result);
	}
	
	@Test
	public void testExportToExcel() throws Exception {
		String result = exporter.exportToExcelFile(qresult);
		logger.debug(result);
	}
	
	@Test
	public void testExportToPdf() throws Exception {
		String result = exporter.exportToPdfFile(qresult);
		logger.debug(result);
	}

}
