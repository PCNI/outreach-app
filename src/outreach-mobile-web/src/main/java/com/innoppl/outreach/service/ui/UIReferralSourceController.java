package com.innoppl.outreach.service.ui;


import com.innoppl.outreach.data.model.Client;
import com.innoppl.outreach.data.model.Enrollment;
import com.innoppl.outreach.data.model.OUser;
import com.innoppl.outreach.data.model.ReferralSource;
import com.innoppl.outreach.data.model.UserInfo;
import com.innoppl.outreach.service.ServiceException;
import com.innoppl.outreach.service.business.ClientService;
import com.innoppl.outreach.service.business.EnrollmentService;
import com.innoppl.outreach.service.business.LOVService;
import com.innoppl.outreach.service.business.ReferralSourceService;
import com.innoppl.outreach.service.business.UserService;
import com.innoppl.outreach.service.business.bean.LOVType;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
@RequestMapping("/ui/referralsource")
public class UIReferralSourceController {
    private final static Logger LOG = LoggerFactory.getLogger(UIReferralSourceController.class);
        
    @Autowired
    private EnrollmentService enrollmentService;
    
    @Autowired
    private ClientService clientService;
    
    @Autowired
    private LOVService lovService;
    
    @Autowired
    private UserService userService;
    
    
    @Autowired
    private ReferralSourceService  referralSourceService;

    
    @RequestMapping(value = "/{personalID}", method = RequestMethod.GET)
    public ModelAndView referralSourceInfo(final ModelAndView mav, 
            HttpServletRequest request, 
            final @PathVariable Integer personalID) {
            Client client = clientService.lookupClient(personalID); 
            mav.addObject("clientProfile", client);

            try {
                mav.addObject("referralSourceList", lovService.findAllForType(LOVType.referralSourceSimple.name()));
            } catch (Exception ex) {}
            try {
                if (client.getEnrollmentList() != null && 
                        client.getEnrollmentList().size() > 0) {                
                   Enrollment enrollment = 
                           enrollmentService.findByPersonalId(personalID).get(0);
                   mav.addObject("enrollment", enrollment);
                   if(enrollment != null && enrollment.getReferralSourceList() != null
                           && enrollment.getReferralSourceList().size() > 0){
                        mav.addObject("referralSource", 
                                enrollment.getReferralSourceList().get(0));
                    }else{
                        mav.addObject("referralSource", new ReferralSource());
                    }
                }else{
                    mav.addObject("referralSource", new ReferralSource());
                }
            } catch (Exception ex) {
                    mav.addObject("referralSource", new ReferralSource());
            }
        mav.setViewName("referralSource/referralSourceInfo");
        return mav;
    } 
    
    
       
/**
     *
     * @param mav
     * @param referralSource
     * @param errors
     * @param principal
     * @return 
     * 
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView saveReferralSourceInfo(final ModelAndView mav,
            final @ModelAttribute ReferralSource referralSource,
            BindingResult errors, Principal principal){
        String resCode = "200";
        String message;
        String nextPage;
        try {
            
            if (!errors.hasErrors()) {
                final UserInfo userInfo = userService.findUserByPrincipal(principal);
      
                if (referralSource.getId() != null) {
                    referralSourceService.updateReferralSource(referralSource, userInfo.getUserkey());
                    message = "Referral Source data has been updated successfully";
                } else {
                    referralSourceService.addReferralSource(referralSource,  userInfo.getUserkey());
                    message = "Referral Source data has been created successfully";
                }
                nextPage = "ui/client/"+referralSource.getProjectEntryID().getPersonalID().getId();
            }else{
                resCode="500";
                message = errors.toString();
                LOG.debug("Referral Source Errors:"+message);
                nextPage = "ui";
            }
        } catch (ServiceException ex) {
                resCode="500";
                message = ex.getMessage();
                LOG.debug("Referral Source Exception:"+ex);                
                nextPage = "ui";
        }
        mav.addObject("responseCode",resCode);
        mav.addObject("message",message);
        mav.addObject("nextPage",nextPage);
        mav.setViewName("common/response");        
        return mav;
        
    }    
}
