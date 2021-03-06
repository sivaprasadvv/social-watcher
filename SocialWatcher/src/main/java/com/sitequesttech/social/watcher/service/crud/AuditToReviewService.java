package com.sitequesttech.social.watcher.service.crud;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import com.sitequesttech.social.watcher.domain.entity.Client;
import com.sitequesttech.social.watcher.domain.entity.Login;
import com.sitequesttech.social.watcher.domain.entity.Partner;
import com.sitequesttech.social.watcher.domain.entity.Query;
import com.sitequesttech.social.watcher.domain.entity.QueryResult;
import com.sitequesttech.social.watcher.domain.entity.User;
import com.sitequesttech.social.watcher.service.helper.EntityHelper;
import com.sitequesttech.social.watcher.service.helper.QueryResultHelper;
import com.sitequesttech.social.watcher.web.support.LoginContextUtil;
import com.sitequesttech.social.watcher.web.support.SocialWatcherContextUtil;
import com.sitequesttech.social.watcher.domain.repository.QueryResultRepository;
import com.sitequesttech.social.watcher.common.support.ReadOperationParams;
import com.sitequesttech.social.watcher.common.support.ReadOperationResults;
import com.sitequesttech.social.watcher.common.support.SocialWatcherUtil;

@Service("AuditToReviewService")
public class AuditToReviewService extends AbstractCrudService<QueryResult>{
	
	private static final Logger logger = Logger
			.getLogger(AuditToReviewService.class);
	
	@Autowired
	private QueryResultRepository repository;
	
	@Autowired
	private QueryResultHelper helper; 
	
	@Autowired
	private SocialWatcherContextUtil socialWatcherContextUtil;
	
	@Autowired
    private ClientService clientService;
	
	@Autowired
	private PartnerService partnerService;
	
	@Autowired
	private QueryService queryService;
	
	@Autowired
	private UserService userService;
	
	@Override
    public PagingAndSortingRepository<QueryResult, Long> getRepository() {
        return this.repository;
    }
 
	 public List<QueryResult> getQueryResultsByQuery(Query query) {
		 return repository.findByQuery(query);
	 }

	@Override
    public EntityHelper<QueryResult> getHelper() {
        return this.helper;
    }
	
	public ReadOperationResults read(ReadOperationParams params) {
		logger.info("read(ReadOperationParams params) - ");
        ReadOperationResults result = new ReadOperationResults();
        Direction sortDir = params.getsSortDir_0().equals("asc") ? Direction.ASC : Direction.DESC;
        String sortColName = params.getsColumns().split(",")[params.getiSortCol_0()];
        Sort sort = new Sort(sortDir, sortColName);
        int pageNumber = (int) Math.ceil(params.getiDisplayStart() / params.getiDisplayLength());
        Page<QueryResult> page = null;
        
        page = repository.findByIsReviewedIsNullAndIsDeletedIsNull(new PageRequest(pageNumber, params.getiDisplayLength(), sort));
        
        List<QueryResult> data = page.getContent();
        List<QueryResult> uiDate = new ArrayList<QueryResult>();
        for (QueryResult entity : data) {
            uiDate.add(getHelper().copyFrom(entity));
        }
        result.setsEcho(params.getsEcho());
        result.setiTotalDisplayRecords(page.getTotalElements());
        result.setiTotalRecords(page.getTotalElements());
        result.setAaData(uiDate);
        return result;
    }		
	
	/*
	 * 
	 */
	public ReadOperationResults read(ReadOperationParams params, Collection<Query> queries) {
		
		logger.info("read(ReadOperationParams params) - ");
		
		ReadOperationResults result = new ReadOperationResults();
        Direction sortDir = params.getsSortDir_0().equals("asc") ? Direction.ASC : Direction.DESC;
        String sortColName = params.getsColumns().split(",")[params.getiSortCol_0()];
        Sort sort = new Sort(sortDir, sortColName);
        int pageNumber = (int) Math.ceil(params.getiDisplayStart() / params.getiDisplayLength());
        Page<QueryResult> page = null;
		
        if (null != queries && queries.size() > 0 ) {
        	page = repository.findByQueryInAndIsReviewedIsNullAndIsDeletedIsNull(queries, new PageRequest(pageNumber, params.getiDisplayLength(), sort));
        } else {
        	page = repository.findByIsReviewedIsNullAndIsDeletedIsNull(new PageRequest(pageNumber, params.getiDisplayLength(), sort));
        }
		
        List<QueryResult> data = page.getContent();
        List<QueryResult> uiDate = new ArrayList<QueryResult>();
        for (QueryResult entity : data) {
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
		
		List<Query> queries = null;
		if (SocialWatcherUtil.isNotEmptyAndNotNullString(role)) {
			
			if ("partner".equals(role)){
				Partner partner = partnerService.getByLogin(loginUser);
	    		Set<Client> clients = partner.getClients();
	    		if (null != clients && clients.size() > 0) {
		    		if(logger.isDebugEnabled()) {
		    			logger.debug("Total clients found for " +partner.getPartnerName()+" is " +clients.size());
		    		}
		    		queries = queryService.getQueriesByClientIn(clients);
	    		}
			} 
			if ("client".equals(role)){
				Client client = clientService.getByLogin(loginUser);
	    		queries = queryService.getQueriesByClient(client);
			} 
			if ("user".equals(role)){
				User user = userService.getByLogin(loginUser);
	    		Collection<User> users = new ArrayList<User>();
	    		users.add(user);
	    		Client client = clientService.getByUsersIn(users);
	    		 queries = queryService.getQueriesByClient(client);
			}
		}		
		
		return read(params, queries);
	}
	
	public Map<String, ? extends Object> processUpdate(HttpServletRequest request, 
			HttpServletResponse response, Long id, QueryResult entity) {
		
		if (logger.isDebugEnabled())
			logger.debug("processUpdate() - ");
		
		LoginContextUtil userContextUtil = (LoginContextUtil)request.getAttribute("userContext");
    	Login user = userContextUtil.getUser();
    	if (logger.isDebugEnabled())
    		logger.debug("logged in user id/username:" +user.getId()+"/"+user.getName());
    	entity.setReviewedBy(user.getId());
    	entity.setReviewedDate(new Date());
    	entity.setModifiedBy(user.getId());
    	entity.setModifiedDate(new Date());
		
		QueryResult entityToUpdate = getById(id);
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
