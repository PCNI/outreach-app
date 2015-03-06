package com.innoppl.outreach.service.security;

import com.innoppl.outreach.data.model.OUser;
import com.innoppl.outreach.data.model.UserInfo;
import com.innoppl.outreach.service.logger.InjectLogging;
import com.innoppl.outreach.service.logger.LogLevel;
import com.innoppl.outreach.service.rest.ExceptionHelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jerald Mejarla
 */
@Transactional(readOnly = true)
public class OutreachUserDetailsService implements UserDetailsService {

    private final static Logger LOG = LoggerFactory
            .getLogger(OutreachUserDetailsService.class);
    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager newEm) {
        this.entityManager = newEm;
    }

    @InjectLogging(LogLevel.DEBUG)
    @Override
    public UserDetails loadUserByUsername(final String userName)
            throws UsernameNotFoundException {

        try {
            final UserInfo user
                    = getUser(userName);
            return new User(user.getUserId()+ "/" + user.getNameFirst() + "/"
                    + (user.getNameLast() != null ? user.getNameLast() : " "),
                    user.getPasswordEnc(),
                    true, true, true, true, getAuthorities());

        } catch (Exception ex) {
            LOG.error("Error in retrieving user. " + userName + ", "
                    + ExceptionHelper.getStackTrace(ex));
            throw new UsernameNotFoundException("Error in retrieving user", ex);
        }
    }

    /**
     *
     * @param roleId
     * @return
     */
    @Deprecated
    private String getRoles(final Integer roleId) {
        try {
            final String roleName
                    = (String) this.entityManager.createQuery(
                            "select t.name from ORole t where t.id = :roleId")
                    .setParameter("roleId", roleId)
                    .getSingleResult();
            return roleName;
        } catch (NoResultException ex) {
            LOG.error("Error in retrieving roles. "
                    + ExceptionHelper.getStackTrace(ex));
            return null;
        }
    }

    /**
     *
     * @param userName
     * @return {@link oUser}
     */
    private UserInfo getUser(final String userName) {
        try {
            return (UserInfo) this.entityManager.createQuery(
                    "select t from UserInfo t where t.userId = :userName")
                    .setParameter("userName", userName).getSingleResult();
        } catch (NoResultException ex) {
            LOG.error("Error in retrieving user. "
                    + ExceptionHelper.getStackTrace(ex));
            return null;
        }
    }

    /**
     *
     * @return {@link GrantedAuthority} list
     */
    public Collection<GrantedAuthority> getAuthorities() {
        final List<GrantedAuthority> aList = new ArrayList<>(1);
        aList.add(new SimpleGrantedAuthority("User"));
        return aList;
    }
}
