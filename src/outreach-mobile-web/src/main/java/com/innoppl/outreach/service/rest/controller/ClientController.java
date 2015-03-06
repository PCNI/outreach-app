package com.innoppl.outreach.service.rest.controller;

import com.innoppl.outreach.data.model.Client;
import com.innoppl.outreach.service.ServiceException;
import com.innoppl.outreach.service.business.ClientService;
import com.innoppl.outreach.service.business.bean.ClientSearchRequest;
import com.innoppl.outreach.service.rest.ResponseWrapper;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Jerald Mejarla
 */
@Controller
@RequestMapping("/service/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    /**
     *
     * @param client
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseWrapper<Client> addNewClient(
            @RequestBody final Client client, final HttpServletRequest request) {
        try {
            final Integer uid = Integer.parseInt(request.getAttribute("uid").toString());
            final Client savedClient = clientService.addClient(client, uid);
            return new ResponseWrapper<>("en", savedClient);
        } catch (ServiceException ex) {
            return new ResponseWrapper<>("en", ex);
        }
    }

    /**
     *
     * @param client
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseWrapper<Client> updateClient(
            @RequestBody final Client client, final HttpServletRequest request) {
        try {
            final Integer uid = Integer.parseInt(request.getAttribute("uid").toString());
            final Client savedClient = clientService.updateClient(client, uid);
            return new ResponseWrapper<>("en", savedClient);
        } catch (ServiceException ex) {
            return new ResponseWrapper<>("en", ex);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseWrapper<Client> lookupClient(final @PathVariable Integer id) {
        final Client lookupClient = clientService.lookupClient(id);
        return new ResponseWrapper<>(null, lookupClient);
    }

    /**
     *
     * @param clientSearchRequest
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public ResponseWrapper<Client> searchClient(
            @RequestBody final ClientSearchRequest clientSearchRequest) {
        final List<Client> searchClient = clientService.searchClient(clientSearchRequest);
        return new ResponseWrapper<>(null, searchClient);
    }
}
