package com.sitequesttech.social.watcher.service.helper;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.sitequesttech.social.watcher.domain.entity.Query;
import com.sitequesttech.social.watcher.domain.entity.QueryResult;
import com.sitequesttech.social.watcher.common.support.SocialWatcherConstants;
import com.sitequesttech.social.watcher.common.support.SocialWatcherUtil;


/**
 * Query Result Helper 
 * 
 * @author sivaprasad.vindula@icentris.com
 *
 */
@Component
public class QueryResultHelper implements EntityHelper<QueryResult> {
	
	private static final Logger logger = Logger
			.getLogger(QueryResultHelper.class);

    @Override
    public QueryResult createEntityInstance() {
        return new QueryResult();
    }

    @Override
    public QueryResult createRandomEntity() {
    	QueryResult entity = new QueryResult();
        return entity;
    }

    @Override
    public QueryResult copyFrom(QueryResult entity) {
    	QueryResult copy = new QueryResult();
        copy.setId(entity.getId());
        copy.setTitle(entity.getTitle());
        copy.setUrl(entity.getUrl());
        copy.setCreatedBy(entity.getCreatedBy());
        copy.setCreatedDate(entity.getCreatedDate());
        copy.setRank(entity.getRank());
        copy.setIsReviewed(entity.getIsReviewed());
        
        String profileUrl = entity.getProfileImageUrl();
        if (SocialWatcherUtil.isEmptyOrNullString(profileUrl) )
			profileUrl = SocialWatcherConstants.NO_IMAGE_URL;
		
		if (SocialWatcherUtil.isNotEmptyAndNotNullString(profileUrl))
			profileUrl = "<img src=\"" + profileUrl + "\" height="+SocialWatcherConstants.IMAGE_DIMENSION_HEIGHT+" width="+SocialWatcherConstants.IMAGE_DIMENSION_WIDTH+"/>";
		logger.debug(profileUrl);
        copy.setProfileImageUrl(profileUrl);
        copy.setComment(entity.getComment());
        copy.setSourceId(entity.getSourceId());
        copy.setSourceCreatedDate(entity.getSourceCreatedDate());
        copy.setSourceCreatedAt(SocialWatcherUtil.convertDateToString(entity.getSourceCreatedDate()));
        
        logger.debug(entity.getQuery().getId()+"/"+entity.getQuery().getQueryText()+"/"+entity.getQuery().getSocialMedia().getName());
        Query query = new Query();
        query.setSocialMedia(entity.getQuery().getSocialMedia());
        query.setId(entity.getQuery().getId());
        query.setQueryText(entity.getQuery().getQueryText());
        copy.setQuery(query);
        
        copy.setDescription(SocialWatcherUtil.highlightQueryTextInResultText(entity.getQuery().getQueryText(), entity.getDescription()));
        
        String socialMediaName = entity.getQuery().getSocialMedia().getName();
        copy.setSocialMediaName(socialMediaName);
        logger.debug("Query text in query result:" +copy.getSocialMediaName()+"/"+socialMediaName);
        if (SocialWatcherUtil.isNotEmptyAndNotNullString(socialMediaName)) {
        	if (SocialWatcherConstants.FACEBOOK_SEARCH_ENGINE.trim().equals(socialMediaName)) {
        		//copy.setSearchApiLogo("<img src=\"" + SocialWatcherConstants.FACEBOOK_IMAGE_URL + "\"/>");
        		copy.setSocialMediaLogo("<img src=\"" + SocialWatcherConstants.FACEBOOK_IMAGE_URL + "\" height="+SocialWatcherConstants.IMAGE_DIMENSION_HEIGHT+" width="+SocialWatcherConstants.IMAGE_DIMENSION_WIDTH+"/>");
        	}
        	if (SocialWatcherConstants.TWITTER_SEARCH_ENGINE.trim().equals(socialMediaName)) {
        		//copy.setSearchApiLogo("<img src=\"" + SocialWatcherConstants.TWITTER_IMAGE_URL + "\"/>");
        		copy.setSocialMediaLogo("<img src=\"" + SocialWatcherConstants.TWITTER_IMAGE_URL + "\" height="+SocialWatcherConstants.IMAGE_DIMENSION_HEIGHT+" width="+SocialWatcherConstants.IMAGE_DIMENSION_WIDTH+"/>");
        	}
        }
        
        copy.setIsDeleted(entity.getIsDeleted());
        copy.setPlace(entity.getPlace());
        
        return copy;
    }

    @Override
    public QueryResult copyWithoutPkFrom(QueryResult entity) {
    	QueryResult copy = new QueryResult();
        copy.setTitle(entity.getTitle());
        copy.setUrl(entity.getUrl());
        copy.setCreatedBy(entity.getCreatedBy());
        copy.setCreatedDate(entity.getCreatedDate());
        copy.setRank(entity.getRank());
        copy.setIsReviewed(entity.getIsReviewed());
        
        String profileUrl = entity.getProfileImageUrl();
        if (SocialWatcherUtil.isEmptyOrNullString(profileUrl) )
			profileUrl = SocialWatcherConstants.NO_IMAGE_URL;
		
		if (SocialWatcherUtil.isNotEmptyAndNotNullString(profileUrl))
			profileUrl = "<img src=\"" + profileUrl + "\"/>";
		
        copy.setProfileImageUrl(profileUrl);
        copy.setComment(entity.getComment());
        copy.setSourceId(entity.getSourceId());
        copy.setSourceCreatedDate(entity.getSourceCreatedDate());
        copy.setSourceCreatedAt(SocialWatcherUtil.convertDateToString(entity.getSourceCreatedDate()));
        
        logger.debug(entity.getQuery().getId()+"/"+entity.getQuery().getQueryText()+"/"+entity.getQuery().getSocialMedia().getName());
        Query query = new Query();
        query.setSocialMedia(entity.getQuery().getSocialMedia());
        query.setId(entity.getQuery().getId());
        query.setQueryText(entity.getQuery().getQueryText());
        copy.setQuery(query);
        
        
        copy.setDescription(SocialWatcherUtil.highlightQueryTextInResultText(entity.getQuery().getQueryText(), entity.getDescription()));
        String socialMediaName = entity.getQuery().getSocialMedia().getName();
        copy.setSocialMediaName(socialMediaName);
        logger.debug("Query text in query result:" +copy.getSocialMediaName()+"/"+socialMediaName);
        if (SocialWatcherUtil.isNotEmptyAndNotNullString(socialMediaName)) {
        	if (SocialWatcherConstants.FACEBOOK_SEARCH_ENGINE.trim().equals(socialMediaName)) {
        		copy.setSocialMediaLogo("<img src=\"" + SocialWatcherConstants.FACEBOOK_IMAGE_URL + "\"/>");
        	}
        	if (SocialWatcherConstants.TWITTER_SEARCH_ENGINE.trim().equals(socialMediaName)) {
        		copy.setSocialMediaLogo("<img src=\"" + SocialWatcherConstants.TWITTER_IMAGE_URL + "\"/>");
        	}
        }
        copy.setIsDeleted(entity.getIsDeleted());
        copy.setPlace(entity.getPlace());
        
        return copy;
    }

    @Override
    public QueryResult updateFrom(QueryResult fromentity, QueryResult toEntity) {
    	
    	if (SocialWatcherUtil.isNotEmptyAndNotNullString(fromentity.getComment()))
    		toEntity.setComment(fromentity.getComment());
    	if (SocialWatcherUtil.isNotEmptyAndNotNullString(fromentity.getIsDeleted()))
    		toEntity.setIsDeleted(fromentity.getIsDeleted());
        toEntity.setIsReviewed(fromentity.getIsReviewed());
        toEntity.setReviewedDate(fromentity.getReviewedDate());
        toEntity.setReviewedBy(fromentity.getReviewedBy());
        if (SocialWatcherUtil.isNotNullObject(fromentity.getModifiedBy()))
        	toEntity.setModifiedBy(fromentity.getModifiedBy());
        if (SocialWatcherUtil.isNotNullObject(fromentity.getModifiedDate()))
        	toEntity.setModifiedDate(fromentity.getModifiedDate());
        
        return toEntity;
    }
    
    public QueryResult copyFromForExport(QueryResult entity) {
    	QueryResult copy = new QueryResult();
        copy.setId(entity.getId());
        copy.setTitle(entity.getTitle());
        copy.setUrl(entity.getUrl());
        copy.setCreatedBy(entity.getCreatedBy());
        copy.setCreatedDate(entity.getCreatedDate());
        copy.setRank(entity.getRank());
        copy.setIsReviewed(entity.getIsReviewed());
        
        String profileUrl = entity.getProfileImageUrl();
        if (SocialWatcherUtil.isEmptyOrNullString(profileUrl) )
			profileUrl = SocialWatcherConstants.NO_IMAGE_URL;
		
		logger.debug(profileUrl);
        copy.setProfileImageUrl(profileUrl);
        copy.setComment(entity.getComment());
        copy.setSourceId(entity.getSourceId());
        copy.setSourceCreatedDate(entity.getSourceCreatedDate());
        copy.setSourceCreatedAt(SocialWatcherUtil.convertDateToString(entity.getSourceCreatedDate()));
        
        logger.debug(entity.getQuery().getId()+"/"+entity.getQuery().getQueryText()+"/"+entity.getQuery().getSocialMedia().getName());
        Query query = new Query();
        query.setSocialMedia(entity.getQuery().getSocialMedia());
        query.setId(entity.getQuery().getId());
        query.setQueryText(entity.getQuery().getQueryText());
        copy.setQuery(query);
        
        copy.setDescription(entity.getDescription());
        String socialMedia = entity.getQuery().getSocialMedia().getName();
        copy.setSocialMediaName(socialMedia);
        logger.debug("Query text in query result:" +copy.getSocialMediaName()+"/"+socialMedia);
        if (SocialWatcherUtil.isNotEmptyAndNotNullString(socialMedia)) {
        	if (SocialWatcherConstants.FACEBOOK_SEARCH_ENGINE.trim().equals(socialMedia)) {
        		copy.setSocialMediaLogo(SocialWatcherConstants.FACEBOOK_IMAGE_URL);
        	}
        	if (SocialWatcherConstants.TWITTER_SEARCH_ENGINE.trim().equals(socialMedia)) {
        		copy.setSocialMediaLogo(SocialWatcherConstants.TWITTER_IMAGE_URL);
        	}
        }
        copy.setIsDeleted(entity.getIsDeleted());
        copy.setPlace(entity.getPlace());
        
        return copy;
    }
    
    public QueryResult copyFromForExportByFields(QueryResult entity, String[] reportFields) {
    	QueryResult copy = new QueryResult();
    	copy.setId(entity.getId());
    	for(String reportField : reportFields) {
    		if ("Name".equalsIgnoreCase(reportField)) {
    			 copy.setTitle(entity.getTitle());
    		} else if ("Description".equalsIgnoreCase(reportField)) {
    			copy.setDescription(entity.getDescription());
    		} else if ("Url".equalsIgnoreCase(reportField)) {
    			copy.setUrl(entity.getUrl());
    		} else if ("Source".equalsIgnoreCase(reportField)) {
    			logger.debug(entity.getQuery().getId()+"/"+entity.getQuery().getQueryText()+"/"+entity.getQuery().getSocialMedia().getName());
    			Query query = new Query();
    	        query.setSocialMedia(entity.getQuery().getSocialMedia());
    	        query.setId(entity.getQuery().getId());
    	        query.setQueryText(entity.getQuery().getQueryText());
    	        copy.setQuery(query);
    			String socialMediaName = entity.getQuery().getSocialMedia().getName();
    	        copy.setSocialMediaName(socialMediaName);
    	        if (logger.isDebugEnabled())
    	        	logger.debug("Query text in query result:" +copy.getSocialMediaName()+"/"+socialMediaName);
    	        if (SocialWatcherUtil.isNotEmptyAndNotNullString(socialMediaName)) {
    	        	if (SocialWatcherConstants.FACEBOOK_SEARCH_ENGINE.trim().equals(socialMediaName)) {
    	        		copy.setSocialMediaLogo(SocialWatcherConstants.FACEBOOK_IMAGE_URL);
    	        	}
    	        	if (SocialWatcherConstants.TWITTER_SEARCH_ENGINE.trim().equals(socialMediaName)) {
    	        		copy.setSocialMediaLogo(SocialWatcherConstants.TWITTER_IMAGE_URL);
    	        	}
    	        }
    		} else if ("ProfileImage".equalsIgnoreCase(reportField)) {
    			String profileUrl = entity.getProfileImageUrl();
    	        if (SocialWatcherUtil.isEmptyOrNullString(profileUrl) )
    				profileUrl = SocialWatcherConstants.NO_IMAGE_URL;
    			if (logger.isDebugEnabled())
    				logger.debug(profileUrl);
    	        copy.setProfileImageUrl(profileUrl);
    		} else if ("IsReviewed".equalsIgnoreCase(reportField)) {
    			copy.setIsReviewed(entity.getIsReviewed());
    		} else if ("CreatedBy".equalsIgnoreCase(reportField)) {
    			copy.setCreatedBy(entity.getCreatedBy());
    		} else if ("CreatedDate".equalsIgnoreCase(reportField)) {
    			copy.setCreatedDate(entity.getCreatedDate());
    		} else if ("Comment".equalsIgnoreCase(reportField)) {
    			copy.setComment(entity.getComment());
    		} else if ("SourceId".equalsIgnoreCase(reportField)) {
    			copy.setSourceId(entity.getSourceId());
    		} else if ("SourceCreatedDate".equalsIgnoreCase(reportField)) {
    			copy.setSourceCreatedDate(entity.getSourceCreatedDate());
    	        copy.setSourceCreatedAt(SocialWatcherUtil.convertDateToString(entity.getSourceCreatedDate()));    	        
    		} else if ("IsDeleted".equalsIgnoreCase(reportField)) {
    			 copy.setIsDeleted(entity.getIsDeleted());
    		} else if ("Place".equalsIgnoreCase(reportField)) {
    			 copy.setPlace(entity.getPlace());
    		} 
    		
    	}
        return copy;
    }
}
