package com.innoppl.outreach.service.business;

import com.innoppl.outreach.data.model.DateOfEngagement;
import com.innoppl.outreach.service.ServiceException;

/**
 *
 * @author Jerald Mejarla
 */
public interface DateOfEngagementService {
    
    DateOfEngagement lookupDOEByEnrollmentID(final Integer EnrollmentID);
    
    /**
     *
     * @param dateOfEngagement
     * @param uid
     * @return
     * @throws ServiceException
     */
    DateOfEngagement addDateOfEngagement(final DateOfEngagement dateOfEngagement,
            final Integer uid)
            throws ServiceException;
    
    /**
     *
     * @param dateOfEngagement
     * @param uid
     * @return
     * @throws ServiceException
     */
    DateOfEngagement updateDateOfEngagement(final DateOfEngagement dateOfEngagement,
            final Integer uid)
            throws ServiceException;
}
