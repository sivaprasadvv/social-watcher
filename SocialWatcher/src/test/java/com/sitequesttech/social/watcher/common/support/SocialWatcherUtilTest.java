package com.sitequesttech.social.watcher.common.support;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sitequesttech.social.watcher.domain.entity.Query;
import com.sitequesttech.social.watcher.domain.entity.QueryResult;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={ "classpath:socialwatcher-spring-application-context-test.xml" })
public class SocialWatcherUtilTest {
	private static final Logger logger = Logger
			.getLogger(SocialWatcherUtilTest.class);
	
	@Test
	public void testExtractUrlFromText() {
		SocialWatcherUtil.extractUrlFromText("Kid tested http://t.co/Yd9GKPWNWB");
	}
	
	@Test
	public void testIsEmptyOrNullString() {
		logger.debug(SocialWatcherUtil.isEmptyOrNullString(""));
		logger.debug(SocialWatcherUtil.isEmptyOrNullString(null));
		logger.debug(SocialWatcherUtil.isEmptyOrNullString("null"));
		logger.debug(SocialWatcherUtil.isEmptyOrNullString("test"));
	}
	
	@Test
	public void testIsNotEmptyAndNullString() {
		logger.debug(SocialWatcherUtil.isNotEmptyAndNotNullString(" "));
		logger.debug(SocialWatcherUtil.isNotEmptyAndNotNullString("test"));
		logger.debug(SocialWatcherUtil.isNotEmptyAndNotNullString(""));
		logger.debug(SocialWatcherUtil.isNotEmptyAndNotNullString(null));
		logger.debug(SocialWatcherUtil.isNotEmptyAndNotNullString("null"));
	}
	
	@Test
	public void testHighlightQueryTextInResultText() {
		String queryText="eSuite";
		String resultText = "http://ic.longaberger.com/esuite/home/shereefravel?showID=15855043&hostName=Sheree+Fravel&closeDate=06/28/2013";
		logger.debug((resultText.contains(queryText) 
						|| resultText.contains(queryText.toLowerCase())
						|| resultText.contains(queryText.toUpperCase())));
		logger.debug(SocialWatcherUtil.highlightQueryTextInResultText("eSuite", "http://ic.longaberger.com/esuite/home/shereefravel?showID=15855043&hostName=Sheree+Fravel&closeDate=06/28/2013"));
	}
	
	@Test
	public void testIsImage() {
		logger.debug(SocialWatcherUtil.isImage("1.jpg"));
		logger.debug(SocialWatcherUtil.isImage("2.JPG"));
		logger.debug(SocialWatcherUtil.isImage("3.png"));
		logger.debug(SocialWatcherUtil.isImage("4.PNG"));
		logger.debug(SocialWatcherUtil.isImage("5.gif"));
		logger.debug(SocialWatcherUtil.isImage("6.GIF"));
		logger.debug(SocialWatcherUtil.isImage("7.bmp"));
		logger.debug(SocialWatcherUtil.isImage("8.BMP"));
		logger.debug(SocialWatcherUtil.isImage("9.jpeg"));
		logger.debug(SocialWatcherUtil.isImage("10.JPEG"));
	}
	
	@Test
	public void testGetPropertyNames() {
		SocialWatcherUtil.getPropertyNames(Query.class);
		SocialWatcherUtil.getPropertyNames(QueryResult.class);
	}
	
	@Test
	public void testConvertStringToDate() {
		logger.debug(SocialWatcherUtil.convertStringToDate("07-11-2013"));
	}
	
	@Test
	public void testQueryTextInResultText() {
		
		String queryText="eSuite";
		
		String resultText = "http://ic.longaberger.com/esuite/home/shereefravel?showID=15855043&hostName=Sheree+Fravel&closeDate=06/28/2013";
		logger.debug((resultText.contains(queryText) 
						|| resultText.contains(queryText.toLowerCase())
						|| resultText.contains(queryText.toUpperCase())));
		
	}

}
