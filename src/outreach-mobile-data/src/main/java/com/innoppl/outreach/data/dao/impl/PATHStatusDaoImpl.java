package com.innoppl.outreach.data.dao.impl;

import com.innoppl.outreach.data.dao.PATHStatusDao;
import com.innoppl.outreach.data.model.PATHStatus;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jerald Mejarla
 */
@Repository("PATHStatusDao")
public class PATHStatusDaoImpl extends AbstractJPADao<PATHStatus, Integer>
    implements PATHStatusDao {
    
}
