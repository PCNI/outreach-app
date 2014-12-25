package com.innoppl.outreach.data.dao;

import com.innoppl.outreach.data.model.Client;
import java.util.List;

/**
 *
 * @author Jerald Mejarla
 */
public interface ClientDao extends AbstractDao<Client, Integer> {
    
    /**
     *
     * @param firstName
     * @param lastName
     * @param ssn
     * @param startAge
     * @param endAge
     * @return
     */
    List<Client> searchClient(String firstName, String lastName, String ssn, 
            Integer startAge, Integer endAge);
}
