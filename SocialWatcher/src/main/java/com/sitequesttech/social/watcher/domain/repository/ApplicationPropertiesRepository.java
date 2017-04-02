package com.sitequesttech.social.watcher.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sitequesttech.social.watcher.domain.entity.ApplicationProperties;

/**
 * Application Properties repository
 * 
 * @author sivaprasad.vindula@icentris.com
 *
 */
public interface ApplicationPropertiesRepository extends JpaRepository<ApplicationProperties, Long> {

	ApplicationProperties findByPropertyName(String propertyName);
}
