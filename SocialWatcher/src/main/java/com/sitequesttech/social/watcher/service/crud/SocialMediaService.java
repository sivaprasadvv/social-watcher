package com.sitequesttech.social.watcher.service.crud;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.sitequesttech.social.watcher.domain.entity.Login;
import com.sitequesttech.social.watcher.domain.entity.SocialMedia;
import com.sitequesttech.social.watcher.domain.repository.SocialMediaRepository;
import com.sitequesttech.social.watcher.service.helper.EntityHelper;
import com.sitequesttech.social.watcher.service.helper.SocialMediaHelper;
import com.sitequesttech.social.watcher.web.support.SocialWatcherContextUtil;


/**
 * Social Media Service
 * 
 * @author sivaprasad.vindula@icentris.com
 *
 */
@Service
public class SocialMediaService extends AbstractCrudService<SocialMedia> {

	private static final Logger logger = Logger
			.getLogger(SocialMediaService.class);
	
	@Autowired
    private SocialMediaRepository repository;
	
	 @Autowired
	 private SocialMediaHelper helper;
	 
	 @Autowired
	 private SocialWatcherContextUtil socialWatcherContextUtil;    
	    

    @Override
    public JpaRepository<SocialMedia, Long> getRepository() {
        return this.repository;
    }

	@Override
	public EntityHelper<SocialMedia> getHelper() {
		return this.helper;
	}

    public SocialMedia getBySocialMediaName(String name) {
    	return repository.findByName(name);
    }
    
    public List<SocialMedia> getActiveSocialMedia() {
    	return repository.findByIsEnabledIsTrue();
    }
    
    public Map<String, ? extends Object> processCreate(HttpServletRequest request, HttpServletResponse response, SocialMedia entity) {
    	
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
    		Long id, SocialMedia entity) {
    	
    	if (logger.isDebugEnabled())
    		logger.debug("processUpdate() - ");    	

		Login loginUser = socialWatcherContextUtil.getLoginUser(request);
		entity.setCreatedBy(loginUser.getId());
		entity.setCreatedDate(new Date());
		
		SocialMedia entityToUpdate = getById(id);
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
