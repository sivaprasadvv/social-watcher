package com.sitequesttech.social.watcher.service.helper;


import org.springframework.stereotype.Component;

import com.sitequesttech.social.watcher.domain.entity.SocialMedia;
import com.sitequesttech.social.watcher.common.support.SocialWatcherUtil;
import com.sitequesttech.social.watcher.common.support.ValueGenerator;

@Component
public class SocialMediaHelper implements EntityHelper<SocialMedia> {
	

	@Override
	public SocialMedia copyFrom(final SocialMedia entity) {
		SocialMedia copy = new SocialMedia();
		copy.setId(entity.getId());
		copy.setName(entity.getName());
		copy.setIsEnabled(entity.getIsEnabled());
		return copy;
	}

	@Override
	public SocialMedia copyWithoutPkFrom(final SocialMedia entity) {
		SocialMedia copy = new SocialMedia();
		copy.setName(entity.getName());
		copy.setIsEnabled(entity.getIsEnabled());
		return copy;
	}

	@Override
	public SocialMedia createEntityInstance() {
		return new SocialMedia();
	}

	@Override
	public SocialMedia createRandomEntity() {
		SocialMedia api = new SocialMedia();
		api.setName(ValueGenerator.getMaxString(100));
		api.setIsEnabled(true);
		return api;
	}

	@Override
	public SocialMedia updateFrom(final SocialMedia fromEntity, SocialMedia toEntity) {		
		
		toEntity.setModifiedBy(fromEntity.getModifiedBy());
		toEntity.setModifiedDate(fromEntity.getModifiedDate());
		
		if (SocialWatcherUtil.isNotNullObject(fromEntity.getIsEnabled()))
		toEntity.setIsEnabled(fromEntity.getIsEnabled());
		
		return toEntity;
	}

}
