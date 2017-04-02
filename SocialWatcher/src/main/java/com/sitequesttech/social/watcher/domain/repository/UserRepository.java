package com.sitequesttech.social.watcher.domain.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sitequesttech.social.watcher.domain.entity.Login;
import com.sitequesttech.social.watcher.domain.entity.User;


public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    public User findByUserName(String username);
    
    public List<User> findByIsAssignedIsNull();
    
    public User getByLogin(Login login);
    
    Page<User> findByUserNameIn(Collection<String> users, Pageable pageable);
    
}
