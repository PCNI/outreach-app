package com.innoppl.outreach.data.dao.impl;

import com.innoppl.outreach.data.dao.UserDao;
import com.innoppl.outreach.data.model.OUser;
import com.innoppl.outreach.logger.LoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jerald Mejarla
 */
@Repository("UserDao")
public class UserDaoImpl extends AbstractJPADao<OUser, Integer>
        implements UserDao {

    private final static Logger LOG
            = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public OUser findByEmail(String email) {
        try {
            return (OUser) getEntityManager().createQuery(
                    "select u from OUser u where "
                    + "u.email = :email and u.isDeleted = 0")
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception ex) {
            LOG.error(LoggerUtils.getStackTrace(ex));
            return null;
        }
    }

    @Override
    public OUser verifyToken(String token) {
        try {
            return (OUser) getEntityManager().createQuery(
                    "select u from OUser u where "
                    + "u.token = :token and u.isDeleted = 0")
                    .setParameter("token", token)
                    .getSingleResult();
        } catch (Exception ex) {
            LOG.error(LoggerUtils.getStackTrace(ex));
            return null;
        }
    }
}
