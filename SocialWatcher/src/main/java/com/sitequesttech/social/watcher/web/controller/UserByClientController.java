package com.sitequesttech.social.watcher.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sitequesttech.social.watcher.common.support.SocialWatcherUtil;
import com.sitequesttech.social.watcher.domain.entity.Client;
import com.sitequesttech.social.watcher.domain.entity.Login;
import com.sitequesttech.social.watcher.domain.entity.Partner;
import com.sitequesttech.social.watcher.domain.entity.User;
import com.sitequesttech.social.watcher.service.crud.ClientService;
import com.sitequesttech.social.watcher.service.crud.PartnerService;
import com.sitequesttech.social.watcher.web.support.SocialWatcherContextUtil;
import com.sitequesttech.social.watcher.web.view.ClientUser;


@Controller
@RequestMapping("/management/userbyclient/")
public class UserByClientController {
	
	private static final Logger logger = Logger
			.getLogger(UserByClientController.class);
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private PartnerService partnerService;
	
	@Autowired
	private SocialWatcherContextUtil socialWatcherContextUtil;
	
	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String loadClientByUserPage(Model model, HttpServletRequest request) {
		logger.info("Calling loadClientByUserPage");
		
		Login loginUser = socialWatcherContextUtil.getLoginUser(request);
    	String role = socialWatcherContextUtil.getLoginUserRole(request);
    	
    	if (SocialWatcherUtil.isNotEmptyAndNotNullString(role) && "admin".equals(role)) {
    		model.addAttribute("clients", clientService.findAll());
    	}
    	
    	if (SocialWatcherUtil.isNotEmptyAndNotNullString(role) && "partner".equals(role)) {
    		Partner partner = partnerService.getByLogin(loginUser);
    		Set<Client> clients = partner.getClients();
    		model.addAttribute("clients", clients);
    	}
    	
		
		return "clientUserPage";
	}
	
	@RequestMapping(value = "/{clientid}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<ClientUser> getUsersByClient(@PathVariable("clientid") Long clientId, HttpServletResponse response) {
		logger.info("Calling getUsersByClient/" +clientId);
		
		List<ClientUser> list = new ArrayList<ClientUser>();
		Client client = clientService.getById(clientId);
		Set<User> users = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Client id/name:" +client.getId()+"/"+client.getClientName());
			if (null != client.getUsers()) {
				logger.debug("Number of users found for " +client.getClientName()+ " are " +client.getUsers().size());
				users = client.getUsers();
			}
		}
		if (null != users && users.size() > 0) {
			if (logger.isDebugEnabled())
				logger.debug(users.size());
			for (User user : users) {
				list.add(new ClientUser(user.getUserName()));
			}
			return list;
		} else {
			list.add(new ClientUser("No users found"));
		}
		return list;
	}
}
