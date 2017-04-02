package com.sitequesttech.social.watcher.domain.repository;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sitequesttech.social.watcher.domain.entity.Client;
import com.sitequesttech.social.watcher.domain.entity.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={ "classpath:socialwatcher-spring-application-context-test.xml"})
public class ClientRepositoryTest {
	
	private static final Logger logger = Logger
			.getLogger(ClientRepositoryTest.class);
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	PartnerRepository partnerRepository;
	
	@Autowired
	UserRepository userRepository;
	
	
	@Test
	public void testCreate() {
		
		logger.info("In testCreate");
		
		User user = userRepository.findOne(1L);
		
		Client client = new Client();
		client.setClientName("apple");
		client.setIsEnabled(true);
		//client.setPartner(partnerRepository.findOne(1L));
		client.setCreatedBy(user.getId());
		client.setCreatedDate(new Date());
		client.setUsers(null);
		clientRepository.save(client);
	}
	
	
	@Test
	public void testFindByUsersIn(){
		User user = userRepository.findOne(1L);
		Collection<User> users = new ArrayList<User>();
		users.add(user);
		Client client = clientRepository.findByUsersIn(users);
		logger.debug(client.getId()+"/"+client.getClientName());
	}
	
}
