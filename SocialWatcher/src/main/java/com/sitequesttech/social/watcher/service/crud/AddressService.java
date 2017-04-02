package com.sitequesttech.social.watcher.service.crud;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import com.sitequesttech.social.watcher.domain.entity.Address;
import com.sitequesttech.social.watcher.service.helper.AddressHelper;
import com.sitequesttech.social.watcher.service.helper.EntityHelper;
import com.sitequesttech.social.watcher.domain.repository.AddressRepository;


/**
 * Address Service
 * 
 * @author sivaprasad.vindula@icentris.com
 *
 */
@Service
public class AddressService extends AbstractCrudService<Address> {

	@Autowired
    private AddressRepository repository;

	@Autowired
    private AddressHelper helper;

    
    @Override
    public PagingAndSortingRepository<Address, Long> getRepository() {
        return this.repository;
    }

    @Override
    public EntityHelper<Address> getHelper() {
        return this.helper;
    }   

}
