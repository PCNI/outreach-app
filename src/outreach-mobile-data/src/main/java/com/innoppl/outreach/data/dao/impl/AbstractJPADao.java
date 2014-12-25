package com.innoppl.outreach.data.dao.impl;

import com.innoppl.outreach.data.beans.ValueAndCount;
import com.innoppl.outreach.data.dao.AbstractDao;
import com.innoppl.outreach.data.model.AbstractEntity;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jerald Mejarla
 *
 * @param <T>
 * @param <ID>
 */
@Repository
public abstract class AbstractJPADao<T extends AbstractEntity, ID extends Serializable>
        implements AbstractDao<T, ID> {

    private final transient Class<T> persistentClass;
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public AbstractJPADao() {
        this.persistentClass
                = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    /**
     * @return persistent class
     */
    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    /**
     * Set {@link EntityManager}
     *
     * @param entityManager
     * @see EntityManager
     */
    public void setEntityManager(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Returns {@link EntityManager}
     *
     * @return {@link EntityManager}
     * @see EntityManager
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public T findById(final ID id, final boolean lock) {
        if (lock) {
            return (T) entityManager.find(getPersistentClass(), id,
                    LockModeType.WRITE);
        } else {
            return (T) entityManager.find(getPersistentClass(), id);
        }
    }

    @Override
    public T findById(final ID id) {
        final T entity = (T) entityManager.find(getPersistentClass(), id);
        if (entity != null && entity.getIsDeleted() == 0) {
            return entity;
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll() {
        return entityManager.createQuery(
                new StringBuilder("select entity from ")
                .append(getPersistentClass().getSimpleName())
                .append(" as entity  where entity.isDeleted = 0").toString()).getResultList();
    }

    /**
     * Return all entities T.
     *
     * @param sortBy
     * @return List of T
     */
    @Override
    public List<T> findAll(final String sortBy) {
        return entityManager.createQuery(
                new StringBuilder("select entity from ")
                .append(getPersistentClass().getSimpleName())
                .append(" as entity where entity.isDeleted = 0 order by ")
                .append(sortBy).toString()).getResultList();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public T save(final T entity, final Integer uid) {
        if (entity.getId() == null) {
            entity.setUserID(uid);
            entity.setDateCreated(new Date());
        } else {
            entity.setUserID(uid);
            entity.setDateUpdated(new Date());
        }
        final T entityPersist = entityManager.merge(entity);
        flush();
        return entityPersist;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(final T entity, final Integer uid) {
        entity.setUserID(uid);
        entity.setDateDeleted(new Date());
        entity.setIsDeleted(1);
        entityManager.merge(entity);
        flush();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteById(final ID id, final Integer uid) {
        T entity = findById(id);
        delete(entity, uid);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void deletePermanently(final T entity) {
        entityManager.remove(entity);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void deletePermanentlyById(final ID id) {
        entityManager.createQuery(
                new StringBuilder("delete ").append(getPersistentClass().getName())
                .append(" where id = ").append(id).toString()).executeUpdate();
    }

    @Override
    public long count() {
        return (Long) entityManager.createQuery(
                new StringBuilder("select count(*) from ").append(
                        getPersistentClass().getName())
                .append(" where isDeleted = 0")
                .toString()).getSingleResult();
    }

    /**
     * Flush {@link EntityManager}
     *
     * @see EntityManager
     */
    public void flush() {
        entityManager.flush();
    }

    /**
     * Clear {@link EntityManager}
     *
     * @see EntityManager
     */
    public void clear() {
        entityManager.clear();
    }

    @SuppressWarnings("unchecked")
    @Override
    public ValueAndCount<T> findEntries(final int firstResult, final int maxResults) {
        final List<T> data = entityManager.createQuery(
                new StringBuilder("select entity from ")
                .append(getPersistentClass().getSimpleName())
                .append(" as entity  where entity.isDeleted = 0").toString())
                .setFirstResult(firstResult)
                .setMaxResults(maxResults).getResultList();
        final long count = count();
        return new ValueAndCount<T>(data, count);
    }

    @SuppressWarnings("unchecked")
    @Override
    public ValueAndCount<T> findEntries(final int firstResult, final int maxResults,
            final String sortBy) {
        final List<T> data = entityManager.createQuery(
                new StringBuilder("select entity from ")
                .append(getPersistentClass().getSimpleName())
                .append(" as entity  where entity.isDeleted = 0 order by ")
                .append(sortBy).toString())
                .setFirstResult(firstResult)
                .setMaxResults(maxResults).getResultList();
        final long count = count();
        return new ValueAndCount<T>(data, count);
    }
}
