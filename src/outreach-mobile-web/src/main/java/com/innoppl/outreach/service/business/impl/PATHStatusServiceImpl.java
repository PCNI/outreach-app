/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innoppl.outreach.service.business.impl;

import com.innoppl.outreach.data.dao.EnrollmentDao;
import com.innoppl.outreach.data.dao.PATHStatusDao;
import com.innoppl.outreach.data.dao.UserDao;
import com.innoppl.outreach.data.model.PATHStatus;
import com.innoppl.outreach.service.Errors;
import com.innoppl.outreach.service.ServiceException;
import com.innoppl.outreach.service.business.PATHStatusService;
import com.innoppl.outreach.service.logger.InjectLogging;
import com.innoppl.outreach.service.logger.LogLevel;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author haravallabhanjn
 */
@Service("PATHStatusService")
public class PATHStatusServiceImpl implements PATHStatusService{

 @Autowired
    private UserDao userDao;
 
 @Autowired
    private PATHStatusDao pathStatusDao;
 
  @Autowired
    private EnrollmentDao enrollmentDao;

    @InjectLogging(LogLevel.DEBUG)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PATHStatus addPATHStatus(PATHStatus pathStatus, Integer uid) throws ServiceException {
    try {
            if (pathStatus.getProjectEntryID()!= null
                    && pathStatus.getProjectEntryID().getId() != null) {
                   return pathStatusDao.save(pathStatus, uid);
            } else {
                throw new ServiceException(Errors.E_PROJ_ENTRY_ID_NULL);
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
    public PATHStatus updatePATHStatus(PATHStatus pathStatus, Integer uid) throws ServiceException {
        return pathStatusDao.save(pathStatus, uid);
    }

    @Override
    public List<PATHStatus> findByProjectEntryId(Integer projectEntryId) throws ServiceException {
        return enrollmentDao.findById(projectEntryId).getPATHStatusList();
    }

    @Override
    public PATHStatus findById(Integer pathStatusId) throws ServiceException {
        return pathStatusDao.findById(pathStatusId);
    }
    
}
