package com.innoppl.outreach.service.business.impl;

import com.innoppl.outreach.data.dao.ClientDao;
import com.innoppl.outreach.data.dao.VeteranInfoDao;
import com.innoppl.outreach.data.model.Client;
import com.innoppl.outreach.data.model.VeteranInfo;
import com.innoppl.outreach.service.business.ClientService;
import com.innoppl.outreach.service.logger.InjectLogging;
import com.innoppl.outreach.service.logger.LogLevel;
import com.innoppl.outreach.service.business.bean.ClientSearchRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jerald Mejarla
 */
@Service("ClientService")
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDao clientDao;
    
    @Autowired
    private VeteranInfoDao veteranInfoDao;

    @InjectLogging(LogLevel.DEBUG)
    @Override
    public List<Client> searchClient(ClientSearchRequest clientSearchRequest) {

        final String fName = clientSearchRequest.getFirstName() == null ? ""
                : clientSearchRequest.getFirstName().trim();
        final String lName = clientSearchRequest.getLastName() == null ? ""
                : clientSearchRequest.getLastName().trim();
        final String ssn = clientSearchRequest.getSsn() == null ? ""
                : clientSearchRequest.getSsn().trim();
        final Integer sAge = clientSearchRequest.getsAge();
        final Integer eAge = clientSearchRequest.geteAge();
        return clientDao.searchClient(fName, lName, ssn, sAge, eAge);
    }

    @InjectLogging(LogLevel.DEBUG)
    @Override
    public Client lookupClient(Integer id) {
        return clientDao.findById(id);
    }

    @Override
    public Client addClient(Client client, Integer uid) {
        return clientDao.save(client, uid);
    }

    @Override
    public VeteranInfo addVeteranInfo(VeteranInfo veteranInfo, Integer uid) {
        return veteranInfoDao.save(veteranInfo, uid);
    }
}
