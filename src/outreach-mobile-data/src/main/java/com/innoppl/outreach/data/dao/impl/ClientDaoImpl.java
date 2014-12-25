package com.innoppl.outreach.data.dao.impl;

import com.innoppl.outreach.data.dao.ClientDao;
import com.innoppl.outreach.data.model.Client;
import com.innoppl.outreach.logger.LoggerUtils;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jerald Mejarla
 */
@Repository("ClientDao")
public class ClientDaoImpl extends AbstractJPADao<Client, Integer>
        implements ClientDao {

    private final static Logger LOG
            = LoggerFactory.getLogger(ClientDaoImpl.class);

    @Override
    public List<Client> searchClient(String firstName, String lastName,
            String ssn, Integer startAge, Integer endAge) {

        final Calendar cal1 = new GregorianCalendar();
        cal1.setTime(new Date());
        
        if(startAge != null) {
            cal1.add(Calendar.YEAR, (-1) * startAge);
        }
        
        final Date startDate = cal1.getTime();

        final Calendar cal2 = new GregorianCalendar();
        cal2.setTime(new Date());
        
        if(endAge != null) {
            cal2.add(Calendar.YEAR, (-1) * endAge);
        } else {
            cal2.add(Calendar.YEAR, -200);
        }
        final Date endDate = cal2.getTime();
        LOG.debug("Searching: " + startAge + " , " + endAge + " , " + startDate.toString() + " , " + endDate.toString());
        try {
            return (List<Client>) getEntityManager().createQuery(
                    "select u from Client u where u.firstName like :firstName"
                    + " and u.lastName like :lastName"
                    + " and u.ssn like :ssn"
                    + " and u.dob BETWEEN :endDate AND :startDate"
                    + " and u.isDeleted = 0")
                    .setParameter("lastName", "%" + lastName + "%")
                    .setParameter("firstName", "%" + firstName + "%")
                    .setParameter("ssn", "%" + ssn + "%")
                    .setParameter("ssn", "%" + ssn + "%")
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getResultList();
        } catch (Exception ex) {
            LOG.error(LoggerUtils.getStackTrace(ex));
            return null;
        }
    }
}
