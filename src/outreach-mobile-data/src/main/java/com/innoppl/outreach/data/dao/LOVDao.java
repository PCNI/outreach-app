package com.innoppl.outreach.data.dao;

import com.innoppl.outreach.data.model.LOV;
import java.util.List;

/**
 *
 * @author Jerald Mejarla
 */
public interface LOVDao extends AbstractDao<LOV, Integer> {

    List<LOV> findAllByType(final Integer type);
}
