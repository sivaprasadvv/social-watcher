package com.sitequesttech.social.watcher.service.crud;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sitequesttech.social.watcher.domain.entity.Client;
import com.sitequesttech.social.watcher.domain.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath:socialwatcher-spring-application-context-test.xml"})
public class ClientServiceTest {
	
	@Autowired
	ClientService clientService;
	
	@Autowired
	UserService userService;
	
	@Test
	public void testCreate() {
		
		User user = userService.getById(1L);
		
		Client client = new Client();
		client.setClientName("sitequest");
		client.setIsEnabled(true);
		client.setCreatedDate(new Date());
		client.setCreatedBy(user.getId());
		clientService.create(client);
	}

}
