package com.sitequesttech.social.watcher.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sitequesttech.social.watcher.domain.entity.Role;

/**
 * Role repository
 * 
 * @author sivaprasad.vindula@icentris.com
 *
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(String roleName);
}
