package com.sitequesttech.social.watcher.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sitequesttech.social.watcher.domain.entity.Client;
import com.sitequesttech.social.watcher.domain.entity.Partner;
import com.sitequesttech.social.watcher.service.helper.EntityHelper;
import com.sitequesttech.social.watcher.service.helper.PartnerHelper;
import com.sitequesttech.social.watcher.service.crud.AbstractCrudService;
import com.sitequesttech.social.watcher.service.crud.ClientService;
import com.sitequesttech.social.watcher.service.crud.PartnerService;


/**
 * Partner CRUD controller
 * 
 * @author sivaprasad.vindula@icenris.com
 * 
 */
@Controller
@RequestMapping("/management/partner/")
public class PartnerController extends AbstractCrudController<Partner> {
	
	private static final Logger logger = Logger
			.getLogger(PartnerController.class);

    @Autowired
    private PartnerService service;

    @Autowired
    private PartnerHelper helper;

    @Autowired
    private Validator validator;
    
    @Autowired
    ClientService clientService;
    
    
    @Override
    protected String getListPageName() {
    	return "listPartnerPage";
    }

    @Override
    protected String getCreatePageName() {
    	return "createPartnerPage";
    }

    @Override
    protected String getUpdatePageName() {
    	return "updatePartnerPage";
    }
    
    protected String getAssignClientPageName() {
    	return "assignClientPage";
    }

    @Override
    protected AbstractCrudService<Partner> getService() {
	return this.service;
    }

    @Override
    protected EntityHelper<Partner> getHelper() {
	return this.helper;
    }

    @Override
    protected Validator getValidator() {
	return this.validator;
    }      
    
    @ModelAttribute("clients")
	public Collection<Client> populateClients(){
		return clientService.getClientsNotAssignedToPartners();
	}
    
    @Override
    @RequestMapping(value = "/", method = POST, consumes = "application/json")
	public @ResponseBody
	Map<String, ? extends Object> create(@RequestBody Partner entity,
			HttpServletRequest request, HttpServletResponse response) {
    	logger.info("create() - ");
    	
    	if (logger.isDebugEnabled())
    		logger.debug(entity.toString());
    	
		Set<ConstraintViolation<Partner>> failures = getValidator().validate(entity);
		if (!failures.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return getFailureMessages(failures);
		} else {
			return service.processCreate(request, response, entity);
		}
	}
    
    @RequestMapping(value = "assign/client/{id}", method = GET)
	public String assignClient(@PathVariable("id") Long id, Model model) {
		logger.info("assignClient() -  id :" +id);
		Partner entity = getService().getById(id);
		model.addAttribute("partnerObj", entity != null ? getHelper().copyFrom(entity) : getHelper()
			.createEntityInstance());
		return getAssignClientPageName();
	}
    
    @RequestMapping(value = "/{id}", method = PUT, consumes = "application/json")
	public @ResponseBody
	Map<String, ? extends Object> update(@PathVariable Long id,
			@RequestBody Partner entity, HttpServletRequest request, HttpServletResponse response) {
    	logger.info("update() - id :" +id);
    	
    	if (logger.isDebugEnabled())
    		logger.debug(entity.toString());
    	
		Set<ConstraintViolation<Partner>> failures = getValidator().validate(entity);
		if (!failures.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return getFailureMessages(failures);
		} else {			
			return service.processUpdate(request, response, id, entity);
		}
	}
    
    /**
     * This method is used to update assign users to clients
     * @param id
     * @param entity
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "assign/update/{id}", method = PUT, consumes = "application/json")
	public @ResponseBody
	Map<String, ? extends Object> updateAssignedClient(@PathVariable Long id,
			@RequestBody Partner entity, HttpServletRequest request, HttpServletResponse response) {
    	logger.info("updateAssignedClient() - id :" +id);
    	
    	if (logger.isDebugEnabled())
    		logger.debug(entity.toString());
    	
		Set<ConstraintViolation<Partner>> failures = getValidator().validate(entity);
		if (!failures.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return getFailureMessages(failures);
		} else {			
			return service.processUpdateAssignedClient(request, response, id, entity);
		}
	}
    
}
