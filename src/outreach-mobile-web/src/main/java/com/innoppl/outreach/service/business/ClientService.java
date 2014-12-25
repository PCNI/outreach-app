package com.innoppl.outreach.service.business;

import com.innoppl.outreach.data.model.Client;
import com.innoppl.outreach.data.model.VeteranInfo;
import com.innoppl.outreach.service.business.bean.ClientSearchRequest;
import java.util.List;

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
     * @return
     */
    Client addClient(final Client client, final Integer uid);
    
    /**
     *
     * @param veteranInfo
     * @param uid
     * @return
     */
    VeteranInfo addVeteranInfo(final VeteranInfo veteranInfo, final Integer uid);
}
