package com.innoppl.outreach.data.dao.impl;

import com.innoppl.outreach.data.dao.VeteranInfoDao;
import com.innoppl.outreach.data.model.VeteranInfo;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jerald Mejarla
 */
@Repository("VeteranInfoDao")
public class VeteranInfoDaoImpl extends AbstractJPADao<VeteranInfo, Integer>
    implements VeteranInfoDao {
    
}
