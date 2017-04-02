package com.sitequesttech.social.watcher.service.mail;


import java.util.ArrayList;
import java.util.Collection;

import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath:socialwatcher-spring-application-context-test.xml"})
public class MailServiceTest {
	
	@Autowired
	MailService mailService;
	
	
	@Test
	public void testSendMail() {		
		mailService.sendMail("icentris.sivaprasad@gmail.com", "test", "This is test mail");			
	}
	
	@Test
	public void testSendMailWithAttachment() {
		mailService.sendMailWithAttachement("icentris.sivaprasad@gmail.com", "sivaprasad.icentris@gmail.com", "Test Attachment", "Hi \n\nThis is test attachment mail", "/home/sivaprasad/socialwatcher.pdf");
	}
	
	@Test
	public void testSendConfiguredMail() {
		mailService.sendConfiguredMail("Hello test social watcher");
	}
	
	@Test
	public void testSendHtmlMail() throws MessagingException {		
		Collection<String> results = new ArrayList<String>();
		results.add("This is test mail");
		mailService.sendHtmlMail("test", "sivaprasad", null, "icentris.sivaprasad@gmail.com", "test", results);			
	}

}
