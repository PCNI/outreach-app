package com.innoppl.outreach.service.rest.interceptor;

import com.innoppl.outreach.data.model.OUser;
import com.innoppl.outreach.service.business.UserService;
import com.innoppl.outreach.service.logger.InjectLogging;
import com.innoppl.outreach.service.logger.LogLevel;
import com.innoppl.outreach.service.rest.ExceptionHelper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author Jerald Mejarla
 */
public class TokenInterceptorAdapter extends HandlerInterceptorAdapter {

    private final static Logger LOG
            = LoggerFactory.getLogger(TokenInterceptorAdapter.class);

    @Autowired
    private UserService userService;

    /**
     *
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @param handler {@link Object}
     * @return true if handler is successful
     * @throws Exception
     */
    @InjectLogging(LogLevel.DEBUG)
    @Override
    public boolean preHandle(final HttpServletRequest request,
            final HttpServletResponse response, Object handler)
            throws Exception {
        final String token = request.getHeader("Authorization");
        LOG.debug("Raw Token: " + token);
        return true;
//        try {
//            if (token != null && !token.isEmpty()) {
//                final String[] tokenVals = token.split("\\s+");
//                LOG.debug("Tokens: " + tokenVals.length);
//                LOG.debug("Tokens: " + tokenVals[0]);
//                LOG.debug("Tokens: " + tokenVals[1]);
//                if (tokenVals != null && tokenVals.length == 2
//                        && "Bearer".equals(tokenVals[0])) {
//                    final String decodedToken = new String(Base64.decode(
//                            tokenVals[1].getBytes("UTF-8")), "UTF-8");
//                    LOG.debug("decodedToken: " + decodedToken);
//                    final OUser oUser = userService.verifyToken(decodedToken);
//                    LOG.debug("User reverse Lookup: " + oUser);
//                    if (oUser != null) {
//                        return true;
//                    } else {
//                        response.sendError(401);
//                        return false;
//                    }
//                } else {
//                    response.sendError(401);
//                    return false;
//                }
//            } else {
//                response.sendError(401);
//                return false;
//            }
//        } catch (Exception ex) {
//            response.sendError(401);
//            LOG.error("Access Denied for Token: " + token + "\n"
//                    + ExceptionHelper.getStackTrace(ex));
//            return false;
//        }
    }
}
