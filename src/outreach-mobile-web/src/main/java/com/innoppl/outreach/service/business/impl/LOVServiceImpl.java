package com.innoppl.outreach.service.business.impl;

import com.innoppl.outreach.data.dao.LOVDao;
import com.innoppl.outreach.data.model.LOV;
import com.innoppl.outreach.service.Errors;
import com.innoppl.outreach.service.ServiceException;
import com.innoppl.outreach.service.business.LOVService;
import com.innoppl.outreach.service.business.bean.LOVType;
import com.innoppl.outreach.service.logger.InjectLogging;
import com.innoppl.outreach.service.logger.LogLevel;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Jerald Mejarla
 */
@Service("LOVService")
public class LOVServiceImpl implements LOVService {

    @Autowired
    private LOVDao lovDao;

    @InjectLogging(LogLevel.DEBUG)
    @Override
    public List<LOV> findAllForType(String lovType) throws ServiceException {
        final int type = LOVType.getType(lovType);
        if (type == -1) {
            throw new ServiceException(Errors.E_LOV_TYPE_NOT_FOUND);
        } else {
            return lovDao.findAllByType(type);
        }
    }

    @InjectLogging(LogLevel.DEBUG)
    @Override
    public LOV findByTypeAndValue(String lovType, String value) throws ServiceException {
        final int type = LOVType.getType(lovType);
        if (type == -1) {
            throw new ServiceException(Errors.E_LOV_TYPE_NOT_FOUND);
        } else {
            return lovDao.findByTypeAndValue(type, value);
        }
    }

    @Override
    public ModelAndView injectLov(final ModelAndView mav, final String lovType, 
            final String value) {
        try {
            LOV lov = findByTypeAndValue(lovType, value);
            mav.addObject(lovType, lov.getDisplayName());
        } catch (Exception ex) {
            mav.addObject(lovType, "N/A");
        }
        return mav;
    }
}
