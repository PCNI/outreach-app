package com.innoppl.outreach.data.dao;

import com.innoppl.outreach.data.model.OUser;

/**
 *
 * @author Jerald Mejarla
 */
public interface UserDao extends AbstractDao<OUser, Integer>{
    
    /**
     *
     * @param email
     * @return
     */
    OUser findByEmail(final String email);
    
    /**
     *
     * @param token
     * @return
     */
    OUser verifyToken(final String token);
}
