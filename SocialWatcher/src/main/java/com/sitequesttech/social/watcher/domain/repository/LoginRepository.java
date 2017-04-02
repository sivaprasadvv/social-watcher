package com.sitequesttech.social.watcher.domain.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sitequesttech.social.watcher.domain.entity.Login;


public interface LoginRepository extends PagingAndSortingRepository<Login, Long> {

    public Login findByName(String name);
    
}
