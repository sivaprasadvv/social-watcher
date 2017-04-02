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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sitequesttech.social.watcher.common.support.ReadOperationParams;
import com.sitequesttech.social.watcher.common.support.ReadOperationResults;
import com.sitequesttech.social.watcher.domain.entity.Client;
import com.sitequesttech.social.watcher.domain.entity.User;
import com.sitequesttech.social.watcher.service.helper.ClientHelper;
import com.sitequesttech.social.watcher.service.helper.EntityHelper;
import com.sitequesttech.social.watcher.service.crud.AbstractCrudService;
import com.sitequesttech.social.watcher.service.crud.ClientService;
import com.sitequesttech.social.watcher.service.crud.UserService;


/**
 * Client CRUD controller
 * 
 * @author sivaprasad.vindula@icenris.com
 * 
 */
@Controller
@RequestMapping("/management/clients/")
public class ClientController extends AbstractCrudController<Client> {
	
	private static final Logger logger = Logger
			.getLogger(ClientController.class);

    @Autowired
    private ClientService service;

    @Autowired
    private ClientHelper helper;

    @Autowired
    private Validator validator;
    
    @Autowired
    private UserService userService;
    
    
    @Override
    protected String getListPageName() {
    	logger.debug("In getListPageName");
	return "clientsPage";
    }

    @Override
    protected String getCreatePageName() {
	return "createClientPage";
    }

    @Override
    protected String getUpdatePageName() {
	return "updateClientPage";
    }
    
    protected String getAssignUserPageName() {
    	return "assignUserPage";
    }

    @Override
    protected AbstractCrudService<Client> getService() {
	return this.service;
    }

    @Override
    protected EntityHelper<Client> getHelper() {
	return this.helper;
    }

    @Override
    protected Validator getValidator() {
	return this.validator;
    }
    
    @ModelAttribute("users")
	public Collection<User> populateUsers(){
		return userService.getUsersNotAssignedToClients();
	}
    
    @Override
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ReadOperationResults list(HttpServletRequest request, ReadOperationParams params) {
		logger.info("list() - ");
		
		return service.processRead(request, params);
	}
    
    @Override
    @RequestMapping(value = "/", method = POST, consumes = "application/json")
	public @ResponseBody
	Map<String, ? extends Object> create(@RequestBody Client entity,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("create() - ");
		
		if (logger.isDebugEnabled())
			logger.debug(entity.toString());
		
		Set<ConstraintViolation<Client>> failures = getValidator().validate(entity);
		if (!failures.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return getFailureMessages(failures);
		} else {
	    	return service.processCreate(request, response, entity);
		}
	}
    
    @RequestMapping(value = "assign/user/{id}", method = GET)
    public String assignUser(@PathVariable("id") Long id, Model model) {
    	logger.info("assignUser() -  id :" +id);
		Client entity = getService().getById(id);
		model.addAttribute("clientObj", entity != null ? getHelper().copyFrom(entity) : getHelper()
			.createEntityInstance());
		return getAssignUserPageName();
    }
    
    @RequestMapping(value = "/{id}", method = PUT, consumes = "application/json")
	public @ResponseBody
	Map<String, ? extends Object> update(@PathVariable Long id,
			@RequestBody Client entity, HttpServletRequest request, HttpServletResponse response) {
		logger.info("Update() - ");
		
		if (logger.isDebugEnabled())
			logger.debug(entity.toString());
		
		Set<ConstraintViolation<Client>> failures = getValidator().validate(entity);
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
	Map<String, ? extends Object> updateAssignedUser(@PathVariable Long id,
			@RequestBody Client entity, HttpServletRequest request, HttpServletResponse response) {
		logger.info("updateAssignedUser() - ");
		
		if (logger.isDebugEnabled())
			logger.debug(entity.toString());
		
		Set<ConstraintViolation<Client>> failures = getValidator().validate(entity);
		if (!failures.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return getFailureMessages(failures);
		} else {
			return service.processUpdateAssignedUser(request, response, id, entity);
		}
	}
}
