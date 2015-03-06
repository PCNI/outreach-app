package com.innoppl.outreach.service.rest.controller;

import com.innoppl.outreach.data.model.Enrollment;
import com.innoppl.outreach.service.ServiceException;
import com.innoppl.outreach.service.business.EnrollmentService;
import com.innoppl.outreach.service.business.impl.EnrollmentServiceImpl;
import com.innoppl.outreach.service.logger.InjectLogging;
import com.innoppl.outreach.service.logger.LogLevel;
import com.innoppl.outreach.service.rest.ResponseWrapper;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author jerald
 */
@Controller
@RequestMapping("/service/enrollment")
public class EnrollmentController {

    private final static Logger LOG
            = LoggerFactory.getLogger(EnrollmentServiceImpl.class);
    
    @Autowired
    private EnrollmentService enrollmentService;

    @InjectLogging(LogLevel.DEBUG)
    @RequestMapping(value = "/{personalId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseWrapper<Enrollment> findByPersonalId(
            final @PathVariable Integer personalId) {
        try {
            final List<Enrollment> enrollmentList
                    = enrollmentService.findByPersonalId(personalId);
            return new ResponseWrapper<>("en", enrollmentList);
        } catch (ServiceException ex) {
            return new ResponseWrapper<>("en", ex);
        }
    }

    /**
     *
     * @param enrollment
     * @param request
     * @return
     */
    @InjectLogging(LogLevel.DEBUG)
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseWrapper<Enrollment> addNewEnrollment(
            @RequestBody final Enrollment enrollment, final HttpServletRequest request) {
        try {
            final Integer uid = Integer.parseInt(request.getAttribute("uid").toString());
            try {
                LOG.debug("Request Enrollment" + enrollment.toJson() + ", uid: " + uid);
            } catch (IOException ex) {
                //Ignore Exception;
            }
            final Enrollment savedEnrollment = enrollmentService.addEnrollment(
                    enrollment, uid);
            return new ResponseWrapper<>("en", savedEnrollment);
        } catch (ServiceException ex) {
            return new ResponseWrapper<>("en", ex);
        }
    }

    /**
     *
     * @param enrollment
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseWrapper<Enrollment> updateEnrollment(
            @RequestBody final Enrollment enrollment, final HttpServletRequest request) {
        try {
            final Integer uid = Integer.parseInt(request.getAttribute("uid").toString());
            final Enrollment savedEnrollment
                    = enrollmentService.updateEnrollment(enrollment, uid);
            return new ResponseWrapper<>("en", savedEnrollment);
        } catch (ServiceException ex) {
            return new ResponseWrapper<>("en", ex);
        }
    }
}
