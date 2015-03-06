package com.innoppl.outreach.service.rest.controller;

import com.innoppl.outreach.data.model.DateOfEngagement;
import com.innoppl.outreach.service.ServiceException;
import com.innoppl.outreach.service.business.DateOfEngagementService;
import com.innoppl.outreach.service.rest.ResponseWrapper;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Jerald Mejarla
 */
@Controller
@RequestMapping("/service/doe")
public class DateOfEngagementController {

    @Autowired
    private DateOfEngagementService dateOfEngagementService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseWrapper<DateOfEngagement> addNewClient(
            @RequestBody final DateOfEngagement dateOfEngagement,
            final HttpServletRequest request) {
        try {
            final Integer uid = Integer.parseInt(request.getAttribute("uid").toString());
            final DateOfEngagement addDateOfEngagement
                    = dateOfEngagementService.addDateOfEngagement(dateOfEngagement, uid);
            return new ResponseWrapper<>("en", addDateOfEngagement);
        } catch (ServiceException ex) {
            return new ResponseWrapper<>("en", ex);
        }
    }
    
    
}
