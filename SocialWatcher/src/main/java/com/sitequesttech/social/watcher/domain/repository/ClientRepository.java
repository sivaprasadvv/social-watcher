package com.sitequesttech.social.watcher.domain.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sitequesttech.social.watcher.domain.entity.Client;
import com.sitequesttech.social.watcher.domain.entity.Login;
import com.sitequesttech.social.watcher.domain.entity.User;


public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {

	public Client findByClientName(String clientName);
	
	public List<Client> findByIsEnabledIsTrue();
	
	public List<Client> findByIsAssignedIsNull();
	
	public Client findByUsersIn(Collection<User> users);
	
	public Client findByLogin(Login login);
	
	Page<Client> findByClientNameIn(Collection<String> clients, Pageable pageable);
	
}
