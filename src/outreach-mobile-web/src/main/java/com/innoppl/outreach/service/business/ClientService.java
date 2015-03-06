package com.innoppl.outreach.service.business;

import com.innoppl.outreach.data.model.Client;
import com.innoppl.outreach.service.ServiceException;
import com.innoppl.outreach.service.business.bean.ClientSearchRequest;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Jerald Mejarla
 */
public interface ClientService {

    /**
     *
     * @param clientSearchRequest
     * @return
     */
    List<Client> searchClient(final ClientSearchRequest clientSearchRequest);

    /**
     *
     * @param id
     * @return
     */
    Client lookupClient(final Integer id);

    /**
     *
     * @param client
     * @param uid
     * @param file
     * @return
     * @throws com.innoppl.outreach.service.ServiceException
     */
    Client addClient(final Client client, final Integer uid,
            final MultipartFile file) throws ServiceException;

    /**
     *
     * @param client
     * @param uid
     * @param file
     * @return
     * @throws com.innoppl.outreach.service.ServiceException
     */
    Client updateClient(final Client client, final Integer uid,
            final MultipartFile file) throws ServiceException;
    
    /**
     *
     * @param client
     * @param uid
     * @return
     * @throws com.innoppl.outreach.service.ServiceException
     */
    Client addClient(final Client client, final Integer uid) throws ServiceException;

    /**
     *
     * @param client
     * @param uid
     * @return
     * @throws com.innoppl.outreach.service.ServiceException
     */
    Client updateClient(final Client client, final Integer uid) throws ServiceException;

}
