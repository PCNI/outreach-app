/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innoppl.outreach.service.business;

import com.innoppl.outreach.data.model.Project;
import java.util.List;

/**
 *
 * @author haravallabhanjn
 */
public interface ProjectService {
    
    List<Project> findAllProjects();
    
    /**
     *
     * @param Id
     * @return
     */
    Project findById(Integer Id);
}


