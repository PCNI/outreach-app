package com.innoppl.outreach.data.dao.impl;

import com.innoppl.outreach.data.dao.LOVDao;
import com.innoppl.outreach.data.model.LOV;
import com.innoppl.outreach.logger.LoggerUtils;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jerald Mejarla
 */
@Repository("LOVDao")
public class LOVDaoImpl extends AbstractJPADao<LOV, Integer>
        implements LOVDao {

    private final static Logger LOG
            = LoggerFactory.getLogger(LOVDaoImpl.class);
    
    @Override
    public List<LOV> findAllByType(Integer type) {
        try {
            return (List<LOV>) getEntityManager().createQuery(
                    "select u from LOV u where u.lovType = :type"
                    + " and u.isDeleted = 0")
                    .setParameter("type", type)
                    .getResultList();
        } catch (Exception ex) {
            LOG.error(LoggerUtils.getStackTrace(ex));
            return null;
        }
    }

    @Override
    public LOV findByTypeAndValue(Integer type, String value) {
        try {
            return (LOV) getEntityManager().createQuery(
                    "select u from LOV u where u.lovType = :type"
                    + " and u.valueString = :value and u.isDeleted = 0")
                    .setParameter("type", type)
                    .setParameter("value", value)
                    .getSingleResult();
        } catch (Exception ex) {
            LOG.error(LoggerUtils.getStackTrace(ex));
            return null;
        }
    }
}
