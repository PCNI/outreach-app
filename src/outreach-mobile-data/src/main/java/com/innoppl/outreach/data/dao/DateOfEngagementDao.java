package com.innoppl.outreach.data.dao;

import com.innoppl.outreach.data.model.DateOfEngagement;

/**
 *
 * @author Jerald Mejarla
 */
public interface DateOfEngagementDao extends AbstractDao<DateOfEngagement, Integer>{

    /**
     *
     * @param enrollmentId
     * @return
     */
    DateOfEngagement findByEnrollmentID(final Integer enrollmentId);
}
