package com.innoppl.outreach.data.dao.impl;

import com.innoppl.outreach.data.dao.ReferralSourceDao;
import com.innoppl.outreach.data.model.ReferralSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jerald Mejarla
 */
@Repository("ReferralSourceDao")
public class ReferralSourceDaoImpl extends AbstractJPADao<ReferralSource, Integer>
    implements ReferralSourceDao {
    
}
