package com.sitequesttech.social.watcher.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sitequesttech.social.watcher.domain.entity.ApplicationProperties;
import com.sitequesttech.social.watcher.service.crud.ApplicationPropertiesService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={ "classpath:socialwatcher-spring-application-context-test.xml"})
public class SocialWatcherPropertiesTest {
	
	private static final Logger logger = Logger
			.getLogger(SocialWatcherPropertiesTest.class);
	
	@Autowired
	private Properties socialWatcherPropertiesBean;
	
	@Autowired
	ApplicationPropertiesService propertiesService;
	
	@Test
	public void testProperties() {
		logger.debug(socialWatcherPropertiesBean.get("application.host.name"));
		List<ApplicationProperties> entities = new ArrayList<ApplicationProperties>();
		for(Object key : socialWatcherPropertiesBean.keySet()) {
			//logger.debug(key);
			ApplicationProperties property = new ApplicationProperties();
			property.setPropertyName((String)key);
			property.setPropertyValue(socialWatcherPropertiesBean.getProperty((String)key));
			property.setCreatedBy(1L);
			property.setCreatedDate(new Date());
			entities.add(property);
		}
		propertiesService.create(entities);
	}

}
