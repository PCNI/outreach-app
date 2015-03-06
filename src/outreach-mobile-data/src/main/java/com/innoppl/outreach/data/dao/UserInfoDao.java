package com.innoppl.outreach.data.dao;

import com.innoppl.outreach.data.model.UserInfo;

/**
 *
 * @author Jerald Mejarla
 */
public interface UserInfoDao {
    
    /**
     *
     * @param userName
     * @return
     */
    UserInfo findByUserName(final String userName);
    
    UserInfo findById(final Integer userKey);
    
}
