package com.innoppl.outreach.data.dao;

import com.innoppl.outreach.data.model.Disabilities;

/**
 *
 * @author jerald
 */
public interface DisabilitiesDao extends AbstractDao<Disabilities, Integer> {

    /**
     *
     * @param projectEntryID
     * @param disabilityType
     * @return
     */
    Disabilities findByDisabilityType(final Integer projectEntryID, 
            final Integer disabilityType);
}
