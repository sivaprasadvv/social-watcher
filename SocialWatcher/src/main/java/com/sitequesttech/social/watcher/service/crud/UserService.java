package com.sitequesttech.social.watcher.service.crud;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
import com.sitequesttech.social.watcher.domain.entity.Client;
import com.sitequesttech.social.watcher.domain.entity.Login;
import com.sitequesttech.social.watcher.domain.entity.Partner;
import com.sitequesttech.social.watcher.domain.entity.User;
import com.sitequesttech.social.watcher.service.helper.EntityHelper;
import com.sitequesttech.social.watcher.service.helper.UserHelper;
import com.sitequesttech.social.watcher.web.support.SocialWatcherContextUtil;
import com.sitequesttech.social.watcher.domain.repository.UserRepository;


/**
 * User service 
 * 
 * @author sivaprasad.vindula@icentris.com
 *
 */
@Service
public class UserService extends AbstractCrudService<User> {
	
	private static final Logger logger = Logger
			.getLogger(UserService.class);

	@Autowired
    private UserRepository repository;

	@Autowired
    private UserHelper helper;
	
	@Autowired
    private RoleService roleService;
    
    @Autowired
    private LoginService loginService;
    
    @Autowired
    private SocialWatcherContextUtil socialWatcherContextUtil;
    
    @Autowired
    private PartnerService partnerService;
    
    @Autowired
    private ClientService clientService;

    @Override
    public PagingAndSortingRepository<User, Long> getRepository() {
        return this.repository;
    }

    @Override
    public EntityHelper<User> getHelper() {
        return this.helper;
    }
    
    public List<User> getUsersNotAssignedToClients() {
    	return repository.findByIsAssignedIsNull();
    }   
    
    public User getByLogin(Login login) {
    	return repository.getByLogin(login);
    }
    
    public ReadOperationResults read(ReadOperationParams params, Collection<String> users) {
        ReadOperationResults result = new ReadOperationResults();
        Direction sortDir = params.getsSortDir_0().equals("asc") ? Direction.ASC : Direction.DESC;
        String sortColName = params.getsColumns().split(",")[params.getiSortCol_0()];
        Sort sort = new Sort(sortDir, sortColName);
        int pageNumber = (int) Math.ceil(params.getiDisplayStart() / params.getiDisplayLength());
        
        Page<User> page = repository.findByUserNameIn(users, new PageRequest(pageNumber, params.getiDisplayLength(), sort));
        
        List<User> data = page.getContent();
        List<User> uiDate = new ArrayList<User>();
        for (User entity : data) {
            uiDate.add(getHelper().copyFrom(entity));
        }
        result.setsEcho(params.getsEcho());
        result.setiTotalDisplayRecords(page.getTotalElements());
        result.setiTotalRecords(page.getTotalElements());
        result.setAaData(uiDate);
        return result;
    }
    
    public 	ReadOperationResults processRead(HttpServletRequest request, ReadOperationParams params) {
    	
    	if (logger.isDebugEnabled())
    		logger.debug("processRead() - ");
    	
    	Login loginUser = socialWatcherContextUtil.getLoginUser(request);
    	String role = socialWatcherContextUtil.getLoginUserRole(request);
    	
    	if (SocialWatcherUtil.isNotEmptyAndNotNullString(role) && "partner".equals(role)) {
    		Partner partner = partnerService.getByLogin(loginUser);
    		Collection<String> usernames = new ArrayList<String>();
    		usernames.add("Nothing to Display");
    		Set<Client> clients = partner.getClients();
    		if (null != clients && clients.size() > 0) {
	    		
    			if(logger.isDebugEnabled()) {
	    			logger.debug("Total clients found for " +partner.getPartnerName()+" is " +clients.size());
	    		}
	    		for(Client client: clients){
	    			Set<User> users = client.getUsers();
	    			if (null != users && users.size()>0){
	    				if(logger.isDebugEnabled()) {
	    	    			logger.debug("Total users found for " +client.getClientName()+" is " +users.size());
	    	    		}
	    				for(User user: users){
	    					usernames.add(user.getUserName());
	    				}
	    			}	    			
	    		}	    		
    		}
    		return read(params, usernames);
    	}
    	
    	if (SocialWatcherUtil.isNotEmptyAndNotNullString(role) && "client".equals(role)) {
    		Client client = clientService.getByLogin(loginUser);
    		Collection<String> usernames = new ArrayList<String>();
    		usernames.add("Nothing to Display");
    		Set<User> users = client.getUsers();
			if (null != users && users.size()>0){
				if(logger.isDebugEnabled()) {
	    			logger.debug("Total users found for " +client.getClientName()+" is " +users.size());
	    		}
				for(User user: users){
					usernames.add(user.getUserName());
				}
			}
    		return read(params, usernames);
    	}
    	
    	//admin role
   		return read(params);    	
    }
    
    public Map<String, ? extends Object> processCreate(HttpServletRequest request, HttpServletResponse response, User entity) {
    	
    	if (logger.isDebugEnabled())
    		logger.debug("processCreate() - ");    	

    	Login loginUser = socialWatcherContextUtil.getLoginUser(request);
    	
    	Login ulogin = entity.getLogin();
    	ulogin.setPassword(DigestUtils.md5Hex(ulogin.getPassword()));
    	ulogin.setIsEnabled(true);
    	ulogin.setCreatedDate(new Date());
    	ulogin.setCreatedBy(loginUser.getId());
    	ulogin.getRoles().add(roleService.getRoleByName("user"));
    	loginService.create(ulogin);
    	if (logger.isDebugEnabled())
    		logger.debug("user login obj:" +ulogin.getId());
    	
    	entity.setLogin(ulogin);
    	entity.setCreatedBy(loginUser.getId());
    	entity.setCreatedDate(new Date());				
		create(entity);
		
		response.setStatus(HttpServletResponse.SC_CREATED);
		return null;	
    }
    
    public Map<String, ? extends Object> processUpdate(HttpServletRequest request, HttpServletResponse response, 
    		Long id, User entity) {
    	
    	if (logger.isDebugEnabled())
    		logger.debug("processUpdate() - ");    	

		Login loginUser = socialWatcherContextUtil.getLoginUser(request);
    	
    	entity.setModifiedBy(loginUser.getId());
    	entity.setModifiedDate(new Date());
    	
		User entityToUpdate = getById(id);
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
