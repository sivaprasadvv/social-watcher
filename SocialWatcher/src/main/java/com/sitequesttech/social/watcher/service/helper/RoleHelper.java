package com.sitequesttech.social.watcher.service.helper;

import org.springframework.stereotype.Component;

import com.sitequesttech.social.watcher.domain.entity.Role;
import com.sitequesttech.social.watcher.common.support.SocialWatcherUtil;
import com.sitequesttech.social.watcher.common.support.ValueGenerator;


/**
 * Role Helper 
 * 
 * @author sivaprasad.vindula@icentris.com
 *
 */
@Component
public class RoleHelper implements EntityHelper<Role> {

    @Override
    public Role createEntityInstance() {
        return new Role();
    }

    @Override
    public Role createRandomEntity() {
        Role entity = new Role();
        entity.setRoleName(ValueGenerator.getUniqueString(10));
        return entity;
    }

    @Override
    public Role copyFrom(Role entity) {
        Role copy = new Role();
        copy.setId(entity.getId());
        copy.setRoleName(entity.getRoleName());
        copy.setTitle(entity.getTitle());
        copy.setDescription(entity.getDescription());
        return copy;
    }

    @Override
    public Role copyWithoutPkFrom(Role entity) {
        Role copy = new Role();
        copy.setRoleName(entity.getRoleName());
        copy.setTitle(entity.getTitle());
        copy.setDescription(entity.getDescription());
        return copy;
    }

    @Override
    public Role updateFrom(Role fromentity, Role toEntity) {
    	
    	if (SocialWatcherUtil.isNotEmptyAndNotNullString(fromentity.getTitle()))
    			toEntity.setTitle(fromentity.getTitle());
    	if (SocialWatcherUtil.isNotEmptyAndNotNullString(fromentity.getDescription()))
			toEntity.setDescription(fromentity.getDescription());
    	if (SocialWatcherUtil.isNotNullObject(fromentity.getModifiedBy()))
			toEntity.setModifiedBy(fromentity.getModifiedBy());
    	if (SocialWatcherUtil.isNotNullObject(fromentity.getModifiedDate()))
			toEntity.setModifiedDate(fromentity.getModifiedDate());
    	
        return toEntity;
    }
}
