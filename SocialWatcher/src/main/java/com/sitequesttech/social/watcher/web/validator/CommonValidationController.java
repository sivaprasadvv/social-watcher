package com.sitequesttech.social.watcher.web.validator;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sitequesttech.social.watcher.domain.entity.Client;
import com.sitequesttech.social.watcher.domain.repository.ClientRepository;
import com.sitequesttech.social.watcher.domain.repository.LoginRepository;
import com.sitequesttech.social.watcher.domain.repository.PartnerRepository;
import com.sitequesttech.social.watcher.domain.repository.QueryRepository;
import com.sitequesttech.social.watcher.domain.repository.RoleRepository;


/**
 * A general purpose controller used to validate fields on a form.
 * 
 * @author sivaprasad.vindula@icentris.com
 */
@Controller
@RequestMapping("/validator/*")
public class CommonValidationController {
    private static final Logger logger = Logger.getLogger(CommonValidationController.class);

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    ClientRepository clientRepository;
    
    @Autowired
    PartnerRepository partnerRepository;
    
    @Autowired
    QueryRepository queryRepository;
    
    /**
     * Checking the existency of an email
     * 
     * @param email
     * @return "true" or "false"
     */
    @RequestMapping(value = "/checkemail", params = "name")
    public @ResponseBody
    String checkMail(@RequestParam String name) {
	logger.debug("checking name : " + name);
	return (loginRepository.findByName(name) != null) ? "false" : "true";
    }

    /**
     * Checking the existency of a role name
     * 
     * @param roleName
     * @return "true" or "false"
     */
    @RequestMapping(value = "/checkrolename", params = "roleName")
    public @ResponseBody
    String checkRoleName(@RequestParam String roleName) {
	logger.debug("checking role name : " + roleName);
	return (roleRepository.findByRoleName(roleName) != null) ? "false" : "true";
    }   
    
    /**
     * Checking the existing of a client name
     * 
     * @param clientName
     * @return "true" or "false"
     */
    @RequestMapping(value = "/checkclientname", params = "clientName")
    public @ResponseBody
    String checkClientName(@RequestParam String clientName) {
    	logger.debug("checking client name : " + clientName);
    	return (clientRepository.findByClientName(clientName) != null) ? "false" : "true";
    }
    
    /**
     * Checking the existing of a partner name
     * 
     * @param partnerName
     * @return "true" or "false"
     */
    @RequestMapping(value = "/checkpartnername", params = "partnerName")
    public @ResponseBody
    String checkPartnerName(@RequestParam String partnerName) {
    	logger.debug("checking partner name : " + partnerName);
    	return (partnerRepository.findByPartnerName(partnerName) != null) ? "false" : "true";
    }
    
    @RequestMapping(value = "/checkalreadyexists", params = {"queryText", "clientId", "socialmediaId"})
    public @ResponseBody
    String checkAlreadyExists(@RequestParam (value="queryText") String queryText, @RequestParam (value="clientId") String clientId, @RequestParam (value="socialmediaId") String socialmediaId) {
    	logger.debug("checking Query Text/Client Id/Social Media Id : " + queryText+"/"+clientId+"/"+socialmediaId);
    	Client client = clientRepository.findOne(Long.valueOf(clientId));
    	return (queryRepository.findByQueryTextAndClientAndSocialMediaId(queryText, client,Long.valueOf(socialmediaId)) != null) ? "false" : "true";
    }
}
