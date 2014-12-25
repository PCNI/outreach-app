package com.innoppl.outreach.service.rest.controller;

import com.innoppl.outreach.data.model.OUser;
import com.innoppl.outreach.service.Messages;
import com.innoppl.outreach.service.ServiceException;
import com.innoppl.outreach.service.business.UserService;
import com.innoppl.outreach.service.rest.ResponseWrapper;
import com.innoppl.outreach.service.rest.controller.beans.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Jerald Mejarla
 */
@Controller
@RequestMapping("/service/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    @ResponseBody
    public ResponseWrapper<OUser> authenticate(
            final @RequestBody LoginRequest loginRequest,
            final @RequestParam(required = false,
                    defaultValue = "en") String locale) {
        final OUser oUser;
        try {
            oUser = userService.authenticate(loginRequest.getEmail(),
                    loginRequest.getPassword());
            return new ResponseWrapper<>(Messages.M_SUCCESS_LOGIN, locale, oUser);
        } catch (ServiceException ex) {
            return new ResponseWrapper<>(locale, ex);
        }
    }
}
