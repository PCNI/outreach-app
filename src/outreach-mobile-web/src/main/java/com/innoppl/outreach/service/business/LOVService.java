package com.innoppl.outreach.service.business;

import com.innoppl.outreach.data.model.LOV;
import com.innoppl.outreach.service.ServiceException;
import java.util.List;

/**
 *
 * @author Jerald Mejarla
 */
public interface LOVService {
    
    List<LOV> findAllForType(final String lovType) throws ServiceException;
}
