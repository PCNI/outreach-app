package com.innoppl.outreach.service.business.impl;

import com.innoppl.outreach.data.dao.EnrollmentDao;
import com.innoppl.outreach.data.dao.ServicesDao;
import com.innoppl.outreach.data.model.Services;
import com.innoppl.outreach.service.Errors;
import com.innoppl.outreach.service.ServiceException;
import com.innoppl.outreach.service.business.ServicesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author haravallabhanjn
 */

@Service("ServicesService")
public class ServicesServiceImpl implements ServicesService{

    @Autowired
    private ServicesDao servicesDao;
    
    @Autowired
    private EnrollmentDao enrollmentDao;
    
    @Override
    public Services addServices(Services services, Integer uid) throws ServiceException {
    try {
            if (services.getProjectEntryID()!= null
                    && services.getProjectEntryID().getId() != null) {
                    return servicesDao.save(services, uid);
            } else {
                throw new ServiceException(Errors.E_PROJ_ENTRY_ID_NULL);
            }
        } catch (ServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ServiceException(ex, Errors.E_SAVE_CLIENT_FAILED);
        }        
       
    }

    @Override
    public Services updateServices(Services services, Integer uid) throws ServiceException {
        return servicesDao.save(services, uid);
    }

    @Override
    public List<Services> findByProjectEntryId(Integer projectEntryId) throws ServiceException {
        return enrollmentDao.findById(projectEntryId).getServicesList();
    }

    @Override
    public Services findById(Integer servicesId) throws ServiceException {
        return servicesDao.findById(servicesId);
    }
    
}
