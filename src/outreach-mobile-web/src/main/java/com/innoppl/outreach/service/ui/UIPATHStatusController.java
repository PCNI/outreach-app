package com.innoppl.outreach.service.ui;


import com.innoppl.outreach.data.model.Client;
import com.innoppl.outreach.data.model.Enrollment;
import com.innoppl.outreach.data.model.OUser;
import com.innoppl.outreach.data.model.PATHStatus;
import com.innoppl.outreach.data.model.UserInfo;
import com.innoppl.outreach.service.ServiceException;
import com.innoppl.outreach.service.business.ClientService;
import com.innoppl.outreach.service.business.EnrollmentService;
import com.innoppl.outreach.service.business.LOVService;
import com.innoppl.outreach.service.business.PATHStatusService;
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
@RequestMapping("/ui/pathstatus")
public class UIPATHStatusController {
        private final static Logger LOG = LoggerFactory.getLogger(UIPATHStatusController.class);
        
    @Autowired
    private EnrollmentService enrollmentService;
    
    @Autowired
    private ClientService clientService;
    
    @Autowired
    private LOVService lovService;
    
    @Autowired
    private UserService userService;
    
    
    @Autowired
    private PATHStatusService  pathStatusService;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    
    @RequestMapping(value = "/{personalID}", method = RequestMethod.GET)
    public ModelAndView pathStatusInfo(final ModelAndView mav, 
            HttpServletRequest request, 
            final @PathVariable Integer personalID) {
            Client client = clientService.lookupClient(personalID); 
            mav.addObject("clientProfile", client);

            try {
                mav.addObject("noYes", lovService.findAllForType(LOVType.noYes.name()));
                mav.addObject("reasonNotEnrolledList", 
                        lovService.findAllForType(LOVType.reasonNotEnrolled.name()));
            } catch (Exception ex) {}
            try {
                if (client.getEnrollmentList() != null && 
                        client.getEnrollmentList().size() > 0) {                
                   Enrollment enrollment = 
                           enrollmentService.findByPersonalId(personalID).get(0);
                   mav.addObject("enrollment", enrollment);
                   if(enrollment != null && enrollment.getPATHStatusList() != null
                           && enrollment.getPATHStatusList().size() > 0){
                        mav.addObject("pathStatus", 
                                enrollment.getPATHStatusList().get(0));
                    }else{
                        mav.addObject("pathStatus", new PATHStatus());
                    }
                }else{
                    mav.addObject("pathStatus", new PATHStatus());
                }
            } catch (Exception ex) {
                    mav.addObject("pathStatus", new PATHStatus());
            }
        mav.setViewName("pathStatus/pathStatusInfo");
        return mav;
    } 
    
    
       
/**
     *
     * @param mav
     * @param pathStatus
     * @param errors
     * @param principal
     * @return 
     * 
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView savePATHStatusInfo(final ModelAndView mav,
            final @ModelAttribute PATHStatus pathStatus,
            BindingResult errors, Principal principal){
        String resCode = "200";
        String message;
        String nextPage;
        try {
            
            if (!errors.hasErrors()) {
                try {
                    LOG.debug("pathStatus: " + pathStatus.toJson());
                    LOG.debug("pathStatus: " + pathStatus.getProjectEntryID());
                } catch (Exception ex) {
                    //Ignore Exception
                }
                final UserInfo userInfo = userService.findUserByPrincipal(principal);
      
                if (pathStatus.getId() != null) {
                    pathStatusService.updatePATHStatus(pathStatus, userInfo.getUserkey());
                    message = "Path Status data has been updated successfully";
                } else {
                    pathStatusService.addPATHStatus(pathStatus,  userInfo.getUserkey());
                    message = "Path Status data has been created successfully";
                }
                nextPage = "ui/client/"+pathStatus.getProjectEntryID().getPersonalID().getId();
            }else{
                resCode="500";
                message = errors.toString();
                LOG.debug("PATH Status Errors:"+message);
                nextPage = "ui";
            }
        } catch (ServiceException ex) {
                resCode="500";
                message = ex.getMessage();
                LOG.debug("PATH Status Exception:"+message);                
                nextPage = "ui";
        }
        mav.addObject("responseCode",resCode);
        mav.addObject("message",message);
        mav.addObject("nextPage",nextPage);
        mav.setViewName("common/response");        
        return mav;
        
    }    
}
