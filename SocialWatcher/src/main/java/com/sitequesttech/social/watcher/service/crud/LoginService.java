package com.sitequesttech.social.watcher.service.crud;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import com.sitequesttech.social.watcher.domain.entity.Login;
import com.sitequesttech.social.watcher.service.helper.EntityHelper;
import com.sitequesttech.social.watcher.service.helper.LoginHelper;
import com.sitequesttech.social.watcher.domain.repository.LoginRepository;


/**
 * Login Service
 * 
 * @author sivaprasad.vindula@icentris.com
 *
 */
@Service
public class LoginService extends AbstractCrudService<Login> {

	@Autowired
    private LoginRepository repository;

	@Autowired
    private LoginHelper helper;

    @Override
    public PagingAndSortingRepository<Login, Long> getRepository() {
        return this.repository;
    }

    @Override
    public EntityHelper<Login> getHelper() {
        return this.helper;
    }
    
    
    public Login getLoginByName(String name) {
    	return repository.findByName(name);
    }

}
