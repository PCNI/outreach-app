package com.innoppl.outreach.service.business.impl;

import com.innoppl.outreach.data.dao.UserDao;
import com.innoppl.outreach.data.dao.UserInfoDao;
import com.innoppl.outreach.data.model.OUser;
import com.innoppl.outreach.data.model.UserInfo;
import com.innoppl.outreach.service.Errors;
import com.innoppl.outreach.service.ServiceException;
import com.innoppl.outreach.service.business.UserService;
import java.security.Principal;
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
    private UserDao userDao;
    
    @Autowired
    private UserInfoDao userInfoDao;
    

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserInfo authenticate(final String userName, final String password)
            throws ServiceException {
        final UserInfo userInfo = userInfoDao.findByUserName(userName);
        if (userInfo != null) {
            if (userInfo.getPasswordEnc().equals(password)) {
                return userInfo;
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

    /**
     * Reset auth token and token expiry
     *
     * @param oUser
     * @return
     */
    private OUser resetToken(final OUser oUser) {
        if (oUser.getToken() == null) {
            final String token = UUID.randomUUID().toString();
            oUser.setToken(token);
        }
        final Calendar tokenExpiryCal = Calendar.getInstance();
        // Set token expiry to 5 minutes
        tokenExpiryCal.add(Calendar.MINUTE, 30);
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
        tokenExpiryCal.add(Calendar.MINUTE, 30);
        oUser.setTokenExpiry(tokenExpiryCal.getTime());
        return userDao.save(oUser, oUser.getId());
    }

    @Override
    public OUser resetToken(String userName) throws ServiceException {
        return null; //To change body of generated methods, choose Tools | Templates.
    }

    public UserInfo findUserByPrincipal(Principal principal) {
        final String userName = principal.getName().split("/")[0];
        return userInfoDao.findByUserName(userName);
    }

    @Override
    public OUser findUserByEmail(String email) {
        return null;//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String resetPassword(String email) throws ServiceException {
        return null;//To change body of generated methods, choose Tools | Templates.
    }

    
}
