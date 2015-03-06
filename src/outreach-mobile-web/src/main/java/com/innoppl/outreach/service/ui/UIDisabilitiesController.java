package com.innoppl.outreach.service.ui;

import com.innoppl.outreach.data.model.Client;
import com.innoppl.outreach.data.model.Disabilities;
import com.innoppl.outreach.data.model.Enrollment;
import com.innoppl.outreach.data.model.LOV;
import com.innoppl.outreach.data.model.OUser;
import com.innoppl.outreach.data.model.UserInfo;
import com.innoppl.outreach.service.ServiceException;
import com.innoppl.outreach.service.business.ClientService;
import com.innoppl.outreach.service.business.DisabilitiesService;
import com.innoppl.outreach.service.business.EnrollmentService;
import com.innoppl.outreach.service.business.LOVService;
import com.innoppl.outreach.service.business.UserService;
import com.innoppl.outreach.service.business.bean.LOVType;
import com.innoppl.outreach.service.logger.InjectLogging;
import com.innoppl.outreach.service.logger.LogLevel;
import com.innoppl.outreach.service.ui.bean.DisabilitiesListWrapper;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
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
 * @author Jerald Mejarla
 */
@Controller
@RequestMapping("/ui/disability")
public class UIDisabilitiesController {

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private LOVService lovService;

    @Autowired
    private UserService userService;

    @Autowired
    private DisabilitiesService disabilitiesService;

    /**
     *
     * @param mav
     * @param request
     * @param personalID
     * @return
     */
    @InjectLogging(LogLevel.DEBUG)
    @RequestMapping(value = "/{personalID}", method = RequestMethod.GET)
    public ModelAndView list(final ModelAndView mav,
            HttpServletRequest request,
            final @PathVariable Integer personalID) {
        final Client client = clientService.lookupClient(personalID);
        final DisabilitiesListWrapper disabilitiesListWrapper = new DisabilitiesListWrapper();
        try {
            mav.addObject("clientProfile", client);
            mav.addObject("fiveValDKRefused", lovService.findAllForType(LOVType.fiveValDKRefused.name()));
            mav.addObject("noYes", lovService.findAllForType(LOVType.noYes.name()));
            mav.addObject("pathHowConfirmed", lovService.findAllForType(LOVType.pathHowConfirmed.name()));
            mav.addObject("pathSMIInformation", lovService.findAllForType(LOVType.pathSMIInformation.name()));

            final List<LOV> disabilityTypeList = lovService.findAllForType(LOVType.disabilityType.name());
            final Map<Integer, String> disabilityTypeMap = new HashMap<>();
            final List<Disabilities> dummyDisabilitiesList = new ArrayList<>(
                    disabilityTypeList.size());

            if (client.getEnrollmentList() != null
                    && client.getEnrollmentList().size() > 0) {
                Enrollment enrollment
                        = enrollmentService.findByPersonalId(personalID).get(0);
                for (int i = 0; i < disabilityTypeList.size(); i++) {
                    disabilityTypeMap.put(Integer.parseInt(disabilityTypeList.get(i).getValueString()),
                            disabilityTypeList.get(i).getDisplayName());
                    dummyDisabilitiesList.add(new Disabilities(Integer.parseInt(
                            disabilityTypeList.get(i).getValueString()),
                            enrollment));
                }
                disabilitiesListWrapper.setDisabilitiesList(dummyDisabilitiesList);
                mav.addObject("disabilityTypeMap", disabilityTypeMap);
                mav.addObject("enrollment", enrollment);
                if (enrollment != null && enrollment.getDisabilitiesList() != null
                        && enrollment.getDisabilitiesList().size() > 0) {
                    disabilitiesListWrapper.setDisabilitiesList(
                            enrollment.getDisabilitiesList());
                    mav.addObject("disabilitiesListWrapper",
                            disabilitiesListWrapper);
                } else {
                    mav.addObject("disabilitiesListWrapper", disabilitiesListWrapper);
                }
            } else {
                mav.addObject("disabilitiesListWrapper", disabilitiesListWrapper);
            }
        } catch (Exception ex) {
            mav.addObject("disabilitiesListWrapper", disabilitiesListWrapper);
        }
        mav.setViewName("disabilities/edit");
        return mav;
    }

    /**
     *
     * @param mav
     * @param disabilitiesListWrapper
     * @param errors
     * @param principal
     * @return
     */
    @InjectLogging(LogLevel.DEBUG)
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(final ModelAndView mav,
            final @ModelAttribute("disabilitiesListWrapper") DisabilitiesListWrapper disabilitiesListWrapper,
            BindingResult errors, Principal principal) {
        String resCode = "200";
        String message;
        String nextPage;

        try {
            if (!errors.hasErrors()) {
                final UserInfo userInfo = userService.findUserByPrincipal(principal);
                final List<Disabilities> diabilitiesList = 
                        disabilitiesListWrapper.getDisabilitiesList();
                disabilitiesService.addDisabilites(diabilitiesList,
                        userInfo.getUserkey());
                message = "Disabilities data saved successfully";
                final Enrollment enrollment = enrollmentService.findById(
                        diabilitiesList.get(0).getProjectEntryID().getId());
                nextPage = "ui/client/" + enrollment.getPersonalID().getId();
            } else {
                resCode = "500";
                message = errors.toString();
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
