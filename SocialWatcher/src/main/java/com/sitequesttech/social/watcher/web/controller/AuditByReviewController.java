package com.sitequesttech.social.watcher.web.controller;



import javax.servlet.http.HttpServletRequest;
import javax.validation.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sitequesttech.social.watcher.domain.entity.QueryResult;
import com.sitequesttech.social.watcher.service.helper.EntityHelper;
import com.sitequesttech.social.watcher.service.helper.QueryResultHelper;
import com.sitequesttech.social.watcher.service.crud.AbstractCrudService;
import com.sitequesttech.social.watcher.service.crud.AuditByReviewService;
import com.sitequesttech.social.watcher.common.support.ReadOperationParams;
import com.sitequesttech.social.watcher.common.support.ReadOperationResults;


/**
 * Audit By Review CRUD controller
 * 
 * @author sivaprasad.vindula@icenris.com
 * 
 */
@Controller
@RequestMapping("/audit/byreview/")
public class AuditByReviewController extends AbstractCrudController<QueryResult> {
	
	private static final Logger logger = Logger
			.getLogger(AuditByReviewController.class);

    @Autowired
    private AuditByReviewService service;

    @Autowired
    private QueryResultHelper helper;

    @Autowired
    private Validator validator;
    
    @Override
    protected String getListPageName() {
    	return "auditByReviewListPage";
    }    
    
    @Override
    protected String getCreatePageName() {
    	return "auditByReviewCreatePage";
    }

    @Override
    protected String getUpdatePageName() {
    	return "auditByReviewUpdatePage";
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
}
