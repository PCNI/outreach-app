package com.innoppl.outreach.data.dao.impl;

import com.innoppl.outreach.data.dao.ORoleDao;
import com.innoppl.outreach.data.model.ORole;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jerald Mejarla
 */
@Repository("ORoleDao")
public class ORoleDaoImpl extends AbstractJPADao<ORole, Integer>
    implements ORoleDao {
    
}
