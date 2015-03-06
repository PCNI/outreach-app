package com.innoppl.outreach.service.business;

import com.innoppl.outreach.data.model.PATHStatus;
import com.innoppl.outreach.data.model.Services;
import com.innoppl.outreach.service.ServiceException;
import java.util.List;

/**
 *
 * @author Hara JN
 */
public interface ServicesService {

    /**
     *
     * @param services
     * @param uid
     * @return
     * @throws com.innoppl.outreach.service.ServiceException
     */
    Services addServices(final Services services, final Integer uid) 
            throws ServiceException;

    /**
     *
     * @param services
     * @param uid
     * @return
     * @throws com.innoppl.outreach.service.ServiceException
     */
    Services updateServices(final Services services, final Integer uid) 
            throws ServiceException;
    
    /**
     *
     * @param projectEntryId
     * @return
     * @throws com.innoppl.outreach.service.ServiceException
     */
    List<Services> findByProjectEntryId(final Integer projectEntryId) throws ServiceException;
    
    
    /**
     *
     * @param servicesId
     * @return
     * @throws com.innoppl.outreach.service.ServiceException
     */
    Services findById(final Integer servicesId) throws ServiceException;
}
