package com.innoppl.outreach.service.business;

import com.innoppl.outreach.data.model.Disabilities;
import com.innoppl.outreach.service.ServiceException;
import java.util.List;

/**
 *
 * @author Jerald Mejarla
 */
public interface DisabilitiesService {

    /**
     *
     * @param disabilities
     * @param uid
     * @return
     * @throws ServiceException
     */
    Disabilities addDisability(final Disabilities disabilities,
            Integer uid) throws ServiceException;
    
    /**
     *
     * @param disabilitesList
     * @param uid
     * @return
     * @throws com.innoppl.outreach.service.ServiceException
     */
    List<Disabilities> addDisabilites(List<Disabilities> disabilitesList,
            Integer uid) throws ServiceException;
    
    /**
     *
     * @param projectEntryId
     * @return
     * @throws com.innoppl.outreach.service.ServiceException
     */
    List<Disabilities> findByProjectEntry(final Integer projectEntryId) 
            throws ServiceException;
    
}
