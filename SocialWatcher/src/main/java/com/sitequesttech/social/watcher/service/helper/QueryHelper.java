package com.sitequesttech.social.watcher.service.helper;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.sitequesttech.social.watcher.domain.entity.Query;
import com.sitequesttech.social.watcher.common.support.ValueGenerator;

@Component
public class QueryHelper  implements EntityHelper<Query> {
	
	private static final Logger logger = Logger
			.getLogger(QueryHelper.class);

    @Override
    public Query createEntityInstance() {
        return new Query();
    }

    @Override
    public Query createRandomEntity() {
        Query entity = new Query();
        entity.setQueryText(ValueGenerator.getUniqueString(10));
        return entity;
    }

    @Override
    public Query copyFrom(Query entity) {
    	Query copy = new Query();
        copy.setId(entity.getId());
        copy.setQueryText(entity.getQueryText());
        copy.setCreatedDate(entity.getCreatedDate());
        copy.setCreatedBy(entity.getCreatedBy());
        if (logger.isDebugEnabled())
        	logger.debug(entity.getSocialMedia().getName());
        copy.setSocialMedia(entity.getSocialMedia());
        return copy;
    }

    @Override
    public Query copyWithoutPkFrom(Query entity) {
    	Query copy = new Query();
        copy.setQueryText(entity.getQueryText());
        copy.setCreatedDate(entity.getCreatedDate());
        copy.setCreatedBy(entity.getCreatedBy());
        if (logger.isDebugEnabled())
        	logger.debug(entity.getSocialMedia().getName());
        copy.setSocialMedia(entity.getSocialMedia());
        return copy;
    }

    @Override
    public Query updateFrom(Query fromentity, Query toEntity) {
        toEntity.setQueryText(fromentity.getQueryText());
        return toEntity;
    }

}
