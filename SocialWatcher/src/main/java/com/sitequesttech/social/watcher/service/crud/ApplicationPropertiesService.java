package com.sitequesttech.social.watcher.service.crud;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import com.sitequesttech.social.watcher.domain.entity.ApplicationProperties;
import com.sitequesttech.social.watcher.domain.entity.Login;
import com.sitequesttech.social.watcher.service.helper.ApplicationPropertiesHelper;
import com.sitequesttech.social.watcher.service.helper.EntityHelper;
import com.sitequesttech.social.watcher.web.support.SocialWatcherContextUtil;
import com.sitequesttech.social.watcher.domain.repository.ApplicationPropertiesRepository;


/**
 * Application Properties Service
 * 
 * @author sivaprasad.vindula@icentris.com
 *
 */
@Service
public class ApplicationPropertiesService extends AbstractCrudService<ApplicationProperties> {
	
	private static final Logger logger = Logger
			.getLogger(ApplicationPropertiesService.class);

	@Autowired
    private ApplicationPropertiesRepository repository;

	@Autowired
    private ApplicationPropertiesHelper helper;
	
	@Autowired
    SocialWatcherContextUtil socialWatcherContextUtil;

    @Override
    public PagingAndSortingRepository<ApplicationProperties, Long> getRepository() {
        return this.repository;
    }

    @Override
    public EntityHelper<ApplicationProperties> getHelper() {
        return this.helper;
    }
    
    public List<ApplicationProperties> getApplicationProperties() {
    	return repository.findAll();
    }
    
    public ApplicationProperties getApplicationPropertyByPropertyName(String propertyName) {
    	return repository.findByPropertyName(propertyName);
    }
    
    public Map<String, ? extends Object> processCreate(HttpServletRequest request, HttpServletResponse response, ApplicationProperties entity) {
    	
    	if (logger.isDebugEnabled())
    		logger.debug("processCreate() - ");
    	
    	Login loginUser = socialWatcherContextUtil.getLoginUser(request);
		
    	entity.setCreatedBy(loginUser.getId());
    	entity.setCreatedDate(new Date());
    	
		create(entity);
		response.setStatus(HttpServletResponse.SC_CREATED);
		return null;    	
    }
    
    public Map<String, ? extends Object> processUpdate(HttpServletRequest request, HttpServletResponse response, 
    		Long id, ApplicationProperties entity) {
    	
    	if (logger.isDebugEnabled())
    		logger.debug("processUpdate() - ");
    	
    	Login loginUser = socialWatcherContextUtil.getLoginUser(request);
		
    	entity.setModifiedBy(loginUser.getId());
    	entity.setModifiedDate(new Date());
    	
    	ApplicationProperties entityToUpdate = getById(id);
		if (entityToUpdate != null) {
			entityToUpdate = getHelper().updateFrom(entity,
					entityToUpdate);
			update(entityToUpdate);
			response.setStatus(HttpServletResponse.SC_OK);
			return null;
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
    	
    }

}
