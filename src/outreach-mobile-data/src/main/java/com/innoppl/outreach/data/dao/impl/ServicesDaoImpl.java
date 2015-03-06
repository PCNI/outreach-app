package com.innoppl.outreach.data.dao.impl;

import com.innoppl.outreach.data.dao.ServicesDao;
import com.innoppl.outreach.data.model.Services;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jerald Mejarla
 */
@Repository("ServicesDao")
public class ServicesDaoImpl extends AbstractJPADao<Services, Integer>
    implements ServicesDao {
    
}
