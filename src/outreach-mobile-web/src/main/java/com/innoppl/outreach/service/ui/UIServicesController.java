/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innoppl.outreach.service.ui;


import com.innoppl.outreach.data.model.Client;
import com.innoppl.outreach.data.model.Enrollment;
import com.innoppl.outreach.data.model.OUser;
import com.innoppl.outreach.data.model.Services;
import com.innoppl.outreach.data.model.UserInfo;
import com.innoppl.outreach.service.ServiceException;
import com.innoppl.outreach.service.business.ClientService;
import com.innoppl.outreach.service.business.EnrollmentService;
import com.innoppl.outreach.service.business.LOVService;
import com.innoppl.outreach.service.business.ServicesService;
import com.innoppl.outreach.service.business.UserService;
import com.innoppl.outreach.service.business.bean.LOVType;
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
@RequestMapping("ui/services")
public class UIServicesController {
        private final static Logger LOG = LoggerFactory.getLogger(UIServicesController.class);
        
    @Autowired
    private EnrollmentService enrollmentService;
    
    @Autowired
    private ClientService clientService;
    
    @Autowired
    private LOVService lovService;
    
    @Autowired
    private UserService userService;
    
    
    @Autowired
    private ServicesService  servicesService;

    
    @RequestMapping(value = "/{personalID}", method = RequestMethod.GET)
    public ModelAndView servicesInfo(final ModelAndView mav, 
            HttpServletRequest request, 
            final @PathVariable Integer personalID) {
            Client client = clientService.lookupClient(personalID); 
            mav.addObject("clientProfile", client);

            try {
                mav.addObject("recordTypeList", lovService.findAllForType(LOVType.recordType.name()));
                mav.addObject("referralOutcomeList", 
                        lovService.findAllForType(LOVType.referralOutcome.name()));
            } catch (Exception ex) {}
            try {
                if (client.getEnrollmentList() != null && 
                        client.getEnrollmentList().size() > 0) {                
                   Enrollment enrollment = 
                           enrollmentService.findByPersonalId(personalID).get(0);
                   mav.addObject("enrollment", enrollment);
                   if(enrollment != null && enrollment.getServicesList() != null
                           && enrollment.getServicesList().size() > 0){
                        mav.addObject("services", 
                                enrollment.getServicesList().get(0));
                    }else{
                        mav.addObject("services", new Services());
                    }
                }else{
                    mav.addObject("services", new Services());
                }
            } catch (Exception ex) {
                    mav.addObject("services", new Services());
            }
        mav.setViewName("services/servicesInfo");
        return mav;
    }
    
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    
    
    
       
/**
     *
     * @param mav
     * @param services
     * @param errors
     * @param principal
     * @return 
     * 
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView saveServicesInfo(final ModelAndView mav,
            final @ModelAttribute Services services,
            BindingResult errors, Principal principal){
        String resCode = "200";
        String message;
        String nextPage;
        try {
            
            if (!errors.hasErrors()) {
                
                final UserInfo userInfo = userService.findUserByPrincipal(principal);
      
                if (services.getId() != null) {
                    servicesService.updateServices(services, userInfo.getUserkey());
                    message = "Services data has been updated successfully";
                } else {
                    servicesService.addServices(services,  userInfo.getUserkey());
                    message = "Services data has been created successfully";
                }
                nextPage = "ui/client/"+services.getProjectEntryID().getPersonalID().getId();
            }else{
                resCode="500";
                LOG.debug(errors.toString());
                message = "Bad request. The request was not processed.";
                nextPage = "ui";
            }
        } catch (ServiceException ex) {
                resCode="500";
                message = ex.getMessage();
                LOG.debug("Services Exception:"+message);                
                nextPage = "ui";
        }
        mav.addObject("responseCode",resCode);
        mav.addObject("message",message);
        mav.addObject("nextPage",nextPage);
        mav.setViewName("common/response");        
        return mav;
        
    }    
}
