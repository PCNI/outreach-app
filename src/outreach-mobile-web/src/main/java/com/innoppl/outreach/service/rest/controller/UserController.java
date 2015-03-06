package com.innoppl.outreach.service.rest.controller;

import com.innoppl.outreach.data.model.UserInfo;
import com.innoppl.outreach.service.ServiceException;
import com.innoppl.outreach.service.business.UserService;
import com.innoppl.outreach.service.rest.ResponseWrapper;
import com.innoppl.outreach.service.rest.controller.beans.LoginRequest;
import com.innoppl.outreach.service.rest.controller.beans.LoginResponse;
import java.io.IOException;
import java.io.Serializable;
import javax.servlet.http.HttpServletResponse;
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
    public LoginResponse authenticate(
            final @RequestBody LoginRequest loginRequest,
            final @RequestParam(required = false,
                    defaultValue = "en") String locale,
            final HttpServletResponse response) {
        final LoginResponse loginResponse = new LoginResponse();
        try {
            final UserInfo userInfo = userService.authenticate(loginRequest.getUserName(),
                    loginRequest.getPassword());
            //loginResponse.setAccess_token(oUser.getToken());
            //loginResponse.setExpires_in(oUser.getTokenExpiry());
        } catch (ServiceException ex) {
            final ResponseWrapper<Serializable> responseWrapper
                    = new ResponseWrapper<>("en", ex);
            try {
                response.sendError(responseWrapper.getHeader().getStatus(),
                        responseWrapper.getHeader().getMessage());
            } catch (IOException ex1) {
                //Ignore Error;
            }
        }
        return loginResponse;
    }
}
