package com.sitequesttech.social.watcher.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sitequesttech.social.watcher.domain.entity.ApplicationProperties;
import com.sitequesttech.social.watcher.service.helper.ApplicationPropertiesHelper;
import com.sitequesttech.social.watcher.service.helper.EntityHelper;
import com.sitequesttech.social.watcher.service.crud.AbstractCrudService;
import com.sitequesttech.social.watcher.service.crud.ApplicationPropertiesService;


/**
 * Application Properties CRUD Controller
 * 
 * @author sivaprasad.vindula@icentris.com
 * 
 */

@Controller
@RequestMapping("/management/properties/")
public class ApplicationPropertiesController extends AbstractCrudController<ApplicationProperties> {
	
	private static final Logger logger = Logger
			.getLogger(ApplicationPropertiesController.class);

    @Autowired
    private ApplicationPropertiesService service;

    @Autowired
    private ApplicationPropertiesHelper helper;

    @Autowired
    private Validator validator;
    
    @Override
    protected String getListPageName() {
	return "applicationPropertiesPage";
    }

    @Override
    protected String getCreatePageName() {
	return "createApplicationPropertyPage";
    }

    @Override
    protected String getUpdatePageName() {
	return "updateApplicationPropertyPage";
    }

    @Override
    protected AbstractCrudService<ApplicationProperties> getService() {
	return this.service;
    }

    @Override
    protected EntityHelper<ApplicationProperties> getHelper() {
	return this.helper;
    }

    @Override
    protected Validator getValidator() {
	return this.validator;
    }
    
    @Override
    @RequestMapping(value = "/", method = POST, consumes = "application/json")
	public @ResponseBody
	Map<String, ? extends Object> create(@RequestBody ApplicationProperties entity,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("create() - ");
		
		if (logger.isDebugEnabled())
			logger.debug(entity.toString());
		
		Set<ConstraintViolation<ApplicationProperties>> failures = getValidator().validate(entity);
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
			@RequestBody ApplicationProperties entity, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("update() - ");
		
		if (logger.isDebugEnabled())
			logger.debug(entity.toString());
		
		Set<ConstraintViolation<ApplicationProperties>> failures = getValidator().validate(entity);
		if (!failures.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return getFailureMessages(failures);
		} else {
			return service.processUpdate(request, response, id, entity);
		}
	}
}
