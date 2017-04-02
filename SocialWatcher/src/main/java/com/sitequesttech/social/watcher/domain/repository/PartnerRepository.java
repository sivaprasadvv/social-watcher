package com.sitequesttech.social.watcher.domain.repository;

import java.util.Collection;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sitequesttech.social.watcher.domain.entity.Client;
import com.sitequesttech.social.watcher.domain.entity.Login;
import com.sitequesttech.social.watcher.domain.entity.Partner;


public interface PartnerRepository extends PagingAndSortingRepository<Partner, Long> {

    public Partner findByPartnerName(String partnerName);
    
    public Partner findByClientsIn(Collection<Client> clients);
    
    public Partner findByLogin(Login login);
    
}
