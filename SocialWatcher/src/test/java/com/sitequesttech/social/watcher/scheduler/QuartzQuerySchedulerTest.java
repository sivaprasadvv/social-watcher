package com.sitequesttech.social.watcher.scheduler;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath:socialwatcher-spring-application-context-test.xml"})
public class QuartzQuerySchedulerTest {
	
	@Autowired
	@Qualifier("QuartzQueryScheduler")
	QuartzQueryScheduler queryQuartzScheduler;
	
	@Test
	public void testExecute() {
		queryQuartzScheduler.execute();
	}
	
}
