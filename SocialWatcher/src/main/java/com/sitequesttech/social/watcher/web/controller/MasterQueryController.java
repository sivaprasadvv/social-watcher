package com.sitequesttech.social.watcher.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sitequesttech.social.watcher.domain.entity.Client;
import com.sitequesttech.social.watcher.domain.entity.Query;
import com.sitequesttech.social.watcher.domain.entity.SocialMedia;
import com.sitequesttech.social.watcher.service.helper.EntityHelper;
import com.sitequesttech.social.watcher.service.helper.QueryHelper;
import com.sitequesttech.social.watcher.service.crud.AbstractCrudService;
import com.sitequesttech.social.watcher.service.crud.QueryService;
import com.sitequesttech.social.watcher.service.crud.SocialMediaService;

@Controller
@RequestMapping("/masterquery/")
public class MasterQueryController extends AbstractCrudController<Query> {
	
	private static final Logger logger = Logger
			.getLogger(MasterQueryController.class);
	
	@Autowired
	private QueryService service;
	
	@Autowired
	private QueryHelper helper;
	
	@Autowired
    private Validator validator;
	
	@Autowired
	private SocialMediaService socialMediaService;
	
	@Override
    protected String getListPageName() {
    	logger.info("getListPageName() - ");
	return "masterQueryCreatePage";
    }
 
 	@RequestMapping(value = "mlist", method = RequestMethod.GET)
    public String getMasterQueryPage(HttpServletRequest request) {
    	logger.info("getMasterQueryPage() - ");		    	
    	service.processReadMasterQueryPage(request);	    	
    	return getListPageName();
    }

    @Override
    protected String getCreatePageName() {
    	return "masterQueryCreatePage";
    }
	    
    @RequestMapping(value = "mcreate", method = GET)
    public String getMasterCreatePage(HttpServletRequest request) {
    	logger.info("getMasterCreatePage() - ");	    	
    	service.processCreateMasterQueryPage(request);			
		return getCreatePageName();
    }

    @Override
    protected String getUpdatePageName() {
	return "updateQueryPage";
    }

    @Override
    protected AbstractCrudService<Query> getService() {
	return this.service;
    }

    @Override
    protected EntityHelper<Query> getHelper() {
	return this.helper;
    }

    @Override
    protected Validator getValidator() {
	return this.validator;
    }
    
    @RequestMapping(value = "/", method = POST, consumes = "application/json")
	public @ResponseBody
	Map<String, ? extends Object> create(@RequestBody Query entity,
			HttpServletRequest request, HttpServletResponse response) {
    	logger.info("create() - ");
    	
    	if (logger.isDebugEnabled())
    		logger.debug(entity.toString());
	
		return service.processCreate(entity, request, response);
	}
    
    @ModelAttribute("socialmedias")
    public Collection<SocialMedia> populateSocialMedia() {
    	logger.info("populateSocialMedia() - ");
    	return (Collection<SocialMedia>) socialMediaService.getActiveSocialMedia();
    }
    
    @ModelAttribute("clients")
    public Collection<Client> populateClient(HttpServletRequest request) {
    	logger.info("populateClient() - ");
    	
    	return service.fetchClients(request);
    }
}
