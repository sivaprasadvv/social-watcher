package com.sitequesttech.social.watcher.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sitequesttech.social.watcher.domain.entity.Address;

/**
 * Address repository
 * 
 * @author sivaprasad.vindula@icentris.com
 *
 */
public interface AddressRepository extends JpaRepository<Address, Long> {

}
