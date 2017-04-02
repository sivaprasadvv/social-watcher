package com.sitequesttech.social.watcher.domain.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sitequesttech.social.watcher.domain.entity.Client;
import com.sitequesttech.social.watcher.domain.entity.Query;

/**
 * Query repository
 * 
 * @author sivaprasad.vindula@icentris.com
 *
 */
public interface QueryRepository extends JpaRepository<Query, Long> {

	public Query findByQueryText(String queryText);
	
	public List<Query> findByQueryTextIn(Collection<String> queryTexts);
    
	public List<Query> findBySocialMediaId(long socialMediaId);
    
	public List<Query> findByClient(Client client);
    
	public Query findByQueryTextAndClient(String queryText, Client client);
    
	public List<Query> findByClientIn(Collection<Client> clients);
    
	public Query findByQueryTextAndClientAndSocialMediaId(String queryText, Client client, Long socialMedia);
    
	public List<Query> findByQueryTextAndClientAndSocialMediaIdIn(String queryText, Client client, Collection<Long> socialMedia);
    
	@org.springframework.data.jpa.repository.Query("select count(q) from Query q where q.client = ?1")
	public long findQueryCountByClient(Client client);
	
	@org.springframework.data.jpa.repository.Query("select DISTINCT(q.queryText) from Query q")
	public List<String> findDistinctQueryText();
	
	@org.springframework.data.jpa.repository.Query("select DISTINCT(q.queryText) from Query q where q.client = ?1")
	public List<String> findDistinctQueryTextByClient(Client client);
    
}
