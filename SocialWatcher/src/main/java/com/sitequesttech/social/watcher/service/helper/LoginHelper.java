package com.sitequesttech.social.watcher.service.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import com.sitequesttech.social.watcher.domain.entity.Login;
import com.sitequesttech.social.watcher.domain.entity.Role;
import com.sitequesttech.social.watcher.common.support.ValueGenerator;

@Component
public class LoginHelper implements EntityHelper<Login> {
	
	private static final String PASSWORD_UI = "**********";

	@Override
	public Login copyFrom(final Login entity) {
		Login copy = new Login();
		copy.setId(entity.getId());
		copy.setName(entity.getName());
		copy.setPassword(PASSWORD_UI);
		copy.setIsEnabled(entity.getIsEnabled());
		return copy;
	}

	@Override
	public Login copyWithoutPkFrom(final Login entity) {
		Login copy = new Login();
		copy.setName(entity.getName());
		copy.setPassword(PASSWORD_UI);
		copy.setIsEnabled(entity.getIsEnabled());
		return copy;
	}

	@Override
	public Login createEntityInstance() {
		return new Login();
	}

	@Override
	public Login createRandomEntity() {
		Login login = new Login();
		login.setName(ValueGenerator.getMaxString(100));
		login.setPassword(PASSWORD_UI);
		login.setIsEnabled(true);
		return login;
	}

	@Override
	public Login updateFrom(final Login fromEntity, Login toEntity) {
		if (!fromEntity.getPassword().equals(PASSWORD_UI)) {
			toEntity.setPassword(DigestUtils.md5Hex(fromEntity.getPassword()));
		}
		toEntity.setIsEnabled(fromEntity.getIsEnabled());
		return toEntity;
	}
	
	/**
	 * Getting the user role names.
	 * 
	 * @param login
	 * 
	 * @return list of role names
	 */
	public List<String> getRoleNames(final Login login) {
		List<String> roleNames = new ArrayList<String>();

		for (Role role : login.getRoles()) {
			roleNames.add(role.getRoleName());
		}
		return roleNames;
	}


}
