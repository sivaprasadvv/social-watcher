package com.sitequesttech.social.watcher.service.helper;


import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sitequesttech.social.watcher.domain.entity.Client;
import com.sitequesttech.social.watcher.domain.entity.Login;
import com.sitequesttech.social.watcher.domain.entity.Partner;
import com.sitequesttech.social.watcher.common.support.SocialWatcherUtil;
import com.sitequesttech.social.watcher.common.support.ValueGenerator;

@Component
public class PartnerHelper implements EntityHelper<Partner> {
	
	private static final Logger logger = Logger
			.getLogger(PartnerHelper.class);
	
	@Autowired
	private LoginHelper loginHelper;
	
	@Autowired
	ClientHelper clientHelper;
	
	@Override
	public Partner copyFrom(final Partner entity) {
		Partner copy = new Partner();
		copy.setId(entity.getId());
		copy.setPartnerName(entity.getPartnerName());
		copy.setTitle(entity.getTitle());
		copy.setDescription(entity.getDescription());
		
		if (null != entity.getLogin()) {
			logger.debug(entity.getLogin().getName());
			Login login = loginHelper.copyFrom(entity.getLogin());
			copy.setLogin(login);
		}
		
		copy.setCreatedBy(entity.getCreatedBy());
		copy.setCreatedDate(entity.getCreatedDate());
		
		copy.setModifiedBy(entity.getModifiedBy());
		copy.setModifiedDate(entity.getModifiedDate());
		
		if (null != entity.getClients()) {
			logger.debug("No of Clients found for partner " +entity.getPartnerName() + " are " +entity.getClients().size());
			Set<Client> clients = new HashSet<Client>();
			for(Client client:entity.getClients()){
				logger.debug(client.getId());
				Client cclient = clientHelper.copyFrom(client);
				clients.add(cclient);
			}
			copy.setClients(clients);
		}
		
		copy.setIsEnabled(entity.getIsEnabled());
		return copy;
	}

	@Override
	public Partner copyWithoutPkFrom(final Partner entity) {
		Partner copy = new Partner();
		copy.setPartnerName(entity.getPartnerName());
		copy.setTitle(entity.getTitle());
		copy.setDescription(entity.getDescription());
		
		if (null != entity.getLogin()) {
			logger.debug(entity.getLogin().getName());
			Login login = loginHelper.copyFrom(entity.getLogin());
			copy.setLogin(login);
		}
		
		copy.setCreatedBy(entity.getCreatedBy());
		copy.setCreatedDate(entity.getCreatedDate());
		
		copy.setModifiedBy(entity.getModifiedBy());
		copy.setModifiedDate(entity.getModifiedDate());
		
		if (null != entity.getClients()) {
			logger.debug("No of Clients found for partner " +entity.getPartnerName() + " are " +entity.getClients().size());
			Set<Client> clients = new HashSet<Client>();
			for(Client client:entity.getClients()){
				logger.debug(client.getId());
				Client cclient = clientHelper.copyFrom(client);
				clients.add(cclient);
			}
			copy.setClients(clients);
		}
		
		copy.setIsEnabled(entity.getIsEnabled());
		return copy;
	}

	@Override
	public Partner createEntityInstance() {
		return new Partner();
	}

	@Override
	public Partner createRandomEntity() {
		Partner partner = new Partner();
		partner.setPartnerName(ValueGenerator.getMaxString(100));
		return partner;
	}

	@Override
	public Partner updateFrom(final Partner fromEntity, Partner toEntity) {
		
		if (SocialWatcherUtil.isNotEmptyAndNotNullString(fromEntity.getTitle()))
			toEntity.setTitle(fromEntity.getTitle());
		
		if (SocialWatcherUtil.isNotEmptyAndNotNullString(fromEntity.getDescription()))
			toEntity.setDescription(fromEntity.getDescription());
		
		if (null != fromEntity.getClients() && fromEntity.getClients().size() > 0) {
			//toEntity.setClients(fromEntity.getClients());
			if (null != toEntity.getClients())
				toEntity.getClients().addAll(fromEntity.getClients());
			else
				toEntity.setClients(fromEntity.getClients());
		}
		
		toEntity.setModifiedBy(fromEntity.getModifiedBy());
		toEntity.setModifiedDate(fromEntity.getModifiedDate());
		
		if (SocialWatcherUtil.isNotNullObject(fromEntity.getIsEnabled()))
			toEntity.setIsEnabled(fromEntity.getIsEnabled());
		return toEntity;
	}

}
