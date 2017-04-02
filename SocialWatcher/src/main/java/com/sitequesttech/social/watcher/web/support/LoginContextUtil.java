package com.sitequesttech.social.watcher.web.support;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.sitequesttech.social.watcher.domain.entity.Login;
import com.sitequesttech.social.watcher.domain.repository.LoginRepository;
import com.sitequesttech.social.watcher.web.security.LoginContext;


/**
 * Utility class to get Login account informations in a request
 * 
 * @author sivaprasad.vindula@icentris.com
 * 
 */
@Component("loginContext")
public class LoginContextUtil {

	@Autowired
	LoginRepository loginRepository;
	
	/**
	 * Getting the user Email address used in the authentification process
	 * 
	 * @return
	 */
	public String getLoginname() {
		return LoginContext.getUsername();
	}

	/**
	 * Getting the current user first name & last Name
	 * 
	 * @return
	 */
	public String getUserFullName() {
		String result = "";
		Login login = loginRepository.findByName(LoginContext
				.getUsername());
		if (login != null) {
			//result = login.getFirstName() + " " + user.getLastName();
			result = login.getName();
		}
		return result;
	}

	/**
	 * Checking if the current user is identified
	 * 
	 * @return
	 */
	public boolean isAnonymousUser() {
		return "anonymousUser".equalsIgnoreCase(getLoginname());
	}

	/**
	 * Getting the current user local id (en, fr...)
	 * 
	 * @return
	 */
	public Locale getLocale() {
		return LocaleContextHolder.getLocale();
	}

	/**
	 * Getting the current user roles
	 * 
	 * @return
	 */
	public List<String> getRoles() {
		return LoginContext.getRoles();
	}
	
	/*public String getRole() {
		return UserContext.getRole();
	}*/
	
	public String getRole() {
		return LoginContext.getUserRole();
	}
	
	public Login getUser() {
		return loginRepository.findByName(getLoginname());
	}
}
