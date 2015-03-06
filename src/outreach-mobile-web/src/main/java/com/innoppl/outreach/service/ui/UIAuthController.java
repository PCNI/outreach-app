package com.innoppl.outreach.service.ui;

import com.innoppl.outreach.data.model.OUser;
import com.innoppl.outreach.service.ServiceException;
import com.innoppl.outreach.service.business.UserService;
import com.innoppl.outreach.service.rest.ExceptionHelper;
import java.security.Principal;
import java.util.Random;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Jerald Mejarla
 */
@Controller
public class UIAuthController {

    private final static Logger LOG = LoggerFactory.getLogger(UIAuthController.class);

    @Autowired
    private UserService userService;

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex, HttpServletRequest request) {
        final ModelAndView mav = new ModelAndView("error");
        mav.addObject("message", ExceptionHelper.getStackTrace(ex));
        return mav;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayhome() {
        return "redirect:/ui";
    }

    @RequestMapping(value = "/denied", method = RequestMethod.GET)
    public String displayDeniedPage(final Model model) {
        return "login";
    }

    @RequestMapping(value = "/ui", method = RequestMethod.GET)
    public String displayIndexPage(final Model model) {
        return "main";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(final Model model,
            @RequestParam(required = false) String message) {
        if (message != null) {
            model.addAttribute("message", message);
        }
        return "login";
    }

    @RequestMapping(value = "/logout/success", method = RequestMethod.GET)
    public String getLogoutPage(final Model model) {
        return "redirect:/login";
    }

    @RequestMapping(value = "/login/failure", method = RequestMethod.GET)
    public String getFailurePage(final Model model) {
        return "redirect:/login?message=Authentication Failed";
    }

    @RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
    public String forgotPassword(final Model model) {
        return "forgotpassword";
    }

    /**
     *
     * @param mav
     * @param email
     * @param errors
     * @param principal
     * @return
     *
     */
    @Transactional
    @RequestMapping(value = "/resetpassword", method = RequestMethod.GET)
    public ModelAndView resetPassword(final ModelAndView mav,
            @RequestParam(required = true) String email,
            BindingResult errors, Principal principal) {
        String resCode = "200";
        String message;
        String nextPage;
        if (!errors.hasErrors()) {
            final OUser oUser = userService.findUserByEmail(email);
            nextPage = "login";
            if (oUser != null && oUser.getIsDeleted() == 0) {
                try {
                    userService.resetPassword(email);
                } catch (ServiceException ex) {
                    resCode = "500";
                    message = "We are unable to process your request at this time.";
                    nextPage = "ui";
                }
                message = "A new password has been sent to your registered email address. Please check your inbox.";
            } else if (oUser != null && oUser.getIsDeleted() == 1) {
                resCode = "500";
                message = "The registered account has been removed. Please contact the administrator.";
                nextPage = "forgotpassword";
            } else {
                resCode = "500";
                message = "Email address is not registered with our database.";
                nextPage = "forgotpassword";
            }

        } else {
            resCode = "500";
            message = "Bad request. The request was not processed.";
            nextPage = "ui";
        }
        mav.addObject("responseCode", resCode);
        mav.addObject("message", message);
        mav.addObject("nextPage", nextPage);
        mav.setViewName("common/response");
        return mav;

    }

}
