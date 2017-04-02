package com.sitequesttech.social.watcher.scheduler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitequesttech.social.watcher.common.exception.SocialWatcherException;
import com.sitequesttech.social.watcher.common.support.SocialWatcherConstants;
import com.sitequesttech.social.watcher.common.support.SocialWatcherUtil;
import com.sitequesttech.social.watcher.domain.entity.Client;
import com.sitequesttech.social.watcher.domain.entity.Login;
import com.sitequesttech.social.watcher.domain.entity.Query;
import com.sitequesttech.social.watcher.domain.entity.QueryResult;
import com.sitequesttech.social.watcher.domain.entity.SocialMedia;
import com.sitequesttech.social.watcher.service.crud.QueryResultService;
import com.sitequesttech.social.watcher.service.crud.QueryService;
import com.sitequesttech.social.watcher.service.mail.MailService;

@Service("QuartzQueryScheduler")
public class QuartzQueryScheduler {
	
	private static final Logger logger = Logger
			.getLogger(QuartzQueryScheduler.class);
	
	@Autowired
	private QueryService queryService;
	
	@Autowired
	private QueryResultService queryResultService;
	
	@Autowired
	private MailService mailService;
	
	public void execute() {
		
		logger.info("execute() - ");
		
		if (SocialWatcherUtil.checkBooleanIsTrue(SocialWatcherConstants.SCHEDULER_QUARTZ_SERVICE_ENABLE)) {
			List<Query> queries = queryService.getQueries();
			for (Query query : queries) {
				if (logger.isDebugEnabled()) {
					logger.debug("SearchWord :" +query.getQueryText());
					logger.debug("Social Media :" +query.getSocialMedia().getName());
				}
				SocialMedia socialMedia = query.getSocialMedia();
				try {
					Set<QueryResult> queryResults = queryResultService.getQueryResponse(socialMedia.getName(), query);
					List<QueryResult> results = (List<QueryResult>) queryResultService.create(queryResults);
					if (null != results && results.size() > 0) {
						logger.debug("Total records processed:" +results.size());
						
						if (SocialWatcherUtil.checkBooleanIsTrue(SocialWatcherConstants.MAIL_SERVICE_ENABLE)) {
							logger.debug("In Mail content preparation...");
							
							Client client = query.getClient();
							Login loginUser = client.getLogin();
							
							Collection<String> bodyText = new ArrayList<String>();
							bodyText.add("Content"+"\t"+"Url");
							for (QueryResult queryResult : results) {
								if (SocialWatcherUtil.isQueryTextInResultText(query.getQueryText(), queryResult.getDescription())) {
									logger.debug("Text found...");
									bodyText.add(queryResult.getDescription()+"\t"+queryResult.getUrl());
									//mailService.sendMail(loginUser.getName(), SocialWatcherConstants.MAIL_SUBJECT, queryResult.getDescription());
								}
							}
							
							try {
								mailService.sendHtmlMail(query.getQueryText(), client.getClientName(), null, loginUser.getName(), SocialWatcherConstants.MAIL_SUBJECT, bodyText);
							} catch (MessagingException e) {
								e.printStackTrace();
							}
							
						}
					}
						
				} catch (SocialWatcherException e) {
					logger.error(e);
				}
			}
		} else {
			logger.debug("Quartz scheduer service is not enabled. Please check.");
		}
		
	}
	
}
