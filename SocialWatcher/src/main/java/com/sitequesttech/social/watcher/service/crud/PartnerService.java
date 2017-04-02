package com.sitequesttech.social.watcher.service.crud;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import com.sitequesttech.social.watcher.domain.entity.Address;
import com.sitequesttech.social.watcher.domain.entity.Client;
import com.sitequesttech.social.watcher.domain.entity.Login;
import com.sitequesttech.social.watcher.domain.entity.Partner;
import com.sitequesttech.social.watcher.service.helper.EntityHelper;
import com.sitequesttech.social.watcher.service.helper.PartnerHelper;
import com.sitequesttech.social.watcher.web.support.SocialWatcherContextUtil;
import com.sitequesttech.social.watcher.web.view.PartnerClient;
import com.sitequesttech.social.watcher.domain.repository.PartnerRepository;


/**
 * Partner service 
 * 
 * @author sivaprasad.vindula@icentris.com
 *
 */
@Service
public class PartnerService extends AbstractCrudService<Partner> {
	
	private static final Logger logger = Logger
			.getLogger(PartnerService.class);

	@Autowired
    private PartnerRepository repository;

	@Autowired
    private PartnerHelper helper;
	
	@Autowired
	private SocialWatcherContextUtil socialWatcherContextUtil;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ClientService clientService;

    
    @Override
    public PagingAndSortingRepository<Partner, Long> getRepository() {
        return this.repository;
    }

    @Override
    public EntityHelper<Partner> getHelper() {
        return this.helper;
    }    
    
    public Partner getByClientsIn(Collection<Client> clients) {
    	return repository.findByClientsIn(clients);
    }
    
    public Partner getByLogin(Login login) {
    	return repository.findByLogin(login);
    }
    
    public List<PartnerClient> getClientsAssignedToPartner(HttpServletResponse response, Long id) {
    	
    	if (logger.isDebugEnabled())
    		logger.debug("getClientsAssignedToPartner() - ");
    	
    	List<PartnerClient> list = new ArrayList<PartnerClient>();
		Partner partner = getById(id);
		Set<Client> clients = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Partner id/name:" +partner.getId()+"/"+partner.getPartnerName());
			if (null != partner.getClients()) {
				logger.debug("Number of users found for " +partner.getPartnerName()+ " are " +partner.getClients().size());
				clients = partner.getClients();
			}
		}
		if (null != clients && clients.size() > 0) {
			if (logger.isDebugEnabled())
				logger.debug(clients.size());
			for (Client client : clients) {
				list.add(new PartnerClient(client.getClientName()));
			}
			return list;
		} else {
			list.add(new PartnerClient("No clients found"));
		}
		return list;    	
    }
    
    public Map<String, ? extends Object> processCreate(HttpServletRequest request, HttpServletResponse response, Partner entity) {
    	
    	if (logger.isDebugEnabled())
    		logger.debug("processCreate() - ");    	

		Login loginUser = socialWatcherContextUtil.getLoginUser(request);
    	
    	Login ulogin = entity.getLogin();
    	ulogin.setPassword(DigestUtils.md5Hex(ulogin.getPassword()));
    	ulogin.setIsEnabled(true);
    	ulogin.setCreatedDate(new Date());
    	ulogin.setCreatedBy(loginUser.getId());
    	ulogin.getRoles().add(roleService.getRoleByName("partner"));
    	loginService.create(ulogin);
    	logger.debug("user login obj:" +ulogin.getId());  	
    	
    	Address address = entity.getAddress();  
    	address.setCreatedDate(new Date());
    	address.setCreatedBy(loginUser.getId()); 
    	
    	entity.setAddress(address);
    	entity.setCreatedBy(loginUser.getId());
    	entity.setCreatedDate(new Date());
    	Partner cpartner = create(entity);
    	
    	if (logger.isDebugEnabled())
    		logger.debug(cpartner.getId());
    	
		response.setStatus(HttpServletResponse.SC_CREATED);
		return null;    	
    }
    
    public Map<String, ? extends Object> processUpdate(HttpServletRequest request, HttpServletResponse response,
    		Long id, Partner entity) {
    	
    	if (logger.isDebugEnabled())
    		logger.debug("processUpdate() - "); 
    	
    	Login loginUser = socialWatcherContextUtil.getLoginUser(request);
		Partner entityToUpdate = getById(id);
		
    	entity.setModifiedBy(loginUser.getId());
    	entity.setModifiedDate(new Date());
    	
    	if (null != entity.getClients() && entity.getClients().size() > 0) {
    		
    		if (null != entityToUpdate.getClients() && entityToUpdate.getClients().size() > 0) {
    			
    			if (entity.getClients().size() != entityToUpdate.getClients().size()) {
    				
    				Set<Client> notAssignedClients = new HashSet<Client>();	    				
    				Set<Client> assignedClients = new HashSet<Client>();
    				for(Client sclient : entityToUpdate.getClients()) {
    					boolean found = false;
    		    		for(Client eclient: entity.getClients()) {
    		    			if (sclient.getId() == eclient.getId()) {
    		    				found = true;
    		    				assignedClients.add(sclient);
    		    			}
    		    		}
    		    		if (!found) {
    		    			sclient.setModifiedBy(loginUser.getId());
    		    			sclient.setModifiedDate(new Date());
    		    			sclient.setIsAssigned(null);
    		    			notAssignedClients.add(sclient);
    		    		}
    				}
    				
    		    	entity.setClients(assignedClients);
    		    	entityToUpdate.setClients(null);
    		    	
    			} else {
    				entity.setClients(null);
    			}
    		}	    	
    	}
		
		if (entityToUpdate != null) {
			entityToUpdate = getHelper().updateFrom(entity,
					entityToUpdate);
			update(entityToUpdate);
			response.setStatus(HttpServletResponse.SC_OK);
			return null;
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}    	
    }
    
    public Map<String, ? extends Object> processUpdateAssignedClient(HttpServletRequest request, HttpServletResponse response,
    		Long id, Partner entity) {
    	
    	if (logger.isDebugEnabled())
    		logger.debug("processUpdateAssignedClient() - "); 
    	
    	Login loginUser = socialWatcherContextUtil.getLoginUser(request);
		Partner entityToUpdate = getById(id);
    	entity.setModifiedBy(loginUser.getId());
    	entity.setModifiedDate(new Date());
    	
    	if (null != entity.getClients() && entity.getClients().size() > 0) {
    		Set<Client> clients = new HashSet<Client>();
	    	for(Client sclient : entity.getClients()) {
	    		Client eclient = clientService.getById(sclient.getId());
	    		logger.debug("In fetching clients :" +eclient.getId());
	    		eclient.setModifiedBy(loginUser.getId());
	    		eclient.setModifiedDate(new Date());
	    		eclient.setIsAssigned(true);
	    		clients.add(eclient);		    		
	    	}
	    	entity.getClients().addAll(clients);
    	}
		
		
		if (entityToUpdate != null) {
			entityToUpdate = getHelper().updateFrom(entity,
					entityToUpdate);
			update(entityToUpdate);
			response.setStatus(HttpServletResponse.SC_OK);
			return null;
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	
    	
    }
}
