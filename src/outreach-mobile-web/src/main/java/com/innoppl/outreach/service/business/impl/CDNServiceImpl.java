package com.innoppl.outreach.service.business.impl;

import com.innoppl.outreach.data.AppConfigBean;
import com.innoppl.outreach.service.Errors;
import com.innoppl.outreach.service.ServiceException;
import com.innoppl.outreach.service.business.CDNService;
import com.innoppl.outreach.service.rest.ExceptionHelper;
import java.io.File;
import java.util.UUID;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Jerald Mejarla
 */
@Service("CDNService")
public class CDNServiceImpl implements CDNService {

    private final static Logger LOG
            = LoggerFactory.getLogger(CDNServiceImpl.class);

    @Autowired
    private AppConfigBean appConfigBean;

    @Override
    public String uploadFile(MultipartFile file) throws ServiceException {
        final String cdnOriginDir = appConfigBean.getCdnOriginDir();
        final String cdnBaseUrl = appConfigBean.getCdnBaseUrl();
        String fileUrl = null;
        try {
            final UUID guid = UUID.randomUUID();
            FileUtils.writeByteArrayToFile(new File(cdnOriginDir + "/" + guid + ".jpeg"),
                    file.getBytes());
            fileUrl = cdnBaseUrl + "/" + guid + ".jpeg";
        } catch (Exception ex) {
            LOG.error(ExceptionHelper.getStackTrace(ex));
            throw new ServiceException(Errors.E_FILE_UPLOAD_FAILED);
        }
        return fileUrl;
    }

    @Override
    public String deleteFile(String url) throws ServiceException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String uploadFile(String base64EncodedData) throws ServiceException {
        final String cdnOriginDir = appConfigBean.getCdnOriginDir();
        final String cdnBaseUrl = appConfigBean.getCdnBaseUrl();
        String fileUrl = null;
        try {
            final UUID guid = UUID.randomUUID();
            final byte[] fileBytes = Base64.decode(base64EncodedData.getBytes("UTF-8"));
            FileUtils.writeByteArrayToFile(new File(cdnOriginDir + "/" + guid + ".jpeg"),
                    fileBytes);
            fileUrl = cdnBaseUrl + "/" + guid + ".jpeg";
        } catch (Exception ex) {
            LOG.error(ExceptionHelper.getStackTrace(ex));
            throw new ServiceException(Errors.E_FILE_UPLOAD_FAILED);
        }
        return fileUrl;
    }

}
