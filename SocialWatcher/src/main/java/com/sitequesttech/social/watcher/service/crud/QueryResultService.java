package com.sitequesttech.social.watcher.service.crud;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import com.sitequesttech.social.watcher.domain.entity.Query;
import com.sitequesttech.social.watcher.domain.entity.QueryResult;
import com.sitequesttech.social.watcher.common.exception.SocialWatcherException;
import com.sitequesttech.social.watcher.service.helper.EntityHelper;
import com.sitequesttech.social.watcher.service.helper.QueryResultHelper;
import com.sitequesttech.social.watcher.domain.repository.QueryResultRepository;
import com.sitequesttech.social.watcher.service.search.FacebookSearcher;
import com.sitequesttech.social.watcher.service.search.TwitterSearcher;
import com.sitequesttech.social.watcher.common.support.SocialWatcherConstants;
import com.sitequesttech.social.watcher.common.support.ReadOperationParams;
import com.sitequesttech.social.watcher.common.support.ReadOperationResults;
import com.sitequesttech.social.watcher.common.support.SocialWatcherUtil;

@Service("QueryResultService")
public class QueryResultService extends AbstractCrudService<QueryResult>{
	
	private static final Logger logger = Logger
			.getLogger(QueryResultService.class);
	
	@Autowired
	private QueryResultRepository repository;
	
	@Autowired
	private QueryResultHelper helper; 
	
	@Autowired
	private TwitterSearcher twitterSearcher;
	
	@Autowired
	private FacebookSearcher facebookSearcher;	
	
	@Override
	public PagingAndSortingRepository<QueryResult, Long> getRepository() {
		return this.repository;
	}
	 
	public List<QueryResult> getQueryResultsByQuery(Query query) {
		return repository.findByQuery(query);
	}
	
	public List<QueryResult> getPositiveReviewedQueryResultsByQuery(Query query) {
		List<QueryResult> copyResuts = new ArrayList<QueryResult>();
		List<QueryResult> results = repository.findByQueryAndIsReviewedTrue(query);
		for (QueryResult queryResult : results) {
			copyResuts.add(helper.copyFromForExport(queryResult));
		}
		return copyResuts;
	}
	
	public List<QueryResult> getPositiveReviewedQueryResultsByQuery(Collection<Query> query, String[] reportFields) {
		List<QueryResult> copyResuts = new ArrayList<QueryResult>();
		List<QueryResult> results = repository.findByQueryInAndIsReviewedTrue(query);
		for (QueryResult queryResult : results) {
			copyResuts.add(helper.copyFromForExportByFields(queryResult,reportFields));
		}
		return copyResuts;
	}
	
	public List<QueryResult> getPositiveReviewedQueryResultsByQueryInDateRange(Collection<Query> query, String fromDate, String endDate, String[] reportFields) {
		List<QueryResult> copyResuts = new ArrayList<QueryResult>();
		List<QueryResult> results = repository.findByQueryInAndSourceCreatedDateBetweenAndIsReviewedTrue(query,SocialWatcherUtil
				.convertStringToDate(fromDate),SocialWatcherUtil
				.convertStringToDate(endDate));
		for (QueryResult queryResult : results) {
			copyResuts.add(helper.copyFromForExportByFields(queryResult,reportFields));
		}
		return copyResuts;
	}
	
	@Override
    public EntityHelper<QueryResult> getHelper() {
        return this.helper;
    }
	
	/**
	 * 
	 * @param query
	 * @param params
	 * @return
	 */
	public ReadOperationResults read(Query query, ReadOperationParams params) {
		logger.debug("In read/query:" +query);
        ReadOperationResults result = new ReadOperationResults();
        Direction sortDir = params.getsSortDir_0().equals("asc") ? Direction.ASC : Direction.DESC;
        String sortColName = params.getsColumns().split(",")[params.getiSortCol_0()];
        Sort sort = new Sort(sortDir, sortColName);
        int pageNumber = (int) Math.ceil(params.getiDisplayStart() / params.getiDisplayLength());
        Page<QueryResult> page = null;
        if (null != query) {
        	page = repository.findByQuery(query, new PageRequest(pageNumber, params.getiDisplayLength(), sort));
        } else {
        	page = getRepository().findAll(new PageRequest(pageNumber, params.getiDisplayLength(), sort));
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
        logger.debug("read(Query query, ReadOperationParams params):" +result);
        return result;
    }
	
	/**
	 * 
	 * @param searchEngine
	 * @param query
	 * @return
	 * @throws SocialWatcherException
	 */
	public Set<QueryResult> getQueryResponse(String searchEngine, Query query) throws SocialWatcherException {
		
		logger.debug("In getQueryResponse/ query:" +query.getQueryText()+"/search engine:" +searchEngine);
		
		String encodedQuery = "";
		try {
			encodedQuery = URLEncoder.encode( query.getQueryText() , "UTF8" );
			logger.debug("Encoded query :" +encodedQuery);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new SocialWatcherException(e);
		} 
		
		if (SocialWatcherConstants.TWITTER_SEARCH_ENGINE.equals(searchEngine)) {
			try {
				Set<QueryResult> qrList = twitterSearcher.getQueryResults(query);
				return qrList;
			} catch(Exception ex){
				ex.printStackTrace();
				throw new SocialWatcherException(ex);
			}
				
		} 
		
		if (SocialWatcherConstants.FACEBOOK_SEARCH_ENGINE.equals(searchEngine)) {
			try {
				Set<QueryResult> qrList = facebookSearcher.getQueryResults(query);
				return qrList;
			} catch(Exception ex){
				ex.printStackTrace();
				throw new SocialWatcherException(ex);
			}
				
		} 
		
		return null;
		
	}
	
	
	//Override this to achieve not to insert duplicate tweet/post ids
	 /**
     * Create opeation for list
     * 
     * @param entity
     * @return created entity
     */
    public Iterable<QueryResult> create(Iterable<QueryResult> entities) {
    	List<QueryResult> list = new ArrayList<QueryResult>();
    	for (QueryResult queryResult : entities) {
			try {
				QueryResult result = getRepository().save(queryResult);
				list.add(result);
			} catch (Exception ex) {
				
			}
		}
        return list;
    }
	
}
