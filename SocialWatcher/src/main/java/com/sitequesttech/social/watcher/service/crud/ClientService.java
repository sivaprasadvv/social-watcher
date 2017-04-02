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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import com.sitequesttech.social.watcher.common.support.ReadOperationParams;
import com.sitequesttech.social.watcher.common.support.ReadOperationResults;
import com.sitequesttech.social.watcher.common.support.SocialWatcherUtil;
import com.sitequesttech.social.watcher.domain.entity.Address;
import com.sitequesttech.social.watcher.domain.entity.Client;
import com.sitequesttech.social.watcher.domain.entity.Login;
import com.sitequesttech.social.watcher.domain.entity.Partner;
import com.sitequesttech.social.watcher.domain.entity.User;
import com.sitequesttech.social.watcher.service.helper.ClientHelper;
import com.sitequesttech.social.watcher.service.helper.EntityHelper;
import com.sitequesttech.social.watcher.web.support.SocialWatcherContextUtil;
import com.sitequesttech.social.watcher.domain.repository.ClientRepository;


/**
 * Client service 
 * 
 * @author sivaprasad.vindula@icentris.com
 *
 */
@Service
public class ClientService extends AbstractCrudService<Client> {
	
	private static final Logger logger = Logger
			.getLogger(ClientService.class);

	@Autowired
    private ClientRepository repository;

	@Autowired
    private ClientHelper helper;
    
    @Autowired
    private PartnerService partnerService;
    
    @Autowired
    private SocialWatcherContextUtil socialWatcherContextUtil;
    
    @Autowired
    private LoginService loginService;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private UserService userService;

    @Override
    public PagingAndSortingRepository<Client, Long> getRepository() {
        return this.repository;
    }

    @Override
    public EntityHelper<Client> getHelper() {
        return this.helper;
    }
    
    public List<Client> getClientsNotAssignedToPartners() {
    	return repository.findByIsAssignedIsNull();
    }
    
    public Client getByUsersIn(Collection<User> users) {
    	return repository.findByUsersIn(users);
    }   
    
    public List<Client> getEnabledClients() {
    	return repository.findByIsEnabledIsTrue();
    }
    
    public Client getByLogin(Login login) {
    	return repository.findByLogin(login);
    }
    
    public ReadOperationResults read(ReadOperationParams params, Collection<String> clients) {
    	logger.info("read(ReadOperationParams params, Collection<String> clients) - ");
    	
        ReadOperationResults result = new ReadOperationResults();
        Direction sortDir = params.getsSortDir_0().equals("asc") ? Direction.ASC : Direction.DESC;
        String sortColName = params.getsColumns().split(",")[params.getiSortCol_0()];
        Sort sort = new Sort(sortDir, sortColName);
        int pageNumber = (int) Math.ceil(params.getiDisplayStart() / params.getiDisplayLength());
        
        Page<Client> page = repository.findByClientNameIn(clients, new PageRequest(pageNumber, params.getiDisplayLength(), sort));
        
        List<Client> data = page.getContent();
        List<Client> uiDate = new ArrayList<Client>();
        for (Client entity : data) {
            uiDate.add(getHelper().copyFrom(entity));
        }
        result.setsEcho(params.getsEcho());
        result.setiTotalDisplayRecords(page.getTotalElements());
        result.setiTotalRecords(page.getTotalElements());
        result.setAaData(uiDate);
        return result;
    }
    
    
    public ReadOperationResults processRead(HttpServletRequest request, ReadOperationParams params) {
    	
    	if (logger.isDebugEnabled())
    		logger.debug("processRead() - ");
    	
    	Login loginUser = socialWatcherContextUtil.getLoginUser(request);
    	String role = socialWatcherContextUtil.getLoginUserRole(request);
    	
    	if (SocialWatcherUtil.isNotEmptyAndNotNullString(role) && "partner".equals(role)) {
    		Partner partner = partnerService.getByLogin(loginUser);
    		Collection<String> clientnames = new ArrayList<String>();
    		clientnames.add("Nothing to Display");
    		Set<Client> clients = partner.getClients();
    		if (null != clients && clients.size() > 0) {
	    		
    			if(logger.isDebugEnabled()) {
	    			logger.debug("Total clients found for " +partner.getPartnerName()+" is " +clients.size());
	    		}
	    		for(Client client: clients){
	    			clientnames.add(client.getClientName());  			
	    		}	    		
    		}
    		return read(params, clientnames);
    	}
		
		return read(params);    	
    }
    
    public Map<String, ? extends Object> processCreate(HttpServletRequest request, HttpServletResponse response, Client entity) {
    	
    	if (logger.isDebugEnabled())
    		logger.debug("processCreate() - ");
    	
    	Login loginUser = socialWatcherContextUtil.getLoginUser(request);
    	
    	Login ulogin = entity.getLogin();
    	ulogin.setPassword(DigestUtils.md5Hex(ulogin.getPassword()));
    	ulogin.setIsEnabled(true);
    	ulogin.setCreatedDate(new Date());
    	ulogin.setCreatedBy(loginUser.getId());
    	ulogin.getRoles().add(roleService.getRoleByName("client"));
    	loginService.create(ulogin);
    	if (logger.isDebugEnabled())
    		logger.debug("user login obj:" +ulogin.getId());
    	
    	Address address = entity.getAddress();  
    	address.setCreatedDate(new Date());
    	address.setCreatedBy(loginUser.getId());    	
    	
    	entity.setAddress(address);
    	entity.setCreatedBy(loginUser.getId());
    	entity.setCreatedDate(new Date());
    	Client cclient = create(entity);
    	
    	if (logger.isDebugEnabled())
    		logger.debug(cclient.getId());
    	
		response.setStatus(HttpServletResponse.SC_CREATED);
		return null;
    }
    	
    public Map<String, ? extends Object> processUpdate(HttpServletRequest request, HttpServletResponse response, Long id, Client entity) {
    	
    	if (logger.isDebugEnabled())
    		logger.debug("processUpdate() - ");  
				
		Login loginUser = socialWatcherContextUtil.getLoginUser(request);
		Client entityToUpdate = getById(id);
    	
    	entity.setModifiedBy(loginUser.getId());
    	entity.setModifiedDate(new Date());
    	
    	if (null != entity.getUsers() && entity.getUsers().size() > 0) {
    		
    		if (null != entityToUpdate.getUsers() && entityToUpdate.getUsers().size() > 0) {
    			
    			if (entity.getUsers().size() != entityToUpdate.getUsers().size()) {
    				
    				Set<User> assignedUsers = new HashSet<User>();
    				Set<User> notAssignedUsers = new HashSet<User>();	    				
    				for(User suser : entityToUpdate.getUsers()) {
    					boolean found = false;
    		    		for(User euser: entity.getUsers()) {
    		    			if (suser.getId() == euser.getId()) {
    		    				found = true;
    		    				assignedUsers.add(suser);
    		    			}
    		    		}
    		    		if (!found) {
    		    			suser.setModifiedBy(loginUser.getId());
    		    			suser.setModifiedDate(new Date());
    		    			suser.setIsAssigned(null);
    		    			notAssignedUsers.add(suser);
    		    		}
    				}    				
    				entity.setUsers(assignedUsers);	    				
    				entityToUpdate.setUsers(null);  
    			} else {
    				entity.setUsers(null);
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
    
    public Map<String, ? extends Object> processUpdateAssignedUser(HttpServletRequest request, HttpServletResponse response, 
    		Long id, Client entity) {
    
    	if (logger.isDebugEnabled())
    		logger.debug("processUpdateAssignedUser() - id :" +id);

    	Login loginUser = socialWatcherContextUtil.getLoginUser(request);
    	Client entityToUpdate = getById(id);

    	entity.setModifiedBy(loginUser.getId());
    	entity.setModifiedDate(new Date());
    	
    	if (null != entity.getUsers() && entity.getUsers().size() > 0) {
    		logger.debug("In fetching users");
    		Set<User> users = new HashSet<User>();
	    	for(User suser : entity.getUsers()) {
	    		User euser = userService.getById(suser.getId());
	    		logger.debug("In fetching users :" +euser.getId());
	    		euser.setModifiedBy(loginUser.getId());
	    		euser.setModifiedDate(new Date());
	    		euser.setIsAssigned(true);
	    		users.add(euser);		    		
	    	}
	    	entity.getUsers().addAll(users);
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
