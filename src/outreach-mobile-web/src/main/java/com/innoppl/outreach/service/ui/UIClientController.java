package com.innoppl.outreach.service.ui;

import com.innoppl.outreach.data.model.Client;
import com.innoppl.outreach.service.ServiceException;
import com.innoppl.outreach.service.business.ClientService;
import com.innoppl.outreach.service.business.EnrollmentService;
import com.innoppl.outreach.service.business.bean.ClientSearchRequest;
import com.innoppl.outreach.service.rest.ExceptionHelper;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Jerald Mejarla
 */
@Controller
public class UIClientController {

    private final static Logger LOG = LoggerFactory.getLogger(UIClientController.class);
    
    @Autowired
    private ClientService clientService;

    @Autowired
    private EnrollmentService enrollmentService;
    
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex, HttpServletRequest request) {
        final ModelAndView mav = new ModelAndView("error");
        mav.addObject("message", ExceptionHelper.getStackTrace(ex));
        return mav;
    }
    
    @RequestMapping(value = "/ui/results", method = RequestMethod.GET)
    public ModelAndView displayListPage(final ModelAndView mav, 
            final List<Client> clientList, final String message) {
        mav.addObject("clientList", clientList);
        mav.addObject("message", message);        
        mav.setViewName("result");
        return mav;
    }
    
    @RequestMapping(value = "/ui/search", method = RequestMethod.POST)
    public ModelAndView searchClient(
            final @ModelAttribute ClientSearchRequest clientSearchRequest) {
        
        LOG.debug("Request: " + clientSearchRequest);
        final List<Client> searchClient = 
                clientService.searchClient(clientSearchRequest);
        return displayListPage(new ModelAndView(), searchClient, null);
    }
    
    @RequestMapping(value = "/ui/addClientConsent", method = RequestMethod.GET)
    public String addClientConsent(final Model model) {
        return "consent";
    }
    
    @RequestMapping(value = "/ui/profileForm", method = RequestMethod.POST)
    public ModelAndView profileForm(final @ModelAttribute Client clientProfile, 
            HttpServletRequest request) {
        return addClientProfile(new ModelAndView(),clientProfile, request);
    }
    
    @RequestMapping(value = "/ui/addClientProfile", method = RequestMethod.GET)
    public ModelAndView addClientProfile(final ModelAndView mav, 
            final Client clientProfile, HttpServletRequest request) {
        mav.addObject("clientProfile", clientProfile);
        mav.setViewName("clientProfile");
        return mav;
    }
    
    @RequestMapping(value = "/ui/editClientProfile", method = RequestMethod.GET)
    public ModelAndView editClientProfile(final ModelAndView mav, 
            final Client clientProfile, HttpServletRequest request,
            @RequestParam Integer id) {
        
        mav.addObject("clientProfile", clientService.lookupClient(id));
        mav.setViewName("editClientProfile");
        return mav;
    }    
    
    @RequestMapping(value = "/ui/profile", method = RequestMethod.GET)
    public ModelAndView addProfile(final ModelAndView mav, 
            final Client clientProfile, HttpServletRequest request,
            @RequestParam Integer id) {
        mav.addObject("clientProfile", clientService.lookupClient(id));
        mav.setViewName("profile");
        return mav;
    }    
    
    @RequestMapping(value = "/ui/viewProfile", method = RequestMethod.GET)
    public ModelAndView viewProfile(final ModelAndView mav, 
            final Client clientProfile, HttpServletRequest request,  
            @RequestParam Integer id) {
        try {
            mav.addObject("clientProfile", clientService.lookupClient(id));
            //TODO for multiple enrollments
            mav.addObject("enrollment", enrollmentService.findByPersonalId(id).get(0));
            mav.setViewName("profile");
            return mav;
        } catch (ServiceException ex) {

        }
        return null;
    }    
    
    
    @RequestMapping(value = "/ui/healthconditions", method = RequestMethod.GET)
    public ModelAndView addHealthConditions(final ModelAndView mav, 
            final Client clientProfile, HttpServletRequest request) {
        mav.addObject("clientProfile", clientProfile);
        mav.setViewName("health-conditions");
        return mav;
    } 
    
    @RequestMapping(value = "/ui/household", method = RequestMethod.GET)
    public ModelAndView addHouseHold(final ModelAndView mav, 
            final Client clientProfile, HttpServletRequest request) {
        mav.addObject("clientProfile", clientProfile);
        mav.setViewName("house-hold");
        return mav;
    } 
    
    @RequestMapping(value = "/ui/contact", method = RequestMethod.GET)
    public ModelAndView addContact(final ModelAndView mav, 
            final Client clientProfile, HttpServletRequest request) {
        mav.addObject("clientProfile", clientProfile);
        mav.setViewName("contacts");
        return mav;
    } 
    
    @RequestMapping(value = "/ui/pathreferral", method = RequestMethod.GET)
    public ModelAndView addPathReferral(final ModelAndView mav, 
            final Client clientProfile, HttpServletRequest request) {
        mav.addObject("clientProfile", clientProfile);
        mav.setViewName("path-referral");
        return mav;
    } 
}
