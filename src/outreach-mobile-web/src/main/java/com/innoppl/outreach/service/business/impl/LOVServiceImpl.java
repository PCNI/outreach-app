package com.innoppl.outreach.service.business.impl;

import com.innoppl.outreach.data.dao.LOVDao;
import com.innoppl.outreach.data.model.LOV;
import com.innoppl.outreach.service.Errors;
import com.innoppl.outreach.service.ServiceException;
import com.innoppl.outreach.service.business.LOVService;
import com.innoppl.outreach.service.business.bean.LOVType;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jerald Mejarla
 */
@Service("LOVService")
public class LOVServiceImpl implements LOVService {

    @Autowired
    private LOVDao lovDao;
    
    @Override
    public List<LOV> findAllForType(String lovType) throws ServiceException {
        final int type = LOVType.getType(lovType);
        if(type == -1) {
            throw new ServiceException(Errors.E_LOV_TYPE_NOT_FOUND);
        } else {
            return lovDao.findAllByType(type);
        }
    }
}
