package com.sitequesttech.social.watcher.service.crud;

import static com.google.common.collect.Lists.newArrayList;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sitequesttech.social.watcher.domain.entity.Login;
import com.sitequesttech.social.watcher.service.helper.LoginHelper;
import com.sitequesttech.social.watcher.domain.repository.LoginRepository;

/**
 * 
 * An implementation of Spring Security's UserDetailsService.
 * 
 * @author sivaprasad.vindula@icentris.com
 * 
 */
@Service("AuthentificationService")
public class AuthentificationService implements UserDetailsService {

	private static final Logger logger = Logger
			.getLogger(AuthentificationService.class);

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private LoginHelper loginHelper;

	@Autowired
	public AuthentificationService(LoginRepository loginRepository,
			LoginHelper loginHelper, InitDataService initDataService) {
		this.loginRepository = loginRepository;
		this.loginHelper = loginHelper;
	}

	@Transactional
	public UserDetails loadUserByUsername(String name)
			throws UsernameNotFoundException, DataAccessException {
		if ((name == null) || name.trim().isEmpty()) {
			throw new UsernameNotFoundException("name is null or empty");
		}

		logger.debug("Checking  users with name: " + name);
		Login login = loginRepository.findByName(name);

		if (login == null) {
			String errorMsg = "User with name: " + name
					+ " could not be found";
			logger.debug(errorMsg);
			throw new UsernameNotFoundException(errorMsg);
		}

		Collection<GrantedAuthority> grantedAuthorities = toGrantedAuthorities(loginHelper
				.getRoleNames(login));
		String password = login.getPassword();
		boolean enabled = login.getIsEnabled();
		boolean userNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean userNonLocked = true;

		return new org.springframework.security.core.userdetails.User(name,
				password, enabled, userNonExpired, credentialsNonExpired,
				userNonLocked, grantedAuthorities);
	}

	public static Collection<GrantedAuthority> toGrantedAuthorities(
			List<String> roles) {
		List<GrantedAuthority> result = newArrayList();

		for (String role : roles) {
			result.add(new SimpleGrantedAuthority(role));
		}

		return result;
	}
}
