package com.sitequesttech.social.watcher.domain.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sitequesttech.social.watcher.domain.entity.Query;
import com.sitequesttech.social.watcher.domain.entity.QueryResult;

public interface QueryResultRepository extends PagingAndSortingRepository<QueryResult, Long> {
	
	List<QueryResult> findByQuery(Query query);
	
	Page<QueryResult> findByQuery(Query query, Pageable pageable);
	
	Page<QueryResult> findByIsReviewedIsNullAndIsDeletedIsNull(Pageable pageable);
	
	Page<QueryResult> findByIsReviewedIsNotNull(Pageable pageable);
	
	Page<QueryResult> findByQueryAndIsReviewedTrue(Query query, Pageable pageable);
	
	List<QueryResult> findByQueryAndIsReviewedTrue(Query query);
	
	Page<QueryResult> findByQueryInAndIsReviewedTrue(Collection<Query> queries, Pageable pageable);
	
	Page<QueryResult> findByQueryInAndSourceCreatedDateBetweenAndIsReviewedTrue(
			Collection<Query> queries, Date startDate, Date endDate,
			Pageable pageable);
	
	List<QueryResult> findByQueryInAndIsReviewedTrue(Collection<Query> queries);
	
	List<QueryResult> findByQueryInAndSourceCreatedDateBetweenAndIsReviewedTrue(Collection<Query> queries, Date startDate, Date endDate);
	
	Page<QueryResult> findByQueryInAndSourceCreatedDateBetweenAndIsReviewedTrue(
			Query query, Date startDate, Date endDate,
			Pageable pageable);
	
	Page<QueryResult> findByQueryInAndIsReviewedIsNullAndIsDeletedIsNull(Collection<Query> queries, Pageable pageable);
	
	Page<QueryResult> findByQueryInAndIsReviewedIsNotNull(Collection<Query> queries, Pageable pageable);

}
	