package com.innoppl.outreach.service.business.impl;

import com.innoppl.outreach.data.dao.UserDao;
import com.innoppl.outreach.data.model.OUser;
import com.innoppl.outreach.service.Errors;
import com.innoppl.outreach.service.ServiceException;
import com.innoppl.outreach.service.business.UserService;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jerald Mejarla
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

    private final static Logger LOG
            = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    private UserDao userDao;

    /**
     * Reset auth token and token expiry
     *
     * @param oUser
     * @return
     */
    private OUser resetToken(final OUser oUser) {
        final String token = UUID.randomUUID().toString();
        oUser.setToken(token);
        final Calendar tokenExpiryCal = Calendar.getInstance();
        // Set token expiry to 5 minutes
        tokenExpiryCal.add(Calendar.MINUTE, 5);
        oUser.setTokenExpiry(tokenExpiryCal.getTime());
        return userDao.save(oUser, oUser.getId());
    }

    /**
     *
     * @param oUser
     * @return
     */
    private OUser resetTokenExpiry(final OUser oUser) {
        final Calendar tokenExpiryCal = Calendar.getInstance();
        // Set token expiry to 1 hour
        tokenExpiryCal.add(Calendar.HOUR, 1);
        oUser.setTokenExpiry(tokenExpiryCal.getTime());
        return userDao.save(oUser, oUser.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OUser authenticate(final String email, final String password)
            throws ServiceException {
        LOG.debug("Encoded Password: " + bcryptEncoder.encode(password));
        final OUser oUser = userDao.findByEmail(email);
        if (oUser != null && oUser.getIsDeleted() == 0) {
            if (bcryptEncoder.matches(password, oUser.getPassword())) {
                return resetToken(oUser);
            } else {
                throw new ServiceException(Errors.E_INVALID_PASSWORD);
            }
        } else {
            throw new ServiceException(Errors.E_USER_NOT_FOUND);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OUser verifyToken(String token) throws ServiceException {
        final OUser oUser = userDao.verifyToken(token);
        if (oUser != null && oUser.getTokenExpiry().after(new Date())) {
            return resetTokenExpiry(oUser);
        } else {
            return null;
        }
    }
}
