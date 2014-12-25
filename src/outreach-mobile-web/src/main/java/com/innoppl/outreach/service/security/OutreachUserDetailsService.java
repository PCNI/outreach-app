package com.innoppl.outreach.service.security;

import com.innoppl.outreach.data.model.OUser;
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
            final OUser user
                    = getUser(userName);
            return new User(user.getEmail() + "/" + user.getFirstName() + "/"
                    + (user.getLastName() != null ? user.getLastName() : " "),
                    user.getPassword(),
                    true, true, true, true, getAuthorities(user.getRole().getId()));

        } catch (Exception ex) {
            LOG.error("Error in retrieving user. " + userName + ", "
                    + ExceptionHelper.getStackTrace(ex));
            throw new UsernameNotFoundException("Error in retrieving user", ex);
        }
    }

    /**
     *
     * @param userId
     * @return
     */
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
     * @param emailId
     * @return {@link McfUser}
     */
    private OUser getUser(final String emailId) {
        try {
            return (OUser) this.entityManager.createQuery(
                    "select t from OUser t where t.email = :emailId and t.isDeleted = 0")
                    .setParameter("emailId", emailId).getSingleResult();
        } catch (NoResultException ex) {
            LOG.error("Error in retrieving user. "
                    + ExceptionHelper.getStackTrace(ex));
            return null;
        }
    }

    /**
     *
     * @param access
     * @return {@link GrantedAuthority} list
     */
    public Collection<GrantedAuthority> getAuthorities(
            final Integer access) {
        final List<GrantedAuthority> aList = new ArrayList<>(1);
        if (access != null) {
            final String accessName = getRoles(access.intValue());
            if (accessName != null) {
                LOG.debug("Grant " + accessName + " to this user");
                aList.add(new SimpleGrantedAuthority(accessName));
            }
        }
        return aList;
    }
}
