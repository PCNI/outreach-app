/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innoppl.outreach.service.business.impl;

import com.innoppl.outreach.data.dao.ProjectDao;
import com.innoppl.outreach.data.model.Project;
import com.innoppl.outreach.service.business.ProjectService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author haravallabhanjn
 */
@Service("ProjectService")
public class ProjectServiceImpl implements ProjectService {

    private final static Logger LOG
            = LoggerFactory.getLogger(ProjectServiceImpl.class);

    @Autowired
    private ProjectDao projectDao;

    @Override
    public List<Project> findAllProjects() {
        return projectDao.findAll("projectName");
    }
    
    @Override
    public Project findById(Integer Id){
        return projectDao.findById(Id);
    }

}
