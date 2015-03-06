package com.innoppl.outreach.service.business.impl;

import com.innoppl.outreach.data.dao.ClientDao;
import com.innoppl.outreach.data.dao.EnrollmentDao;
import com.innoppl.outreach.data.model.Client;
import com.innoppl.outreach.data.model.Enrollment;
import com.innoppl.outreach.service.Errors;
import com.innoppl.outreach.service.ServiceException;
import com.innoppl.outreach.service.business.EnrollmentService;
import com.innoppl.outreach.service.logger.InjectLogging;
import com.innoppl.outreach.service.logger.LogLevel;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jerald
 */
@Service("EnrollmentService")
public class EnrollmentServiceImpl implements EnrollmentService {

    private final static Logger LOG
            = LoggerFactory.getLogger(EnrollmentServiceImpl.class);
    
    @Autowired
    private EnrollmentDao enrollmentDao;

    @Autowired
    private ClientDao clientDao;

    @InjectLogging(LogLevel.DEBUG)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Enrollment addEnrollment(Enrollment enrollment, Integer uid)
            throws ServiceException {
        try {
            LOG.debug("Request Enrollment" + enrollment.toJson());
            if (enrollment.getPersonalID() != null
                    && enrollment.getPersonalID().getId() != null) {
                final Client client = clientDao.findById(
                        enrollment.getPersonalID().getId());
                if (client != null) {
                    enrollment.setPersonalID(client);
                    return enrollmentDao.save(enrollment, uid);
                } else {
                    throw new ServiceException(Errors.E_CLIENT_NOT_FOUND);
                }
            } else {
                throw new ServiceException(Errors.E_PERSONAL_ID_NULL);
            }
        } catch (ServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ServiceException(ex, Errors.E_SAVE_CLIENT_FAILED);
        }
    }

    @InjectLogging(LogLevel.DEBUG)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Enrollment updateEnrollment(Enrollment enrollment, Integer uid)
            throws ServiceException {
        return enrollmentDao.save(enrollment, uid);
    }

    @Override
    public List<Enrollment> findByPersonalId(Integer personalId)
            throws ServiceException {
        final Client client = clientDao.findById(personalId);
        if (client != null) {
            return client.getEnrollmentList();
        } else {
            throw new ServiceException(Errors.E_CLIENT_NOT_FOUND);
        }
    }

    @Override
    public Enrollment findById(Integer enrollmentId) throws ServiceException {
        return enrollmentDao.findById(enrollmentId);
    }
}
