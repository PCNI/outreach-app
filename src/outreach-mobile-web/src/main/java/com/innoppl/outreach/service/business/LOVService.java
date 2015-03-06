package com.innoppl.outreach.service.business;

import com.innoppl.outreach.data.model.LOV;
import com.innoppl.outreach.service.ServiceException;
import java.util.List;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Jerald Mejarla
 */
public interface LOVService {
    
    /**
     *
     * @param lovType
     * @return
     * @throws ServiceException
     */
    List<LOV> findAllForType(final String lovType) throws ServiceException;
    
    /**
     *
     * @param lovType
     * @param value
     * @return
     * @throws com.innoppl.outreach.service.ServiceException
     */
    LOV findByTypeAndValue(final String lovType, final String value) throws ServiceException;
    
    /**
     *
     * @param mav
     * @param lovType
     * @param value
     * @return
     */
    ModelAndView injectLov(final ModelAndView mav, final String lovType, final String value);
}
