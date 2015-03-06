package com.innoppl.outreach.service.business.impl;

import com.innoppl.outreach.data.dao.DateOfEngagementDao;
import com.innoppl.outreach.data.model.DateOfEngagement;
import com.innoppl.outreach.service.ServiceException;
import com.innoppl.outreach.service.business.DateOfEngagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jerald Mejarla
 */
@Service("DateOfEngagementService")
public class DateOfEngagementServiceImpl implements DateOfEngagementService {

    @Autowired
    private DateOfEngagementDao dateOfEngagementDao;

    @Override
    public DateOfEngagement addDateOfEngagement(
            final DateOfEngagement dateOfEngagement, final Integer uid)
            throws ServiceException {
        return dateOfEngagementDao.save(dateOfEngagement, uid);
    }

    @Override
    public DateOfEngagement updateDateOfEngagement(
            final DateOfEngagement dateOfEngagement, final Integer uid)
            throws ServiceException {
        return dateOfEngagementDao.save(dateOfEngagement, uid);
    }

    @Override
    public DateOfEngagement lookupDOEByEnrollmentID(Integer EnrollmentID) {
        return dateOfEngagementDao.findByEnrollmentID(EnrollmentID);
    }

}
