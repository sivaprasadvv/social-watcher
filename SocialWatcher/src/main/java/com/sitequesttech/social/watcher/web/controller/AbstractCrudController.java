package com.sitequesttech.social.watcher.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sitequesttech.social.watcher.service.helper.EntityHelper;
import com.sitequesttech.social.watcher.service.crud.AbstractCrudService;


public abstract class AbstractCrudController<T> extends AbstractRestController<T> {
	
	private static final Logger logger = Logger
			.getLogger(AbstractCrudController.class);

    private static final String MODEL_BEAN_ID = "crudObj";

    protected abstract String getListPageName();

    protected abstract String getCreatePageName();

    protected abstract String getUpdatePageName();

    protected abstract AbstractCrudService<T> getService();

    protected abstract EntityHelper<T> getHelper();

    /**
     * Getting the listing page
     * 
     * @return page
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String getListPage() {
    	logger.debug("In getListPage");
	return getListPageName();
    }

    @RequestMapping(value = "create", method = GET)
    public String getCreatePage() {
    	logger.debug("In getCreatePage");
	return getCreatePageName();
    }

    @RequestMapping(value = "update/{id}", method = GET)
    public String update(@PathVariable("id") Long id, Model model) {
    	logger.debug("In update: id" +id);
	T entity = getService().getById(id);
	model.addAttribute(MODEL_BEAN_ID, entity != null ? getHelper().copyFrom(entity) : getHelper()
		.createEntityInstance());
	return getUpdatePageName();
    }

}
