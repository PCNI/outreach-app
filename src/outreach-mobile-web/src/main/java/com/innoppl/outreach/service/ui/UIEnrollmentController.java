package com.innoppl.outreach.service.ui;


import com.innoppl.outreach.data.model.Client;
import com.innoppl.outreach.data.model.Enrollment;
import com.innoppl.outreach.data.model.OUser;
import com.innoppl.outreach.data.model.UserInfo;
import com.innoppl.outreach.service.ServiceException;
import com.innoppl.outreach.service.business.ClientService;
import com.innoppl.outreach.service.business.EnrollmentService;
import com.innoppl.outreach.service.business.LOVService;
import com.innoppl.outreach.service.business.ProjectService;
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
@RequestMapping("/ui/enrollment")
public class UIEnrollmentController {
        private final static Logger LOG = LoggerFactory.getLogger(UIClientController.class);
        
    @Autowired
    private EnrollmentService enrollmentService;
    
    
    @Autowired
    private ClientService clientService;
    
    @Autowired
    private LOVService lovService;
    
    @Autowired
    private UserService userService;
    

    @RequestMapping(value = "/{personalID}", method = RequestMethod.GET)
    public ModelAndView enrollmentInfo(final ModelAndView mav, 
            HttpServletRequest request, 
            final @PathVariable Integer personalID) {
        Client client = clientService.lookupClient(personalID); 
        mav.addObject("clientProfile", client);
            try {
                mav.addObject("fiveValDKRefused", lovService.findAllForType(LOVType.fiveValDKRefused.name()));
                mav.addObject("residencePriorList", lovService.findAllForType(LOVType.residencePrior.name()));
                mav.addObject("residencePriorLengthOfStayList", lovService.findAllForType(LOVType.residencePriorLengthOfStay.name()));
                mav.addObject("relationshipToHoHList", lovService.findAllForType(LOVType.relationshipToHoH.name()));
                mav.addObject("housingStatusList", lovService.findAllForType(LOVType.housingStatus.name()));

            } catch (Exception ex) {}
            try {
                if (client.getEnrollmentList() != null && client.getEnrollmentList().size() > 0) {                
                    mav.addObject("enrollment",enrollmentService.findByPersonalId(personalID).get(0));
                }else{
                    mav.addObject("enrollment", new Enrollment());
                }
            } catch (Exception ex) {
                mav.addObject("enrollment", new Enrollment());
            }
        mav.setViewName("enrollment/enrollmentInfo");
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
     * @param enrollment
     * @param errors
     * @param principal
     * @return 
     * 
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView saveEnrollmentInfo(final ModelAndView mav,
            final @ModelAttribute Enrollment enrollment,
            BindingResult errors, Principal principal){
        String resCode = "200";
        String message;
        String nextPage;
        try {
            
            if (!errors.hasErrors()) {
                final UserInfo userInfo = userService.findUserByPrincipal(principal);
//                final Project projectID = oUser.getProjectID();
//                enrollment.setProjectID(projectID);            
                if (enrollment.getId() != null) {
                    enrollmentService.updateEnrollment(enrollment, userInfo.getUserkey());
                    message = "Enrollment data has been updated successfully";
                } else {
                    enrollmentService.addEnrollment(enrollment,   userInfo.getUserkey());
                    message = "Enrollment data has been created successfully";
                }
                nextPage = "ui/client/"+enrollment.getPersonalID().getId();
            }else{
                resCode="500";
                message = errors.toString();
                nextPage = "ui";
            }
        } catch (ServiceException ex) {
                resCode="500";
                message = ex.getMessage();
                nextPage = "ui";
        }
        mav.addObject("responseCode",resCode);
        mav.addObject("message",message);
        mav.addObject("nextPage",nextPage);
        mav.setViewName("common/response");        
        return mav;
        
    }    
}
