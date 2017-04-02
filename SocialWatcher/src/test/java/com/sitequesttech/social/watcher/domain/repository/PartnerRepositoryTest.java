package com.sitequesttech.social.watcher.domain.repository;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sitequesttech.social.watcher.domain.entity.Partner;
import com.sitequesttech.social.watcher.domain.entity.Role;
import com.sitequesttech.social.watcher.domain.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={ "classpath:socialwatcher-spring-application-context-test.xml"})
public class PartnerRepositoryTest {
	
	private static final Logger logger = Logger
			.getLogger(PartnerRepositoryTest.class);
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PartnerRepository partnerRepository;
	
	
	@Test
	public void testCreateFindByUser() {
		logger.info("in testCreateFindByUser");
		User foundUser = userRepository.findOne(1L);
        
        Partner parter = new Partner();
        parter.setPartnerName("sitequest");
        parter.setCreatedBy(foundUser.getId());
        parter.setCreatedDate(new Date());
        this.partnerRepository.save(parter);
		
		
	}
	
	 private Role getRole(final String roleId) {
	        Role result = roleRepository.findByRoleName(roleId);
	        if (result == null) {
	            result = new Role();
	            result.setRoleName(roleId);
	            result.setCreatedDate(new Date());
	            result.setCreatedBy(1L);
	            roleRepository.save(result);
	        }
	        return result;
	    }

}
