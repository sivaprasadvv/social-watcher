package com.sitequesttech.social.watcher.service.helper;

import org.springframework.stereotype.Component;

import com.sitequesttech.social.watcher.domain.entity.ApplicationProperties;
import com.sitequesttech.social.watcher.common.support.SocialWatcherUtil;
import com.sitequesttech.social.watcher.common.support.ValueGenerator;


/**
 * Application Properties Helper 
 * 
 * @author sivaprasad.vindula@icentris.com
 *
 */
@Component
public class ApplicationPropertiesHelper implements EntityHelper<ApplicationProperties> {

    @Override
    public ApplicationProperties createEntityInstance() {
        return new ApplicationProperties();
    }

    @Override
    public ApplicationProperties createRandomEntity() {
    	ApplicationProperties entity = new ApplicationProperties();
        entity.setPropertyName(ValueGenerator.getUniqueString(10));
        return entity;
    }

    @Override
    public ApplicationProperties copyFrom(ApplicationProperties entity) {
    	ApplicationProperties copy = new ApplicationProperties();
        copy.setId(entity.getId());
        copy.setPropertyName(entity.getPropertyName());
        copy.setPropertyValue(entity.getPropertyValue());
        return copy;
    }

    @Override
    public ApplicationProperties copyWithoutPkFrom(ApplicationProperties entity) {
    	ApplicationProperties copy = new ApplicationProperties();
        copy.setPropertyName(entity.getPropertyName());
        copy.setPropertyValue(entity.getPropertyValue());
        return copy;
    }

    @Override
    public ApplicationProperties updateFrom(ApplicationProperties fromentity, ApplicationProperties toEntity) {
    	
    	if (SocialWatcherUtil.isNotEmptyAndNotNullString(fromentity.getPropertyValue()))
    			toEntity.setPropertyValue(fromentity.getPropertyValue());
    	if (SocialWatcherUtil.isNotNullObject(fromentity.getModifiedBy()))
			toEntity.setModifiedBy(fromentity.getModifiedBy());
    	if (SocialWatcherUtil.isNotNullObject(fromentity.getModifiedDate()))
			toEntity.setModifiedDate(fromentity.getModifiedDate());
    	
        return toEntity;
    }
}
