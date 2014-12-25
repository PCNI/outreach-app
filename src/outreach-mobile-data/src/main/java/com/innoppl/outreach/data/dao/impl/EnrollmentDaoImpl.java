package com.innoppl.outreach.data.dao.impl;

import com.innoppl.outreach.data.dao.EnrollmentDao;
import com.innoppl.outreach.data.model.Enrollment;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jerald Mejarla
 */
@Repository("EnrollmentDao")
public class EnrollmentDaoImpl extends AbstractJPADao<Enrollment, Integer>
    implements EnrollmentDao {
    
}
