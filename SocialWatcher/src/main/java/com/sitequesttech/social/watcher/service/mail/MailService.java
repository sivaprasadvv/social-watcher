package com.sitequesttech.social.watcher.service.mail;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service("MailService")
public class MailService {
	
	private static final Logger logger = Logger
			.getLogger(MailService.class);
	
	@Autowired
    private JavaMailSender mailSender;
     
    @Autowired
    private SimpleMailMessage simpleMailMessage;
    
    @Autowired
    private TemplateEngine templateEngine;
 
    /**
     * This method will send compose and send the message 
     * */
    public void sendMail(String to, String subject, String body) 
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
    
    /**
     * This method will send compose and send the message with attachment 
     * */
    public void sendMailWithAttachement(String from, String to, String subject, String body, String attachmentSourcePath) {

    	MimeMessage message = mailSender.createMimeMessage();
    	 
 	   try{
 		MimeMessageHelper helper = new MimeMessageHelper(message, true);
  
 		helper.setFrom(from);
 		helper.setTo(to);
 		helper.setSubject(subject);
 		helper.setText(body);
  
 		FileSystemResource file = new FileSystemResource(attachmentSourcePath);
 		helper.addAttachment(file.getFilename(), file);
  
 	     }catch (MessagingException e) {
 	    	 throw new MailParseException(e);
 	     }
 	     mailSender.send(message);
    }
 
    /**
     * This method will send a pre-configured message
     * */
    public void sendConfiguredMail(String message) 
    {
        SimpleMailMessage mailMessage = new SimpleMailMessage(simpleMailMessage);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }
    
    public void sendHtmlMail(String searchTerm, String recipient, String from, String to, String subject, Collection<String> results) throws MessagingException {
    	logger.info("sendHtmlMail() - ");
    	
        // Prepare the evaluation context 
        final Context ctx = new Context(); 
        ctx.setVariable("name", "Hello " +recipient); 
        ctx.setVariable("searchTerm", searchTerm);
        ctx.setVariable("results", results); 
        
        // Prepare message using a Spring helper 
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage(); 
        final MimeMessageHelper message =  new MimeMessageHelper(mimeMessage, true /* multipart */, "UTF-8"); 
        message.setSubject(subject); 
        //message.setFrom(from); 
        message.setTo(to); 

        // Create the HTML body using Thymeleaf 
        final String htmlContent = this.templateEngine.process("email.html", ctx); 
        message.setText(htmlContent, true /* isHtml */); 
        
        this.mailSender.send(mimeMessage);
    }

}
