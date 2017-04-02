package com.sitequesttech.social.watcher.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sitequesttech.social.watcher.domain.entity.SocialMedia;

public interface SocialMediaRepository extends JpaRepository<SocialMedia, Long> {
	
	public SocialMedia findByName(String name);
	
	public List<SocialMedia> findByIsEnabledIsTrue();

}
