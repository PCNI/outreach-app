package com.innoppl.outreach.service.business;

import com.innoppl.outreach.service.ServiceException;

/**
 *
 * @author Jerald Mejarla
 */
public interface EmailService {
    
    void sendPasswordResetEmail(final String email, final String password)
            throws ServiceException;
}
