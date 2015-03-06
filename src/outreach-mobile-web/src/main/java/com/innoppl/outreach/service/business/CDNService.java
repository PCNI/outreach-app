package com.innoppl.outreach.service.business;

import com.innoppl.outreach.service.ServiceException;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Jerald Mejarla
 */
public interface CDNService {

    /**
     *
     * @param file
     * @return URL of the uploaded file in CDN.
     * @throws com.innoppl.outreach.service.ServiceException
     */
    String uploadFile(final MultipartFile file) throws ServiceException;
    
    /**
     *
     * @param base64EncodedData
     * @return URL of the uploaded file in CDN.
     * @throws ServiceException
     */
    String uploadFile(final String base64EncodedData) throws ServiceException;
    
    /**
     *
     * @param url
     * @return
     * @throws com.innoppl.outreach.service.ServiceException
     */
    String deleteFile(final String url) throws ServiceException;
}
