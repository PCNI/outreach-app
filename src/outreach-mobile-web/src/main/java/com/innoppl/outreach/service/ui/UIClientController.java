package com.innoppl.outreach.service.ui;

import com.innoppl.outreach.data.model.Client;
import com.innoppl.outreach.service.business.ClientService;
import com.innoppl.outreach.service.business.bean.ClientSearchRequest;
import com.innoppl.outreach.service.rest.ExceptionHelper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    
    @RequestMapping(value = "/ui/addClientProfile", method = RequestMethod.POST)
    public ModelAndView addClientProfile(final ModelAndView mav, 
            final Client clientProfile, HttpServletRequest request) {
        
        try {
            LOG.debug(clientProfile.toJson());
        } catch (IOException ex) {
        }
        Cookie[] cookies = request.getCookies();
        String token = null;
        if (cookies != null){
            for (Cookie ck : cookies) {
                if ("access-token".equals(ck.getName())) {
                     token = ck.getValue();
                }
            }
        }
        
        if(token != null){
            byte[] b4EncodedToken = null;
            try {
                b4EncodedToken = Base64.encodeBase64(token.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException ex) {}
            mav.addObject("token",b4EncodedToken);
        }
        mav.addObject("clientProfile", clientProfile);
        mav.setViewName("profile-edit");
        return mav;
    }
    
    @RequestMapping(value = "/ui/profile", method = RequestMethod.GET)
    public ModelAndView addProfile(final ModelAndView mav, 
            final Client clientProfile, HttpServletRequest request) {
        mav.addObject("clientProfile", clientProfile);
        mav.setViewName("profile");
        return mav;
    }    
    
    @RequestMapping(value = "/ui/viewProfile", method = RequestMethod.GET)
    public ModelAndView viewProfile(final ModelAndView mav, 
            final Client clientProfile, HttpServletRequest request) {
        mav.addObject("clientProfile", clientProfile);
        mav.addObject("editable", "true");
        mav.setViewName("profile");
        return mav;
    }    
    
    @RequestMapping(value = "/ui/clientinfo", method = RequestMethod.GET)
    public ModelAndView addClientInfo(final ModelAndView mav, 
            final Client clientProfile, HttpServletRequest request) {
        mav.addObject("clientProfile", clientProfile);
        mav.setViewName("client-information");
        return mav;
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
