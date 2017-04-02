package com.sitequesttech.social.watcher.service.crud;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitequesttech.social.watcher.domain.entity.ApplicationProperties;
import com.sitequesttech.social.watcher.domain.entity.Login;
import com.sitequesttech.social.watcher.domain.entity.Role;
import com.sitequesttech.social.watcher.domain.repository.ApplicationPropertiesRepository;
import com.sitequesttech.social.watcher.domain.repository.LoginRepository;
import com.sitequesttech.social.watcher.domain.repository.RoleRepository;

/**
 * Data initialization service
 * 
 * @author sivaprasad.vindula@icentris.com
 *
 */
@Service("initDataService")
public class InitDataService {

    private static final Logger log = Logger.getLogger(InitDataService.class);

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    ApplicationPropertiesRepository propertiesRepository;
    
    @Autowired
	private Properties socialWatcherPropertiesBean;
    
    public void init() {
    	
    	log.debug("Total role entities found :" +roleRepository.count());
    	if (roleRepository.count() == 0) {
    		
    		List<Role> roles = new ArrayList<Role>();
    		Role role = null;
    		
    		role = new Role();    		
    		role.setRoleName("admin");
    		role.setCreatedDate(new Date());
    		role.setCreatedBy(1L);
    		roles.add(role);
    		
    		role = new Role();    		
    		role.setRoleName("partner");
    		role.setCreatedDate(new Date());
    		role.setCreatedBy(1L);
    		roles.add(role);
    		
    		role = new Role();    		
    		role.setRoleName("client");
    		role.setCreatedDate(new Date());
    		role.setCreatedBy(1L);
    		roles.add(role);
    		
    		role = new Role();    		
    		role.setRoleName("user");
    		role.setCreatedDate(new Date());
    		role.setCreatedBy(1L);
    		roles.add(role);
    		
            roleRepository.save(roles);
    	}
    	
    	log.debug("Total login entities found :" +loginRepository.count());
    	if (loginRepository.count() == 0) {
	        /* A user with admin right */
	        Role adminRole = getRole("admin");
	
	        Login admin = new Login();
	        admin.setName("admin@sitequest.com");
	        admin.setPassword(DigestUtils.md5Hex("admin"));
	        admin.setIsEnabled(true);
	        admin.setCreatedDate(new Date());
	        admin.setCreatedBy(1L);
	        this.loginRepository.save(admin);
	        admin.getRoles().add(adminRole);
	        this.loginRepository.save(admin);       
	       
    	}
    	
    	log.debug("Total application properties found :" +propertiesRepository.count());
    	/*if (propertiesRepository.count() == 0) {
    		List<ApplicationProperties> entities = new ArrayList<ApplicationProperties>();
    		for(Object key : socialWatcherPropertiesBean.keySet()) {
    			ApplicationProperties property = new ApplicationProperties();
    			property.setPropertyName((String)key);
    			property.setPropertyValue(socialWatcherPropertiesBean.getProperty((String)key));
    			property.setCreatedBy(1L);
    			property.setCreatedDate(new Date());
    			entities.add(property);
    		}
    		propertiesRepository.save(entities);
    	}*/
    	
    }

    private Role getRole(final String roleId) {
        Role result = roleRepository.findByRoleName(roleId);
        if (result == null) {
            result = new Role();
            result.setRoleName(roleId);
            result.setCreatedDate(new Date());
            result.setCreatedBy(1L);
            roleRepository.save(result);
        }
        return result;
    }
}
