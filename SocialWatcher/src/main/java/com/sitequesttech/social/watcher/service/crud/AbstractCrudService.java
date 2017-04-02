package com.sitequesttech.social.watcher.service.crud;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sitequesttech.social.watcher.service.helper.EntityHelper;
import com.sitequesttech.social.watcher.common.support.ReadOperationParams;
import com.sitequesttech.social.watcher.common.support.ReadOperationResults;



/**
 * Abstract Crud service 
 * 
 * @author sivaprasad.vindula@icentris.com
 *
 */
public abstract class AbstractCrudService<T> {

    public AbstractCrudService() {
        super();
    }

    /**
     * Getting the repository
     * @return
     */
    public abstract PagingAndSortingRepository<T, Long> getRepository();

    /**
     * Getting the entity Helper
     * @return
     */
    public abstract EntityHelper<T> getHelper();

    /**
     * Create opeation
     * 
     * @param entity
     * @return created entity
     */
    public T create(T entity) {
        return getRepository().save(entity);
    }
    
    /**
     * Create opeation for list
     * 
     * @param entity
     * @return created entity
     */
    public Iterable<T> create(Iterable<T> entities) {
        return getRepository().save(entities);
    }
    
    /**
     * Update operation
     * 
     * @param entity
     * @return
     */
    public Iterable<T> update(Iterable<T> entity) {
        return getRepository().save(entity);
    }
    
    /**
     * Update operation
     * 
     * @param entity
     * @return
     */
    public T update(T entity) {
        return getRepository().save(entity);
    }

    /**
     * Delete operation
     * 
     * @param entity
     */
    public void delete(Long id) {
        getRepository().delete(id);
    }

    /**
     * Get all entities
     * @return
     */
    public Iterable<T> getById() {
        return getRepository().findAll();
    }
    
    /**
     * Get entity by id
     * @param id
     * @return
     */
    public T getById(final Long id) {
        return getRepository().findOne(id);
    }

    /**
     * Check if an entity with a given id exists
     * 
     * @param id
     * @return
     */
    public boolean exists(final Long id) {
        return getRepository().exists(id);
    }
    
    public Iterable<T> findAll() {
    	return getRepository().findAll();
    }
    
    /**
     * Read Operation 
     * 
     * @param params
     * @return dataTable response pojo
     */
    public ReadOperationResults read(ReadOperationParams params) {
        ReadOperationResults result = new ReadOperationResults();
        Direction sortDir = params.getsSortDir_0().equals("asc") ? Direction.ASC : Direction.DESC;
        String sortColName = params.getsColumns().split(",")[params.getiSortCol_0()];
        Sort sort = new Sort(sortDir, sortColName);
        int pageNumber = (int) Math.ceil(params.getiDisplayStart() / params.getiDisplayLength());
        Page<T> page = getRepository().findAll(new PageRequest(pageNumber, params.getiDisplayLength(), sort));
        List<T> data = page.getContent();
        List<T> uiDate = new ArrayList<T>();
        for (T entity : data) {
            uiDate.add(getHelper().copyFrom(entity));
        }
        result.setsEcho(params.getsEcho());
        result.setiTotalDisplayRecords(page.getTotalElements());
        result.setiTotalRecords(page.getTotalElements());
        result.setAaData(uiDate);
        return result;
    }

}
