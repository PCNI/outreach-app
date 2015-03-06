package com.innoppl.outreach.service.business.impl;

import com.innoppl.outreach.data.dao.ClientDao;
import com.innoppl.outreach.data.dao.VeteranInfoDao;
import com.innoppl.outreach.data.model.Client;
import com.innoppl.outreach.data.model.Enrollment;
import com.innoppl.outreach.data.model.Project;
import com.innoppl.outreach.data.model.VeteranInfo;
import com.innoppl.outreach.service.Errors;
import com.innoppl.outreach.service.ServiceException;
import com.innoppl.outreach.service.business.CDNService;
import com.innoppl.outreach.service.business.ClientService;
import com.innoppl.outreach.service.business.EnrollmentService;
import com.innoppl.outreach.service.business.ProjectService;
import com.innoppl.outreach.service.logger.InjectLogging;
import com.innoppl.outreach.service.logger.LogLevel;
import com.innoppl.outreach.service.business.bean.ClientSearchRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private CDNService cdnService;
    
    @Autowired
    private ProjectService projectService;

    @Autowired
    private EnrollmentService enrollmentService;

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

    @InjectLogging(LogLevel.DEBUG)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Client addClient(Client client, Integer uid, MultipartFile file)
            throws ServiceException {
        if (client.getId() == null) {
            try {
                if (file != null && file.getSize() > 0) {
                    final String uploadFile = cdnService.uploadFile(file);
                    client.setImageUrl(uploadFile);
                } else {
                    if (client.getImageData() != null && !client.getImageData().isEmpty()) {
                        final String uploadFile = cdnService.uploadFile(client.getImageData());
                        client.setImageUrl(uploadFile);
                    }
                }

                final VeteranInfo veteranInfo = client.getVeteranInfo();
                if (veteranInfo != null) {
                    VeteranInfo veteranInfoSaved = veteranInfoDao.save(veteranInfo, uid);
                    client.setVeteranInfo(veteranInfoSaved);
                }
                final Client savedClient = clientDao.save(client, uid);
                final Enrollment enrollment = new Enrollment();
                enrollment.setPersonalID(savedClient);
                Project project = projectService.findById(client.getProjectID());
//                final OUser oUser = userDao.findById(uid);
                enrollment.setProjectID(project);
                enrollmentService.addEnrollment(enrollment, uid);
                return savedClient;
            } catch (ServiceException ex) {
                throw ex;
            } catch (Exception ex) {
                throw new ServiceException(ex, Errors.E_SAVE_CLIENT_FAILED);
            }
        } else {
            throw new ServiceException(Errors.E_ID_NOT_NULL);
        }
    }

    @InjectLogging(LogLevel.DEBUG)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Client updateClient(Client client, Integer uid, MultipartFile file)
            throws ServiceException {
        if (client.getId() != null) {
            try {
                if (file != null && file.getSize() > 0) {
                    final String uploadFile = cdnService.uploadFile(file);
                    client.setImageUrl(uploadFile);
                } else {
                    if (client.getImageData() != null && !client.getImageData().isEmpty()) {
                        final String uploadFile = cdnService.uploadFile(client.getImageData());
                        client.setImageUrl(uploadFile);
                    } else {
                        client.setImageUrl(clientDao.findById(client.getId()).getImageUrl());
                    }
                }

                final VeteranInfo veteranInfo = client.getVeteranInfo();

                if (veteranInfo != null) {
                    VeteranInfo veteranInfoSaved = veteranInfoDao.save(veteranInfo, uid);
                    client.setVeteranInfo(veteranInfoSaved);
                }
                return clientDao.save(client, uid);
            } catch (ServiceException ex) {
                throw ex;
            } catch (Exception ex) {
                throw new ServiceException(ex, Errors.E_SAVE_CLIENT_FAILED);
            }
        } else {
            throw new ServiceException(Errors.E_ID_NULL);
        }
    }

    @Override
    public Client addClient(Client client, Integer uid) throws ServiceException {
        return addClient(client, uid, null);
    }

    @Override
    public Client updateClient(Client client, Integer uid) throws ServiceException {
        return updateClient(client, uid, null);
    }
}
