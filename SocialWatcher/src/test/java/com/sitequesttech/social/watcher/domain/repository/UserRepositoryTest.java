package com.sitequesttech.social.watcher.domain.repository;


import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sitequesttech.social.watcher.domain.entity.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={ "classpath:socialwatcher-spring-application-context-test.xml"})
public class UserRepositoryTest {
	
	private static final Logger logger = Logger
			.getLogger(UserRepositoryTest.class);
	
	@Autowired
	UserRepository userRepository;
	
	
	@Test
	public void testFindOne() {
		
		logger.info("In testFind");
		User user = userRepository.findOne(2L);
		logger.debug(user.getUserName());
		
	}
	
}
