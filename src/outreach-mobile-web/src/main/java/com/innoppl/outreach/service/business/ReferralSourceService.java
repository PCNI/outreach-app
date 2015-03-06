package com.innoppl.outreach.service.business;

import com.innoppl.outreach.data.model.ReferralSource;
import com.innoppl.outreach.service.ServiceException;
import java.util.List;

/**
 *
 * @author Hara JN
 */
public interface ReferralSourceService {

    /**
     *
     * @param referralSource
     * @param uid
     * @return
     * @throws com.innoppl.outreach.service.ServiceException
     */
    ReferralSource addReferralSource(final ReferralSource referralSource, final Integer uid) 
            throws ServiceException;

    /**
     *
     * @param referralSource
     * @param uid
     * @return
     * @throws com.innoppl.outreach.service.ServiceException
     */
    ReferralSource updateReferralSource(final ReferralSource referralSource, final Integer uid) 
            throws ServiceException;
    
    /**
     *
     * @param projectEntryId
     * @return
     * @throws com.innoppl.outreach.service.ServiceException
     */
    List<ReferralSource> findByProjectEntryId(final Integer projectEntryId) throws ServiceException;
    
    
    /**
     *
     * @param referralSourceId
     * @return
     * @throws com.innoppl.outreach.service.ServiceException
     */
    ReferralSource findById(final Integer referralSourceId) throws ServiceException;
}
