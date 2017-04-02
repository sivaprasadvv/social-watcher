package com.sitequesttech.social.watcher.common.support;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={ "classpath:socialwatcher-spring-application-context-test.xml"})
public class SocialWatcherConstantsTest {
	
	private static final Logger logger = Logger
			.getLogger(SocialWatcherConstantsTest.class);
	
	
	@Test
	public void testConstants() {
		logger.debug(SocialWatcherConstants.FACEBOOK_IMAGE_URL);
	}

}
