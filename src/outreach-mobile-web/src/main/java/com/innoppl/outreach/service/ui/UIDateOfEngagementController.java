package com.innoppl.outreach.service.ui;

import com.innoppl.outreach.data.model.Client;
import com.innoppl.outreach.data.model.DateOfEngagement;
import com.innoppl.outreach.data.model.Enrollment;
import com.innoppl.outreach.data.model.OUser;
import com.innoppl.outreach.data.model.UserInfo;
import com.innoppl.outreach.service.ServiceException;
import com.innoppl.outreach.service.business.ClientService;
import com.innoppl.outreach.service.business.DateOfEngagementService;
import com.innoppl.outreach.service.business.EnrollmentService;
import com.innoppl.outreach.service.business.UserService;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author haravallabhanjn
 */
@Controller
@RequestMapping("/ui/doe")
public class UIDateOfEngagementController {

    private final static Logger LOG = LoggerFactory.getLogger(UIDateOfEngagementController.class);

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserService userService;

    @Autowired
    private DateOfEngagementService doeService;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/{personalID}", method = RequestMethod.GET)
    public ModelAndView doeInfo(final ModelAndView mav,
            HttpServletRequest request,
            final @PathVariable Integer personalID) {
        Client client = clientService.lookupClient(personalID);
        mav.addObject("clientProfile", client);
        try {
            if (client.getEnrollmentList() != null
                    && client.getEnrollmentList().size() > 0) {
                Enrollment enrollment
                        = enrollmentService.findByPersonalId(personalID).get(0);
                mav.addObject("enrollment", enrollment);
                if (enrollment != null && enrollment.getDateOfEngagementList() != null
                        && enrollment.getDateOfEngagementList().size() > 0) {
                    mav.addObject("dateOfEngagement",
                            enrollment.getDateOfEngagementList().get(0));
                    mav.addObject("dateOfEngagementVal",
                            enrollment.getDateOfEngagementList().get(0).getDateOfEngagement());
                } else {
                    mav.addObject("dateOfEngagement", new DateOfEngagement());
                }
            } else {
                mav.addObject("dateOfEngagement", new DateOfEngagement());
            }
        } catch (Exception ex) {
            mav.addObject("dateOfEngagement", new DateOfEngagement());
        }
        mav.setViewName("dateOfEngagement/doeInfo");
        return mav;
    }

    /**
     *
     * @param mav
     * @param dateOfEngagementObj
     * @param errors
     * @param principal
     * @return
     *
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView saveDateOfEngagementInfo(final ModelAndView mav,
            final @ModelAttribute DateOfEngagement dateOfEngagementObj,
            BindingResult errors, Principal principal) {
        String resCode = "200";
        String message;
        String nextPage;
        try {
            if (!errors.hasErrors()) {
                try {
                    LOG.debug("Request: " + dateOfEngagementObj.getDateOfEngagement());
                    LOG.debug("Request: " + dateOfEngagementObj.getProjectEntryID());
                } catch (Exception ex) {
                    //Ignore Exception
                }
                final UserInfo userInfo = userService.findUserByPrincipal(principal);
                if (dateOfEngagementObj.getId() != null) {
                    doeService.updateDateOfEngagement(dateOfEngagementObj, userInfo.getUserkey());
                    message = "Date Of Engagement data has been updated successfully";
                } else {
                    doeService.addDateOfEngagement(dateOfEngagementObj, userInfo.getUserkey());
                    message = "Date Of Engagement data has been created successfully";
                }
                nextPage = "ui/client/" + dateOfEngagementObj.getProjectEntryID().getPersonalID().getId();
            } else {
                resCode = "500";
                LOG.debug("Date of Engagement ERROR: " + errors.toString());
                message = "Bad Request. The request could not be processed.";
                nextPage = "ui";
            }
        } catch (ServiceException ex) {
            resCode = "500";
            message = ex.getMessage();
            nextPage = "ui";
        }
        mav.addObject("responseCode", resCode);
        mav.addObject("message", message);
        mav.addObject("nextPage", nextPage);
        mav.setViewName("common/response");
        return mav;

    }
}
