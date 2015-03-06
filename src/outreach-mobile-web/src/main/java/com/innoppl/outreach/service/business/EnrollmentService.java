package com.innoppl.outreach.service.business;

import com.innoppl.outreach.data.model.Enrollment;
import com.innoppl.outreach.service.ServiceException;
import java.util.List;

/**
 *
 * @author jerald
 */
public interface EnrollmentService {

    /**
     *
     * @param enrollment
     * @param uid
     * @return
     * @throws com.innoppl.outreach.service.ServiceException
     */
    Enrollment addEnrollment(final Enrollment enrollment, final Integer uid) 
            throws ServiceException;

    /**
     *
     * @param enrollment
     * @param uid
     * @return
     * @throws com.innoppl.outreach.service.ServiceException
     */
    Enrollment updateEnrollment(final Enrollment enrollment, final Integer uid) 
            throws ServiceException;
    
    /**
     *
     * @param personalId
     * @return
     * @throws com.innoppl.outreach.service.ServiceException
     */
    List<Enrollment> findByPersonalId(final Integer personalId) throws ServiceException;
    
    
    /**
     *
     * @param enrollmentId
     * @return
     * @throws com.innoppl.outreach.service.ServiceException
     */
    Enrollment findById(final Integer enrollmentId) throws ServiceException;
}
