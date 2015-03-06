package com.innoppl.outreach.service.rest.controller;

import com.innoppl.outreach.data.model.LOV;
import com.innoppl.outreach.service.ServiceException;
import com.innoppl.outreach.service.business.LOVService;
import com.innoppl.outreach.service.rest.ResponseWrapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Jerald Mejarla
 */
@Controller
@RequestMapping("/service/lov")
public class LOVController {

    @Autowired
    private LOVService lovService;
    
    /**
     *
     * @param type
     * @return
     */
    @RequestMapping(value = "/{type}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseWrapper<LOV> findAllLOVsByType(final @PathVariable String type) {
        try {
            List<LOV> lovList = lovService.findAllForType(type);
            return new ResponseWrapper<>(null, lovList);
        } catch (ServiceException ex) {
            return new ResponseWrapper<>("en", ex);
        }
    }
    
    /**
     *
     * @param type
     * @param value
     * @return
     */
    @RequestMapping(value = "/{type}/{value}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseWrapper<LOV> findLOVByTypeAndValue(
            final @PathVariable String type,
            final @PathVariable String value) {
        try {
            LOV lovList = lovService.findByTypeAndValue(type, value);
            return new ResponseWrapper<>(null, lovList);
        } catch (ServiceException ex) {
            return new ResponseWrapper<>("en", ex);
        }
    }
}
