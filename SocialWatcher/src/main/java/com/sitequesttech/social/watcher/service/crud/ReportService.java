package com.sitequesttech.social.watcher.service.crud;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

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
import com.sitequesttech.social.watcher.web.support.SocialWatcherContextUtil;
import com.sitequesttech.social.watcher.web.view.Report;
import com.sitequesttech.social.watcher.domain.repository.QueryResultRepository;
import com.sitequesttech.social.watcher.common.support.ReadOperationParams;
import com.sitequesttech.social.watcher.common.support.ReadOperationResults;
import com.sitequesttech.social.watcher.common.support.SocialWatcherUtil;

@Service("ReportService")
public class ReportService extends AbstractCrudService<QueryResult>{
	
	private static final Logger logger = Logger
			.getLogger(ReportService.class);
	
	@Autowired
	private QueryResultRepository repository;
	
	@Autowired
	private QueryResultHelper helper; 
	
	@Autowired
	private PartnerService partnerService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private UserService userService; 
	
	@Autowired
	private SocialWatcherContextUtil socialWatcherContextUtil;
	
	@Autowired
	private QueryService queryService;
	
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
	
	public ReadOperationResults read(Query query, ReadOperationParams params) {
		logger.info("read(ReadOperationParams params) - ");
		
        ReadOperationResults result = new ReadOperationResults();
        Direction sortDir = params.getsSortDir_0().equals("asc") ? Direction.ASC : Direction.DESC;
        String sortColName = params.getsColumns().split(",")[params.getiSortCol_0()];
        Sort sort = new Sort(sortDir, sortColName);
        int pageNumber = (int) Math.ceil(params.getiDisplayStart() / params.getiDisplayLength());
        Page<QueryResult> page = null;
        
        page = repository.findByQueryAndIsReviewedTrue(query, new PageRequest(pageNumber, params.getiDisplayLength(), sort));
        
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
	
	public ReadOperationResults read(Collection<Query> queries, String startDate, String endDate, ReadOperationParams params) {
		logger.info("read(ReadOperationParams params) - ");
		
        ReadOperationResults result = new ReadOperationResults();
        Direction sortDir = params.getsSortDir_0().equals("asc") ? Direction.ASC : Direction.DESC;
        String sortColName = params.getsColumns().split(",")[params.getiSortCol_0()];
        Sort sort = new Sort(sortDir, sortColName);
        int pageNumber = (int) Math.ceil(params.getiDisplayStart() / params.getiDisplayLength());
        Page<QueryResult> page = null;
        
		page = repository
				.findByQueryInAndSourceCreatedDateBetweenAndIsReviewedTrue(
						queries, SocialWatcherUtil
								.convertStringToDate(startDate),
						SocialWatcherUtil.convertStringToDate(endDate),
						new PageRequest(pageNumber, params.getiDisplayLength(),
								sort));
        
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
	
	public ReadOperationResults read(Query query, String startDate, String endDate, ReadOperationParams params) {
		logger.info("read(ReadOperationParams params) - ");
		
        ReadOperationResults result = new ReadOperationResults();
        Direction sortDir = params.getsSortDir_0().equals("asc") ? Direction.ASC : Direction.DESC;
        String sortColName = params.getsColumns().split(",")[params.getiSortCol_0()];
        Sort sort = new Sort(sortDir, sortColName);
        int pageNumber = (int) Math.ceil(params.getiDisplayStart() / params.getiDisplayLength());
        Page<QueryResult> page = null;
        
		page = repository
				.findByQueryInAndSourceCreatedDateBetweenAndIsReviewedTrue(
						query, SocialWatcherUtil
								.convertStringToDate(startDate),
						SocialWatcherUtil.convertStringToDate(endDate),
						new PageRequest(pageNumber, params.getiDisplayLength(),
								sort));
        
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
		
		Report report = (Report)request.getSession().getAttribute("currentReportQuery");
		logger.debug("Query Object in list:" +report);
		if (null == params) {
			params = new ReadOperationParams();
			params.setsEcho("1");
			params.setiDisplayLength(10);
			params.setsSearch(null);
			params.setiDisplayLength(10);
			params.setiDisplayStart(0);
			params.setiColumns("4");
			params.setiSortingCols("1");
			params.setiSortCol_0(1);
			params.setsSortDir_0("asc");
			params.setsColumns("id,title,description,url");
		} 
		
		Collection<String> selectedQueries = Arrays.asList(report.getQueries());
		Collection<Query> queries = queryService.getQueriesByQueryTextIn(selectedQueries);
		if (logger.isDebugEnabled())
			logger.debug("#############:" +queries.size());
		String dateRage = report.getDateRange();
		String[] dates = dateRage.split("-");
		return read(queries,dates[0].trim(), dates[1].trim(), params);
	}
	
	public Collection<Collection<String>> fetchQueries(HttpServletRequest request) {
		
		if (logger.isDebugEnabled())
			logger.debug("fetchQueries() - ");
		
		Collection<Collection<String>> queries = new ArrayList<Collection<String>>();
		Collection<String> lqueries = new ArrayList<String>();
		
		Login loginUser = socialWatcherContextUtil.getLoginUser(request);
    	String role = socialWatcherContextUtil.getLoginUserRole(request);
    	
    	if (SocialWatcherUtil.isNotEmptyAndNotNullString(role) && "admin".equals(role)) {
    		int i = 1;
    		for(String query : queryService.getDistinctQueries()) {
    			lqueries.add(query);
    			if(i%3 == 0) {
    				queries.add(lqueries);
    				lqueries = new ArrayList<String>();
    			}
    			i++;
    		}
    		queries.add(lqueries);
    	}
    	
    	if (SocialWatcherUtil.isNotEmptyAndNotNullString(role) && "partner".equals(role)) {
    		Partner partner = partnerService.getByLogin(loginUser);
    		Set<Client> clients = partner.getClients();
    		if (null != clients && clients.size() > 0) {
	    		if(logger.isDebugEnabled()) {
	    			logger.debug("Total clients found for " +partner.getPartnerName()+" is " +clients.size());
	    		}
	    		Collection<String> pqueries= new ArrayList<String>();
	    		for(Client client: clients) {
	    			List<String> dqueries = queryService.getDistinctQueriesByClient(client);
	    			if (null != dqueries && dqueries.size() > 0)
	    				pqueries.addAll(dqueries);
	    		}
	    		int i = 1;
	    		for (String query : pqueries) {
	    			lqueries.add(query);
	    			if(i%3 == 0) {
	    				queries.add(lqueries);
	    				lqueries = new ArrayList<String>();
	    			}
	    			i++;
				}
	    		queries.add(lqueries);
    		}
    	}
    	
    	if (SocialWatcherUtil.isNotEmptyAndNotNullString(role) && "client".equals(role)) {
    		Client client = clientService.getByLogin(loginUser);
    		Collection<String> cqueries = queryService.getDistinctQueriesByClient(client);
    		int i = 1;
    		for (String query : cqueries) {
    			lqueries.add(query);
    			if(i%3 == 0) {
    				queries.add(lqueries);
    				lqueries = new ArrayList<String>();
    			}
    			i++;
			}
    		queries.add(lqueries);
    	}	
		
    	if (SocialWatcherUtil.isNotEmptyAndNotNullString(role) && "user".equals(role)) {
    		User user = userService.getByLogin(loginUser);
    		Collection<User> users = new ArrayList<User>();
    		users.add(user);
    		Client client = clientService.getByUsersIn(users);
    		Collection<String> cqueries = queryService.getDistinctQueriesByClient(client);
    		int i = 1;
    		for (String query : cqueries) {
    			lqueries.add(query);
    			if(i%3 == 0) {
    				queries.add(lqueries);
    				lqueries = new ArrayList<String>();
    			}
    			i++;
			}
    		queries.add(lqueries);
    	}
		
    	return queries;
	}
}
