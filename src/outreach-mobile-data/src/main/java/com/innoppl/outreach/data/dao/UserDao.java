package com.innoppl.outreach.data.dao;

import com.innoppl.outreach.data.model.OUser;

/**
 *
 * @author Jerald Mejarla
 */
public interface UserDao extends AbstractDao<OUser, Integer>{
    
    /**
     *
     * @param token
     * @return
     */
    OUser verifyToken(final String token);
}
