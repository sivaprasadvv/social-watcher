package com.sitequesttech.social.watcher.service.crud;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import com.sitequesttech.social.watcher.common.support.SocialWatcherUtil;
import com.sitequesttech.social.watcher.domain.entity.Client;
import com.sitequesttech.social.watcher.domain.entity.Login;
import com.sitequesttech.social.watcher.domain.entity.Partner;
import com.sitequesttech.social.watcher.domain.entity.Query;
import com.sitequesttech.social.watcher.domain.entity.User;
import com.sitequesttech.social.watcher.service.helper.EntityHelper;
import com.sitequesttech.social.watcher.service.helper.QueryHelper;
import com.sitequesttech.social.watcher.web.support.SocialWatcherContextUtil;
import com.sitequesttech.social.watcher.domain.repository.QueryRepository;


/**
 * Query Service
 * 
 * @author sivaprasad.vindula@icentris.com
 *
 */
@Service("QueryService")
public class QueryService extends AbstractCrudService<Query> {
	
	private static final Logger logger = Logger
			.getLogger(QueryService.class);

	@Autowired
    private QueryRepository repository;

	@Autowired
    private QueryHelper helper;
	
	@Autowired
	private SocialWatcherContextUtil socialWatcherContextUtil;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private PartnerService partnerService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SocialMediaService socialMediaService;
	
    @Override
    public PagingAndSortingRepository<Query, Long> getRepository() {
        return this.repository;
    }

    @Override
    public EntityHelper<Query> getHelper() {
        return this.helper;
    }
    
    public List<Query> getQueries() {
    	return repository.findAll();
    }
    
    public List<String> getDistinctQueries() {
    	return repository.findDistinctQueryText();
    }
    
    public Long getQueryId(String queryText) {
    	return repository.findByQueryText(queryText).getId();
    }
    
    public List<Query> getQueriesBySocialMediaId(long socialMediaId) {
    	return repository.findBySocialMediaId(socialMediaId);
    }
    
    public List<Query> getQueriesByQueryTextIn(Collection<String> queryTexts) {
    	return repository.findByQueryTextIn(queryTexts);
    }
    
    public List<Query> getQueriesByClient(Client client) {
    	return repository.findByClient(client);
    }
    
    public List<Query> getQueriesByClientIn(Collection<Client> clients) {
    	return repository.findByClientIn(clients);
    }
    
    public List<String> getDistinctQueriesByClient(Client client) {
    	return repository.findDistinctQueryTextByClient(client);
    }
    
    public Query getQueryByQueryTextAndClient(String queryText, Client client) {
    	return repository.findByQueryTextAndClient(queryText, client);
    }
    
    public Query getQueryByQueryTextAndClientAndSocialMediaId(String queryText, Client client, Long socialMedia) {
    	return repository.findByQueryTextAndClient(queryText, client);
    }
    
    public List<Query> getQueryByQueryTextAndClientAndSocialMediaIdIn(String queryText, Client client, Collection<String> socialMedias) {
    	
    	Collection<Long> socialMediaIds = new ArrayList<Long>();
    	for(String string: socialMedias) {
    		socialMediaIds.add(Long.valueOf(string));
    	}    	
    	return repository.findByQueryTextAndClientAndSocialMediaIdIn(queryText, client,socialMediaIds);
    }
    
    public long getQueryCountByClient(Client client) {
    	return repository.findQueryCountByClient(client);
    }
    
    public List<Query> processMasterQuery(Query query) {
    	
    	logger.info("processMasterQuery() - ");
    	
    	List<Query> queries = new ArrayList<Query>();
    	String[] selectedSocialMedia = query.getSelectedSocialMedia();
    	
    	if (null != selectedSocialMedia && selectedSocialMedia.length > 0) {
    		if (logger.isDebugEnabled())
    			logger.debug("Selected Social Media:" +selectedSocialMedia.length);
    		for (String socialMedia : selectedSocialMedia) {
				if (!socialMedia.equals("multiselect-all") && !socialMedia.equals("-1")) {					
					Query lquery = new Query();
					lquery.setQueryText(query.getQueryText());
					lquery.setSocialMedia(socialMediaService.getById(Long.valueOf(socialMedia)));
					lquery.setCreatedDate(new Date());
					lquery.setCreatedBy(query.getCreatedBy());
					lquery.setClient(query.getClient());
					Query cquery = create(lquery);
					if (logger.isDebugEnabled())
						logger.debug(cquery.getId());
					queries.add(cquery);
				}
			}
    	}
    	
    	return queries;
    }  
    
    public Map<String, ? extends Object> processCreate(Query entity,
				HttpServletRequest request, HttpServletResponse response) {
    	
    	logger.info("processCreate() - ");
    	
    	Login loginUser = socialWatcherContextUtil.getLoginUser(request);
    	String role = socialWatcherContextUtil.getLoginUserRole(request);
		
    	if (SocialWatcherUtil.isNotEmptyAndNotNullString(role) && "admin".equals(role) || 
    			SocialWatcherUtil.isNotEmptyAndNotNullString(role) && "partner".equals(role)) {
    		Client client = clientService.getById(Long.valueOf(entity.getSelectedClient()));
    		
    		/** Start: Conditional check for allowed number of search terms **/
    		if (canAddNewQueryWordToClient(client)) {
    			logger.debug("In allowed query words exceed");
    			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				return null;	    			
    		}
    		/** End: Conditional check for allowed number of search terms **/
    		
    		
    		/** Start: Conditional check for this combination already exists **/
    		if (isCombinationExists(entity, client)) {
    			logger.debug("In combination exists");
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				return null;	    			
    		}
    		/** End: Conditional check for this combination already exists **/
    		
    		entity.setCreatedBy(loginUser.getId());
    		entity.setClient(client);
	    	List<Query> queries = processMasterQuery(entity);
			
			if (null != queries && queries.size() > 0){
				logger.debug("No. of queries: " +queries.size());
				request.getSession().setAttribute("queries", queries);
			}
			
			response.setStatus(HttpServletResponse.SC_CREATED);
			return null;
    	} else {		    	
	    	Client client = clientService.getByLogin(loginUser);
	    	
	    	/** Start: Conditional check for allowed number of search terms **/
    		if (canAddNewQueryWordToClient(client)) {
    			logger.debug("In allowed query words exceed");
    			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				return null;	    			
    		}
    		/** End: Conditional check for allowed number of search terms **/
    		
    		
    		/** Start: Conditional check for this combination already exists **/
    		if (isCombinationExists(entity, client)) {
    			logger.debug("In combination exists");
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				return null;	    			
    		}
    		/** End: Conditional check for this combination already exists **/
	    	
	    	if (null != client) {
				entity.setCreatedBy(loginUser.getId());
		    	entity.setClient(client);
				
				List<Query> queries = processMasterQuery(entity);
				
				if (null != queries && queries.size() > 0){
					logger.debug("No. of queries: " +queries.size());
					request.getSession().setAttribute("queries", queries);
				}
				
				response.setStatus(HttpServletResponse.SC_CREATED);
				return null;
	    	} else {
	    		if (logger.isDebugEnabled())
					logger.debug("No client found for " +loginUser.getName());
	    	}
    	}
    	return null;    	
    }
    
    private boolean canAddNewQueryWordToClient(Client client) {
    	
    	long countByClient = getQueryCountByClient(client);
		return (countByClient >= client.getAllowedQueryWordsCount());	
		
    }
    
    private boolean isCombinationExists(Query query, Client client) {
    	
    	List<Query> fQuery = getQueryByQueryTextAndClientAndSocialMediaIdIn(query.getQueryText(), client, Arrays.asList(query.getSelectedSocialMedia()));
		return (null != fQuery && fQuery.size()>0);
    	
    }
    
    public String processReadMasterQueryPage(HttpServletRequest request) {
    	
    	if (logger.isDebugEnabled())
    		logger.debug("processReadMasterQueryPage() - ");
    	
    	Login loginUser = socialWatcherContextUtil.getLoginUser(request);
    	String role = socialWatcherContextUtil.getLoginUserRole(request);
    	
    	if (SocialWatcherUtil.isNotEmptyAndNotNullString(role) && "admin".equals(role)) {
    		request.getSession().removeAttribute("queries");
    		List<Query> queries = null;				
			queries = getQueries();
			if (null != queries && queries.size() > 0){
				if (logger.isDebugEnabled())
					logger.debug("No. of queries: " +queries.size());
				request.getSession().setAttribute("queries", queries);
			}
			return null;
    	} 
    	
    	if (SocialWatcherUtil.isNotEmptyAndNotNullString(role) && "partner".equals(role)) {
    		request.getSession().removeAttribute("queries");
    		Partner partner = partnerService.getByLogin(loginUser);
    		Set<Client> clients = partner.getClients();
    		if (null != clients && clients.size() > 0) {
	    		if(logger.isDebugEnabled()) {
	    			logger.debug("Total clients found for " +partner.getPartnerName()+" is " +clients.size());
	    		}
	    		List<Query> queries = getQueriesByClientIn(clients);
	    		if (null != queries && queries.size() > 0){
	    			if (logger.isDebugEnabled())
	    				logger.debug("No. of queries: " +queries.size());
					request.getSession().setAttribute("queries", queries);
				}
    		}
    		return null;
    	} 
    	
    	if (SocialWatcherUtil.isNotEmptyAndNotNullString(role) && "client".equals(role)) {
    		request.getSession().removeAttribute("queries");
    		Client client = clientService.getByLogin(loginUser);
    		List<Query> queries = getQueriesByClient(client);
			if (null != queries && queries.size() > 0){
				if (logger.isDebugEnabled())
					logger.debug("No. of queries: " +queries.size());
				request.getSession().setAttribute("queries", queries);
			} else {
				if (logger.isDebugEnabled())
					logger.debug("No queries found against " +client.getClientName());
			}
			return null;
    	}    
    	
    	if (SocialWatcherUtil.isNotEmptyAndNotNullString(role) && "user".equals(role)) {
    		request.getSession().removeAttribute("queries");
    		User user = userService.getByLogin(loginUser);
    		Collection<User> users = new ArrayList<User>();
    		users.add(user);
    		Client client = clientService.getByUsersIn(users);
    		List<Query> queries = getQueriesByClient(client);
			if (null != queries && queries.size() > 0){
				if (logger.isDebugEnabled())
					logger.debug("No. of queries: " +queries.size());
				request.getSession().setAttribute("queries", queries);
			} else {
				if (logger.isDebugEnabled())
					logger.debug("No queries found against user " +user.getUserName());
			}
			return null;
    	}    
    	
    	return null;
    }
    
    public String processCreateMasterQueryPage(HttpServletRequest request) {
    	if (logger.isDebugEnabled())
    		logger.debug("processCreateMasterQueryPage() - ");
    	
    	Login loginUser = socialWatcherContextUtil.getLoginUser(request);
    	String role = socialWatcherContextUtil.getLoginUserRole(request);
    	
    	if (SocialWatcherUtil.isNotEmptyAndNotNullString(role) && "admin".equals(role)) {
    		request.getSession().removeAttribute("queries");
    		List<Query> queries = null;				
			queries = getQueries();
			if (null != queries && queries.size() > 0){
				logger.debug("No. of queries: " +queries.size());
				request.getSession().setAttribute("queries", queries);
			}
			return null;
    	} 
    	
    	if (SocialWatcherUtil.isNotEmptyAndNotNullString(role) && "partner".equals(role)) {
    		request.getSession().removeAttribute("queries");
    		Partner partner = partnerService.getByLogin(loginUser);
    		Set<Client> clients = partner.getClients();
    		if (null != clients && clients.size() > 0) {
	    		if(logger.isDebugEnabled()) {
	    			logger.debug("Total clients found for " +partner.getPartnerName()+" is " +clients.size());
	    		}
	    		List<Query> queries = getQueriesByClientIn(clients);
	    		if (null != queries && queries.size() > 0){
					logger.debug("No. of queries: " +queries.size());
					request.getSession().setAttribute("queries", queries);
				}
    		}
			return null;
    	} 
    	
    	if (SocialWatcherUtil.isNotEmptyAndNotNullString(role) && "client".equals(role)) {
    		request.getSession().removeAttribute("queries");
    		Client client = clientService.getByLogin(loginUser);
    		List<Query> queries = getQueriesByClient(client);
			if (null != queries && queries.size() > 0){
				logger.debug("No. of queries: " +queries.size());
				request.getSession().setAttribute("queries", queries);
			} else {
				logger.debug("No queries found against " +client.getClientName());
			}
			return null;
    	}    
    	
    	if (SocialWatcherUtil.isNotEmptyAndNotNullString(role) && "user".equals(role)) {
    		request.getSession().removeAttribute("queries");
    		User user = userService.getByLogin(loginUser);
    		Collection<User> users = new ArrayList<User>();
    		users.add(user);
    		Client client = clientService.getByUsersIn(users);
    		List<Query> queries = getQueriesByClient(client);
			if (null != queries && queries.size() > 0){
				logger.debug("No. of queries: " +queries.size());
				request.getSession().setAttribute("queries", queries);
			} else {
				logger.debug("No queries found against user " +user.getUserName());
			}
			return null;
    	}    
    	return null;
    }
    
    public Collection<Client> fetchClients(HttpServletRequest request) {
    	
    	if (logger.isDebugEnabled())
    		logger.debug("fetchClients() - ");
    	
    	Login loginUser = socialWatcherContextUtil.getLoginUser(request);
    	String role = socialWatcherContextUtil.getLoginUserRole(request);
    	
    	if (SocialWatcherUtil.isNotEmptyAndNotNullString(role) && "admin".equals(role)) {
    		return (Collection<Client>) clientService.getEnabledClients();
    	}
    		
    	if (SocialWatcherUtil.isNotEmptyAndNotNullString(role) && "partner".equals(role)) {
    		Partner partner = partnerService.getByLogin(loginUser);
    		Set<Client> clients = partner.getClients();
    		if (null != clients && clients.size() > 0) {
	    		if(logger.isDebugEnabled()) {
	    			logger.debug("Total clients found for " +partner.getPartnerName()+" is " +clients.size());
	    		}
    		}
    		return (Collection<Client>) clients;
    	}
    	
    	return null;
    }

}
