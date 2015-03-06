package com.innoppl.outreach.service.business;

import com.innoppl.outreach.data.model.PATHStatus;
import com.innoppl.outreach.service.ServiceException;
import java.util.List;

/**
 *
 * @author Hara JN
 */
public interface PATHStatusService {

    /**
     *
     * @param pathStatus
     * @param uid
     * @return
     * @throws com.innoppl.outreach.service.ServiceException
     */
    PATHStatus addPATHStatus(final PATHStatus pathStatus, final Integer uid) 
            throws ServiceException;

    /**
     *
     * @param pathStatus
     * @param uid
     * @return
     * @throws com.innoppl.outreach.service.ServiceException
     */
    PATHStatus updatePATHStatus(final PATHStatus pathStatus, final Integer uid) 
            throws ServiceException;
    
    /**
     *
     * @param projectEntryId
     * @return
     * @throws com.innoppl.outreach.service.ServiceException
     */
    List<PATHStatus> findByProjectEntryId(final Integer projectEntryId) throws ServiceException;
    
    
    /**
     *
     * @param pathStatusId
     * @return
     * @throws com.innoppl.outreach.service.ServiceException
     */
    PATHStatus findById(final Integer pathStatusId) throws ServiceException;
}
