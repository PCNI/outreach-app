package com.innoppl.outreach.service.ui;

import com.innoppl.outreach.service.rest.ExceptionHelper;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
}
