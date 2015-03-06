package com.innoppl.outreach.service.ui;

import com.innoppl.outreach.data.model.Client;
import com.innoppl.outreach.data.model.Enrollment;
import com.innoppl.outreach.data.model.Project;
import com.innoppl.outreach.data.model.UserInfo;
import com.innoppl.outreach.service.ServiceException;
import com.innoppl.outreach.service.business.ClientService;
import com.innoppl.outreach.service.business.EnrollmentService;
import com.innoppl.outreach.service.business.LOVService;
import com.innoppl.outreach.service.business.ProjectService;
import com.innoppl.outreach.service.business.UserService;
import com.innoppl.outreach.service.business.bean.LOVType;
import com.innoppl.outreach.service.logger.InjectLogging;
import com.innoppl.outreach.service.logger.LogLevel;
import com.innoppl.outreach.service.rest.ExceptionHelper;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.jboss.logging.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Jerald Mejarla
 */
@Controller
@RequestMapping("/ui/client")
public class UIClientDataController {

    private final static Logger LOG
            = LoggerFactory.getLogger(UIClientDataController.class);
    
    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private LOVService lovService;
    
    private EnrollmentService enrollmentService;

    /**
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex, HttpServletRequest request) {
        final ModelAndView mav = new ModelAndView("error");
        mav.addObject("message", ExceptionHelper.getStackTrace(ex));
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
     * @param personalID
     * @param isError
     * @param message
     * @param responseCode
     * @return
     */
    @RequestMapping(value = "/{personalID}", method = RequestMethod.GET)
    public ModelAndView displayClientDetails(ModelAndView mav,
            final @PathVariable Integer personalID,
            final boolean isError,
            final String message,
            @RequestParam(required = false) String responseCode) {
        final Client client = clientService.lookupClient(personalID);
        
        mav.addObject("clientProfile", client);

        if (client.getGender() != null && client.getGender() != -1) {
            LOG.debug("Gender Value :'"+client.getGender()+"'");
            mav = lovService.injectLov(mav, LOVType.gender.name(), String.valueOf(client.getGender()));
        } else {
            mav.addObject("gender", "N/A");
        }

        if (client.getRace() != null && client.getRace() != -1) {
            mav = lovService.injectLov(mav, LOVType.race.name(), String.valueOf(client.getRace()));
        } else {
            mav.addObject("race", "N/A");
        }

        if (client.getEthnicity() != null && client.getEthnicity() != -1) {
            mav = lovService.injectLov(mav, LOVType.ethnicity.name(), String.valueOf(client.getEthnicity()));
        } else {
            mav.addObject("ethnicity", "N/A");
        }

        if (client.getEnrollmentList() != null && client.getEnrollmentList().size() > 0) {
            mav.addObject("enrollmentID", client.getEnrollmentList().get(0).getId());
        } else {
            mav.addObject("enrollmentID", "-1");
        }
        mav.addObject("projectList",projectService.findAllProjects());
        mav.addObject("responseCode", responseCode);
        mav.addObject("editable", true);
        mav.setViewName("client/view");
        return mav;
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ModelAndView displayClientInfo0(final ModelAndView mav,
            final @RequestParam String name,
            final @RequestParam Date dob) {
        final Client client = new Client();
        client.setFirstName(name);
        client.setDob(dob);
        mav.addObject("clientProfile", client);
        try {
            mav.addObject("GenderList", lovService.findAllForType(LOVType.gender.name()));
            mav.addObject("SSNDataQualityList", lovService.findAllForType(LOVType.ssnDataQuality.name()));
            mav.addObject("RaceList", lovService.findAllForType(LOVType.race.name()));
            mav.addObject("DobDataQualityList", lovService.findAllForType(LOVType.dobDataQuality.name()));
            mav.addObject("NameDataQualityList", lovService.findAllForType(LOVType.nameDataQuality.name()));
            mav.addObject("EthnicityList", lovService.findAllForType(LOVType.ethnicity.name()));
            mav.addObject("fiveValDKRefused", lovService.findAllForType(LOVType.fiveValDKRefused.name()));
            mav.addObject("projectList",projectService.findAllProjects());
            mav.addObject("editable", false);
        } catch (Exception ex) {
            //TODO:  Handle failure here.
        }
        mav.setViewName("client/info");
        return mav;
    }

    @RequestMapping(value = "/info/{personalID}", method = RequestMethod.GET)
    public ModelAndView displayClientInfo(ModelAndView mav,
            final @PathVariable Integer personalID,
            final boolean isError,
            final String message) {
        final Client client = clientService.lookupClient(personalID);
        mav.addObject("clientProfile", client);
        Integer projectID = null; 
        
        if(client != null){
            List<Enrollment> enrollmentList = client.getEnrollmentList();
            if(enrollmentList != null && enrollmentList.size() > 0){
                Enrollment enrollment = enrollmentList.get(0);
                if(enrollment != null)
                    projectID = enrollment.getProjectID().getId();
                enrollment = null;
                client.setProjectID(projectID);
            }
        }

        if (client.getGender() != null) {
            mav = lovService.injectLov(mav, LOVType.gender.name(), String.valueOf(client.getGender()));
        } else {
            mav.addObject("gender", "N/A");
        }

        if (client.getRace() != null) {
            mav = lovService.injectLov(mav, LOVType.race.name(), String.valueOf(client.getRace()));
        } else {
            mav.addObject("race", "N/A");
        }

        if (client.getRace() != null) {
            mav = lovService.injectLov(mav, LOVType.ethnicity.name(), String.valueOf(client.getEthnicity()));
        } else {
            mav.addObject("ethnicity", "N/A");
        }
        
        try {
            mav.addObject("GenderList", lovService.findAllForType(LOVType.gender.name()));
            mav.addObject("SSNDataQualityList", lovService.findAllForType(LOVType.ssnDataQuality.name()));
            mav.addObject("RaceList", lovService.findAllForType(LOVType.race.name()));
            mav.addObject("DobDataQualityList", lovService.findAllForType(LOVType.dobDataQuality.name()));
            mav.addObject("NameDataQualityList", lovService.findAllForType(LOVType.nameDataQuality.name()));
            mav.addObject("EthnicityList", lovService.findAllForType(LOVType.ethnicity.name()));
            mav.addObject("fiveValDKRefused", lovService.findAllForType(LOVType.fiveValDKRefused.name()));
            mav.addObject("projectList",projectService.findAllProjects());
            mav.addObject("editable", false);
        } catch (Exception ex) {
            //TODO:  Handle failure here.
        }
        mav.setViewName("client/info");
        return mav;
    }

    /**
     *
     * @param clientProfile
     * @param errors
     * @param projectID
     * @param principal
     * @return
     */
    @InjectLogging(LogLevel.DEBUG)
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView saveClientInfo(final ModelAndView mav,
            final @ModelAttribute Client clientProfile, 
            final @Param Integer projectID,
            BindingResult errors,
            Principal principal) {
        String resCode = "200";
        String message;
        String nextPage;
        Integer clientId;
        try {

            if (!errors.hasErrors()) {
                final UserInfo userInfo = userService.findUserByPrincipal(principal);
                   if (clientProfile.getId() != null) {
                        Client updateClient = clientService.updateClient(clientProfile, userInfo.getUserkey());
                        message = "Client profile has been updated successfully";
                        clientId = updateClient.getId();
                    } else {
                        Client addClient = clientService.addClient(clientProfile, userInfo.getUserkey());
                        message = "Client profile has been created successfully";
                        clientId = addClient.getId();
                    }
                    nextPage = "ui/client/"+clientId;
            } else {
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
