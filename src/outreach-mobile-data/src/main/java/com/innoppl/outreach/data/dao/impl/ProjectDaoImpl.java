/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innoppl.outreach.data.dao.impl;

import com.innoppl.outreach.data.dao.ProjectDao;
import com.innoppl.outreach.data.model.Project;
import org.springframework.stereotype.Repository;

/**
 *
 * @author haravallabhanjn
 */
@Repository("ProjectDao")
public class ProjectDaoImpl extends AbstractJPADao<Project, Integer> implements
        ProjectDao{
    
}
