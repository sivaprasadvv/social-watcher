package com.sitequesttech.social.watcher.service.helper;


import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sitequesttech.social.watcher.domain.entity.Client;
import com.sitequesttech.social.watcher.domain.entity.Login;
import com.sitequesttech.social.watcher.domain.entity.User;
import com.sitequesttech.social.watcher.common.support.SocialWatcherUtil;
import com.sitequesttech.social.watcher.common.support.ValueGenerator;

@Component
public class ClientHelper implements EntityHelper<Client> {
	
	private static final Logger logger = Logger
			.getLogger(ClientHelper.class);
	
	@Autowired
	private LoginHelper loginHelper; 
	
	@Autowired
	private UserHelper userHelper;
	
	@Override
	public Client copyFrom(final Client entity) {
		Client copy = new Client();
		copy.setId(entity.getId());
		copy.setClientName(entity.getClientName());
		copy.setTitle(entity.getTitle());
		copy.setDescription(entity.getDescription());
		
		copy.setCreatedBy(entity.getCreatedBy());
		copy.setCreatedDate(entity.getCreatedDate());
		
		if (null != entity.getLogin()) {
			logger.debug(entity.getLogin().getName());
			Login login = loginHelper.copyFrom(entity.getLogin());
			copy.setLogin(login);
		}
		
		copy.setModifiedBy(entity.getModifiedBy());
		copy.setModifiedDate(entity.getModifiedDate());
		
		if (null != entity.getUsers()) {
			logger.debug("No of Users found for client " +entity.getClientName() + " are " +entity.getUsers().size());
			Set<User> users = new HashSet<User>();
			for(User user:entity.getUsers()){
				logger.debug(user.getId());
				User cuser = userHelper.copyFrom(user);
				users.add(cuser);
			}
			copy.setUsers(users);
		}
		
		copy.setIsEnabled(entity.getIsEnabled());
		return copy;
	}

	@Override
	public Client copyWithoutPkFrom(final Client entity) {
		Client copy = new Client();
		copy.setClientName(entity.getClientName());
		copy.setTitle(entity.getTitle());
		copy.setDescription(entity.getDescription());		
		
		if (null != entity.getLogin()) {
			logger.debug(entity.getLogin().getName());
			Login login = loginHelper.copyFrom(entity.getLogin());
			copy.setLogin(login);
		}
		
		copy.setCreatedBy(entity.getCreatedBy());
		copy.setCreatedDate(entity.getCreatedDate());
		
		copy.setModifiedBy(entity.getModifiedBy());
		copy.setModifiedDate(entity.getModifiedDate());
		
		if (null != entity.getUsers()) {
			logger.debug("No of Users found for client " +entity.getClientName() + " are " +entity.getUsers().size());
			Set<User> users = new HashSet<User>();
			for(User user:entity.getUsers()){
				logger.debug(user.getId());
				User cuser = userHelper.copyFrom(user);
				users.add(cuser);
			}
			copy.setUsers(users);
		}
		
		copy.setIsEnabled(entity.getIsEnabled());
		
		return copy;
	}

	@Override
	public Client createEntityInstance() {
		return new Client();
	}

	@Override
	public Client createRandomEntity() {
		Client user = new Client();
		user.setClientName(ValueGenerator.getMaxString(100));
		user.setIsEnabled(true);
		return user;
	}

	@Override
	public Client updateFrom(final Client fromEntity, Client toEntity) {
		
		if (SocialWatcherUtil.isNotEmptyAndNotNullString(fromEntity.getTitle()))
			toEntity.setTitle(fromEntity.getTitle());
		
		if (SocialWatcherUtil.isNotEmptyAndNotNullString(fromEntity.getDescription()))
			toEntity.setDescription(fromEntity.getDescription());
		
		if (null != fromEntity.getUsers() && fromEntity.getUsers().size() > 0) {
			if (null != toEntity.getUsers())
				toEntity.getUsers().addAll(fromEntity.getUsers());
			else
				toEntity.setUsers(fromEntity.getUsers());
		} 
		
		toEntity.setModifiedBy(fromEntity.getModifiedBy());
		toEntity.setModifiedDate(fromEntity.getModifiedDate());
		
		if (SocialWatcherUtil.isNotNullObject(fromEntity.getIsEnabled()))
			toEntity.setIsEnabled(fromEntity.getIsEnabled());
		
		return toEntity;
	}

}
