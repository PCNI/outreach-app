package com.innoppl.outreach.service.business;

import com.innoppl.outreach.data.model.OUser;
import com.innoppl.outreach.data.model.UserInfo;
import com.innoppl.outreach.service.ServiceException;
import java.security.Principal;

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
    UserInfo authenticate(final String email, final String password)
            throws ServiceException;
    
    /**
     * Verify Auth Token
     * @param token
     * @return
     * @throws ServiceException
     */
    OUser verifyToken(final String token) throws ServiceException;
    
    /**
     *
     * @param userName
     * @return
     * @throws ServiceException
     */
    OUser resetToken(final String userName) throws ServiceException;
    
    /**
     *
     * @param principal
     * @return
     */
    UserInfo findUserByPrincipal(final Principal principal);
    
    /**
     *
     * @param email
     * @return
     */
    OUser findUserByEmail(final String email);
    
    /**
     *
     * @param email
     * @return
     */
    String resetPassword(final String email) throws ServiceException;
    
    
    
}
