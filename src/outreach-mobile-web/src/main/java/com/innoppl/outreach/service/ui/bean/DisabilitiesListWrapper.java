package com.innoppl.outreach.service.ui.bean;

import com.innoppl.outreach.data.model.Disabilities;
import java.util.List;

/**
 *
 * @author Jerald Mejarla
 */
public class DisabilitiesListWrapper {

    private List<Disabilities> disabilitiesList;

    public DisabilitiesListWrapper() {

    }

    public List<Disabilities> getDisabilitiesList() {
        return disabilitiesList;
    }

    public void setDisabilitiesList(List<Disabilities> disabilitiesList) {
        this.disabilitiesList = disabilitiesList;
    }
}
