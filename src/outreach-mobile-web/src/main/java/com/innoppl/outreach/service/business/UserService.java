package com.innoppl.outreach.service.business;

import com.innoppl.outreach.data.model.OUser;
import com.innoppl.outreach.service.ServiceException;

/**
 *
 * @author Jerald Mejarla
 */
public interface UserService {

    /**
     * Authenticate user and generate access token
     * @param email
     * @param password
     * @return
     * @throws ServiceException
     */
    OUser authenticate(final String email, final String password)
            throws ServiceException;
    
    /**
     * Verify Auth Token
     * @param token
     * @return
     * @throws ServiceException
     */
    OUser verifyToken(final String token) throws ServiceException;
}
