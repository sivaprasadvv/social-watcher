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

import com.sitequesttech.social.watcher.domain.entity.Login;
import com.sitequesttech.social.watcher.domain.entity.Role;
import com.sitequesttech.social.watcher.service.helper.EntityHelper;
import com.sitequesttech.social.watcher.service.helper.RoleHelper;
import com.sitequesttech.social.watcher.web.support.SocialWatcherContextUtil;
import com.sitequesttech.social.watcher.domain.repository.RoleRepository;


/**
 * Role Service
 * 
 * @author sivaprasad.vindula@icentris.com
 *
 */
@Service
public class RoleService extends AbstractCrudService<Role> {
	
	private static final Logger logger = Logger
			.getLogger(RoleService.class);

	@Autowired
    private RoleRepository repository;

	@Autowired
    private RoleHelper helper;

	@Autowired
    private SocialWatcherContextUtil socialWatcherContextUtil;
	
    @Override
    public PagingAndSortingRepository<Role, Long> getRepository() {
        return this.repository;
    }

    @Override
    public EntityHelper<Role> getHelper() {
        return this.helper;
    }
    
    public List<Role> getRoles() {
    	return repository.findAll();
    }
    
    public Role getRoleByName(String name) {
    	return repository.findByRoleName(name);
    }

    public Map<String, ? extends Object> processCreate(HttpServletRequest request, HttpServletResponse response, Role entity) {
    	
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
    		Long id, Role entity) {
    	
    	if (logger.isDebugEnabled())
    		logger.debug("processUpdate() - ");
    	
    	Login loginUser = socialWatcherContextUtil.getLoginUser(request);
		
    	entity.setModifiedBy(loginUser.getId());
    	entity.setModifiedDate(new Date());
    	
		Role entityToUpdate = getById(id);
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
