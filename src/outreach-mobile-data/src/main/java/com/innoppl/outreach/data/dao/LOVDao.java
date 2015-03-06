package com.innoppl.outreach.data.dao;

import com.innoppl.outreach.data.model.LOV;
import java.util.List;

/**
 *
 * @author Jerald Mejarla
 */
public interface LOVDao extends AbstractDao<LOV, Integer> {

    /**
     *
     * @param type
     * @return
     */
    List<LOV> findAllByType(final Integer type);
    
    /**
     *
     * @param type
     * @param value
     * @return
     */
    LOV findByTypeAndValue(final Integer type, final String value);
}
