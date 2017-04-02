package com.sitequesttech.social.watcher.service.helper;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sitequesttech.social.watcher.domain.entity.Login;
import com.sitequesttech.social.watcher.domain.entity.User;
import com.sitequesttech.social.watcher.common.support.SocialWatcherUtil;
import com.sitequesttech.social.watcher.common.support.ValueGenerator;

@Component
public class UserHelper implements EntityHelper<User> {
	
	private static final Logger logger = Logger
			.getLogger(UserHelper.class);
	
	@Autowired
	private LoginHelper loginHelper; 
	
	@Override
	public User copyFrom(final User entity) {
		User copy = new User();
		copy.setId(entity.getId());
		copy.setUserName(entity.getUserName());
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
		copy.setIsEnabled(entity.getIsEnabled());
		return copy;
	}

	@Override
	public User copyWithoutPkFrom(final User entity) {
		User copy = new User();
		copy.setUserName(entity.getUserName());
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
		copy.setIsEnabled(entity.getIsEnabled());
		return copy;
	}

	@Override
	public User createEntityInstance() {
		return new User();
	}

	@Override
	public User createRandomEntity() {
		User user = new User();
		user.setUserName(ValueGenerator.getMaxString(100));
		user.setIsEnabled(true);
		return user;
	}

	@Override
	public User updateFrom(final User fromEntity, User toEntity) {
		
		if (SocialWatcherUtil.isNotEmptyAndNotNullString(fromEntity.getTitle()))
			toEntity.setTitle(fromEntity.getTitle());
		
		if (SocialWatcherUtil.isNotEmptyAndNotNullString(fromEntity.getDescription()))
			toEntity.setDescription(fromEntity.getDescription());
		
		toEntity.setModifiedBy(fromEntity.getModifiedBy());
		toEntity.setModifiedDate(fromEntity.getModifiedDate());
		
		if (SocialWatcherUtil.isNotNullObject(fromEntity.getIsEnabled()))
		toEntity.setIsEnabled(fromEntity.getIsEnabled());
		
		return toEntity;
	}
}
