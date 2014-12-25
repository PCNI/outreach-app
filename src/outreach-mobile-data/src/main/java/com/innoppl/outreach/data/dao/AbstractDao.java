package com.innoppl.outreach.data.dao;

import com.innoppl.outreach.data.beans.ValueAndCount;
import com.innoppl.outreach.data.model.AbstractEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.LockModeType;

/**
 * @author Jerald Mejarla
 *
 * @param <T>
 * @param <ID>
 */
public interface AbstractDao<T extends AbstractEntity, ID extends Serializable> {

    /**
     * Save entity T.
     *
     * @param entity T
     * @param uid User Id
     * @return Persistent entity
     */
    T save(final T entity, final Integer uid);

    /**
     * Remove entity T.
     *
     * @param entity T
     * @param uid User ID
     */
    void delete(final T entity, final Integer uid);

    /**
     * Remove entity T by id.
     *
     * @param id ID of the entity
     * @param uid User ID
     */
    void deleteById(final ID id, final Integer uid);

    /**
     * Remove entity T.
     *
     * @param entity T
     */
    void deletePermanently(final T entity);

    /**
     * Remove entity T by id.
     *
     * @param id ID of the entity
     */
    void deletePermanentlyById(final ID id);

    /**
     * Count all entities.
     *
     * @return count of entities
     */
    long count();

    /**
     * Find an entity T by its id.
     *
     * @param id ID of the entity
     * @param lock {@link LockModeType}
     * @return T
     */
    T findById(final ID id, final boolean lock);

    /**
     * Find an entity T by it id.
     *
     * @param id ID of the entity
     * @return T
     */
    T findById(final ID id);

    /**
     * Return all entities T.
     *
     * @return List of T
     */
    List<T> findAll();

    /**
     * Return all entities T.
     *
     * @param sortBy
     * @return List of T
     */
    List<T> findAll(final String sortBy);

    /**
     * Find a range of entities T.
     *
     * @param firstResult
     * @param maxResults
     * @return List of T
     */
    ValueAndCount<T> findEntries(final int firstResult, final int maxResults);

    /**
     * Find a range of entities T.
     *
     * @param firstResult
     * @param maxResults
     * @param sortBy
     * @return List of T
     */
    ValueAndCount<T> findEntries(final int firstResult, final int maxResults,
            final String sortBy);

}
