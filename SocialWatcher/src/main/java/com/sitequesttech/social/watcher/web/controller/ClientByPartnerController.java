package com.sitequesttech.social.watcher.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sitequesttech.social.watcher.service.crud.PartnerService;
import com.sitequesttech.social.watcher.web.view.PartnerClient;


@Controller
@RequestMapping("/management/clientbypartner/")
public class ClientByPartnerController {
	
	private static final Logger logger = Logger
			.getLogger(ClientByPartnerController.class);
	
	@Autowired
	PartnerService partnerService;
	
	
	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String loadClientByPartnerPage(Model model) {
		logger.info("Calling loadClientByPartnerPage");
		model.addAttribute("partners", partnerService.findAll());
		return "clientByPartnerPage";
	}
	
	@RequestMapping(value = "/{partnerid}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<PartnerClient> getClientsByPartner(@PathVariable("partnerid") Long partnerid, HttpServletResponse response) {
		logger.info("Calling getClientsByPartner/" +partnerid);
		
		return partnerService.getClientsAssignedToPartner(response, partnerid);
	}
}
