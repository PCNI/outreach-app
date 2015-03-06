/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innoppl.outreach.service.business.impl;

import com.innoppl.outreach.data.dao.EnrollmentDao;
import com.innoppl.outreach.data.dao.ReferralSourceDao;
import com.innoppl.outreach.data.dao.UserDao;
import com.innoppl.outreach.data.model.ReferralSource;
import com.innoppl.outreach.service.Errors;
import com.innoppl.outreach.service.ServiceException;
import com.innoppl.outreach.service.business.ReferralSourceService;
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
@Service("ReferralSourceService")
public class ReferralSourceServiceImpl implements ReferralSourceService{

 @Autowired
    private UserDao userDao;
 
 @Autowired
    private ReferralSourceDao referralSourceDao;
 
  @Autowired
    private EnrollmentDao enrollmentDao;

    @InjectLogging(LogLevel.DEBUG)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ReferralSource addReferralSource(ReferralSource referralSource, Integer uid) throws ServiceException {
    try {
            if (referralSource.getProjectEntryID()!= null
                    && referralSource.getProjectEntryID().getId() != null) {
                   return referralSourceDao.save(referralSource, uid);
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
    public ReferralSource updateReferralSource(ReferralSource referralSource, Integer uid) throws ServiceException {
        return referralSourceDao.save(referralSource, uid);
    }

    @Override
    public List<ReferralSource> findByProjectEntryId(Integer projectEntryId) throws ServiceException {
        return enrollmentDao.findById(projectEntryId).getReferralSourceList();
    }

    @Override
    public ReferralSource findById(Integer referralSourceId) throws ServiceException {
        return referralSourceDao.findById(referralSourceId);
    }
    
}
