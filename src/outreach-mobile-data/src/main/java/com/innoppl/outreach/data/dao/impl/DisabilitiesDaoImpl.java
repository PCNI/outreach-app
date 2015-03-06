package com.innoppl.outreach.data.dao.impl;

import com.innoppl.outreach.data.dao.DisabilitiesDao;
import com.innoppl.outreach.data.model.Disabilities;
import com.innoppl.outreach.logger.LoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jerald
 */
@Repository("DisabilitiesDao")
public class DisabilitiesDaoImpl extends AbstractJPADao<Disabilities, Integer>
        implements DisabilitiesDao {

    private final static Logger LOG
            = LoggerFactory.getLogger(DisabilitiesDaoImpl.class);

    @Override
    public Disabilities findByDisabilityType(Integer projectEntryID,
            Integer disabilityType) {
        try {
            return (Disabilities) getEntityManager().createQuery(
                    "select u from Disabilities u where u.disabilityType = :disabilityType"
                    + " and u.projectEntryID.id = :projectEntryID and u.isDeleted = 0")
                    .setParameter("disabilityType", disabilityType)
                    .setParameter("projectEntryID", projectEntryID)
                    .getSingleResult();
        } catch (Exception ex) {
            LOG.error(LoggerUtils.getStackTrace(ex));
            return null;
        }
    }

}
