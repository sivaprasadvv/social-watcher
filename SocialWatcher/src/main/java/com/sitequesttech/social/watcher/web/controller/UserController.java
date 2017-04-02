package com.sitequesttech.social.watcher.web.controller;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sitequesttech.social.watcher.common.support.ReadOperationParams;
import com.sitequesttech.social.watcher.common.support.ReadOperationResults;
import com.sitequesttech.social.watcher.domain.entity.Role;
import com.sitequesttech.social.watcher.domain.entity.User;
import com.sitequesttech.social.watcher.service.helper.EntityHelper;
import com.sitequesttech.social.watcher.service.helper.UserHelper;
import com.sitequesttech.social.watcher.service.crud.AbstractCrudService;
import com.sitequesttech.social.watcher.service.crud.RoleService;
import com.sitequesttech.social.watcher.service.crud.UserService;


/**
 * User CRUD controller
 * 
 * @author sivaprasad.vindula@icenris.com
 * 
 */
@Controller
@RequestMapping("/management/users/")
public class UserController extends AbstractCrudController<User> {
	
	private static final Logger logger = Logger
			.getLogger(UserController.class);

    @Autowired
    private UserService service;

    @Autowired
    private UserHelper helper;

    @Autowired
    private Validator validator;
    
    @Autowired
    private RoleService roleService;
    
    @Override
    protected String getListPageName() {
    	return "usersPage";
    }

    @Override
    protected String getCreatePageName() {
    	return "createUserPage";
    }

    @Override
    protected String getUpdatePageName() {
    	return "updateUserPage";
    }

    @Override
    protected AbstractCrudService<User> getService() {
    	return this.service;
    }

    @Override
    protected EntityHelper<User> getHelper() {
    	return this.helper;
    }

    @Override
    protected Validator getValidator() {
    	return this.validator;
    }
    
    @ModelAttribute("roles")
	public Collection<Role> populateRoles(){
		return roleService.getRoles();
	}
    
    @Override
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
   	public @ResponseBody
   	ReadOperationResults list(HttpServletRequest request, ReadOperationParams params) {
   		logger.debug("list() - ");
   		return service.processRead(request, params);   		
   	}
    
    @Override
	@RequestMapping(value = "/", method = POST, consumes = "application/json")
	public @ResponseBody
	Map<String, ? extends Object> create(@RequestBody User entity,
			HttpServletRequest request, HttpServletResponse response) {
		logger.debug("Creating User entity: " + entity.toString());
		Set<ConstraintViolation<User>> failures = getValidator().validate(entity);
		if (!failures.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return getFailureMessages(failures);
		} else {
			return service.processCreate(request, response, entity);
		}
	}
    
    @Override
    @RequestMapping(value = "/{id}", method = PUT, consumes = "application/json")
	public @ResponseBody
	Map<String, ? extends Object> update(@PathVariable Long id,
			@RequestBody User entity, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("Updating User entity: " + entity.toString());
		Set<ConstraintViolation<User>> failures = getValidator().validate(entity);
		if (!failures.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return getFailureMessages(failures);
		} else {
			return service.processUpdate(request, response, id, entity);
		}
	}

}
