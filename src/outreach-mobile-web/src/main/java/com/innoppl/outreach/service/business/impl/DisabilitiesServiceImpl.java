package com.innoppl.outreach.service.business.impl;

import com.innoppl.outreach.data.dao.DisabilitiesDao;
import com.innoppl.outreach.data.dao.EnrollmentDao;
import com.innoppl.outreach.data.model.Disabilities;
import com.innoppl.outreach.service.Errors;
import com.innoppl.outreach.service.ServiceException;
import com.innoppl.outreach.service.business.DisabilitiesService;
import com.innoppl.outreach.service.logger.InjectLogging;
import com.innoppl.outreach.service.logger.LogLevel;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jerald Mejarla
 */
@Service("DisabilitiesService")
public class DisabilitiesServiceImpl implements DisabilitiesService {

    @Autowired
    private DisabilitiesDao disabilitiesDao;

    @Autowired
    private EnrollmentDao enrollmentDao;

    @Override
    public Disabilities addDisability(Disabilities disabilities, Integer uid)
            throws ServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @InjectLogging(LogLevel.DEBUG)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<Disabilities> addDisabilites(List<Disabilities> disabilitesList,
            Integer uid) throws ServiceException {
        final List<Disabilities> savedDisabilitesList = new ArrayList<>(
                disabilitesList.size());
        for (Disabilities x : disabilitesList) {
            savedDisabilitesList.add(disabilitiesDao.save(x, uid));
        }
        return savedDisabilitesList;
    }

    @InjectLogging(LogLevel.DEBUG)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<Disabilities> findByProjectEntry(Integer projectEntryId)
            throws ServiceException {
        if (projectEntryId != null) {
            return enrollmentDao.findById(projectEntryId).getDisabilitiesList();
        } else {
            throw new ServiceException(Errors.E_PROJ_ENTRY_ID_NULL);
        }
    }
}
