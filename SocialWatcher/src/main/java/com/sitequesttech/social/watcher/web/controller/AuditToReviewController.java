package com.sitequesttech.social.watcher.web.controller;


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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sitequesttech.social.watcher.domain.entity.QueryResult;
import com.sitequesttech.social.watcher.service.helper.EntityHelper;
import com.sitequesttech.social.watcher.service.helper.QueryResultHelper;
import com.sitequesttech.social.watcher.service.crud.AbstractCrudService;
import com.sitequesttech.social.watcher.service.crud.AuditToReviewService;
import com.sitequesttech.social.watcher.common.support.ReadOperationParams;
import com.sitequesttech.social.watcher.common.support.ReadOperationResults;


/**
 * Audit To Review CRUD controller
 * 
 * @author sivaprasad.vindula@icenris.com
 * 
 */
@Controller
@RequestMapping("/audit/toreview/")
public class AuditToReviewController extends AbstractCrudController<QueryResult> {
	
	private static final Logger logger = Logger
			.getLogger(AuditToReviewController.class);

    @Autowired
    private AuditToReviewService service;

    @Autowired
    private QueryResultHelper helper;

    @Autowired
    private Validator validator;

    @Override
    protected String getListPageName() {
    	return "auditToReviewListPage";
    }    
    
    @Override
    protected String getCreatePageName() {
    	return "auditToReviewCreatePage";
    }

    @Override
    protected String getUpdatePageName() {
    	return "auditToReviewUpdatePage";
    }

    @Override
    protected AbstractCrudService<QueryResult> getService() {
	return this.service;
    }

    @Override
    protected EntityHelper<QueryResult> getHelper() {
	return this.helper;
    }

    @Override
    protected Validator getValidator() {
	return this.validator;
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ReadOperationResults list(HttpServletRequest request, ReadOperationParams params) {
		logger.info("list() - ");		
		return service.processRead(request, params);
	}
    
    @Override
    @RequestMapping(value = "/{id}", method = PUT, consumes = "application/json")
	public @ResponseBody
	Map<String, ? extends Object> update(@PathVariable Long id,
			@RequestBody QueryResult entity, HttpServletRequest request, HttpServletResponse response) {
		logger.info("update() - ");
		
		if (logger.isDebugEnabled())
			logger.debug(entity.toString());
		
		Set<ConstraintViolation<QueryResult>> failures = getValidator().validate(entity);
		if (!failures.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return getFailureMessages(failures);
		} else {
			return service.processUpdate(request, response, id, entity);
		}
	}    
}
