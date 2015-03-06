package com.innoppl.outreach.data.dao.impl;

import com.innoppl.outreach.data.dao.DateOfEngagementDao;
import com.innoppl.outreach.data.model.DateOfEngagement;
import com.innoppl.outreach.logger.LoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jerald Mejarla
 */
@Repository("DateOfEngagementDao")
public class DateOfEngagementDaoImpl extends AbstractJPADao<DateOfEngagement, Integer>
        implements DateOfEngagementDao {

    private final static Logger LOG
            = LoggerFactory.getLogger(DateOfEngagementDaoImpl.class);

    @Override
    public DateOfEngagement findByEnrollmentID(Integer enrollmentId) {
        try {
            return (DateOfEngagement) getEntityManager().createQuery(
                    "select u from DateOfEngagement u where u.projectEntryID.id = :enrollmentId"
                    + " and u.isDeleted = 0")
                    .setParameter("enrollmentId", enrollmentId)
                    .getSingleResult();
        } catch (Exception ex) {
            LOG.error(LoggerUtils.getStackTrace(ex));
            return null;
        }
    }

}
