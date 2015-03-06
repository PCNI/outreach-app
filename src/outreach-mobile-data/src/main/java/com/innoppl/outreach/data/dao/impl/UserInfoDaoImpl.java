package com.innoppl.outreach.data.dao.impl;

import com.innoppl.outreach.data.dao.UserInfoDao;
import com.innoppl.outreach.data.model.UserInfo;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jerald Mejarla
 */
@Repository("UserInfoDao")
public class UserInfoDaoImpl implements UserInfoDao {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public UserInfo findByUserName(String userName) {
        try {
            return (UserInfo) entityManager.createQuery(
                    "select u from UserInfo u where "
                    + "u.userId = :userName")
                    .setParameter("userName", userName)
                    .getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }
    
    @Override
    public UserInfo findById(Integer userkey) {
        try {
            return (UserInfo) entityManager.createQuery(
                    "select u from UserInfo u where "
                    + "u.userkey = :userkey")
                    .setParameter("userkey", userkey)
                    .getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }
    
    
    
}
