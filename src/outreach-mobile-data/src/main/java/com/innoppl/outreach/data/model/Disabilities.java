package com.innoppl.outreach.data.model;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author Jerald Mejarla
 */
@Cacheable
@Entity
@Table(name = "Disabilities")
public class Disabilities extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Column(name = "DisabilityType")
    private Integer disabilityType;

    @Basic(optional = false)
    @NotNull
    @Column(name = "DisabilityResponse")
    private Integer disabilityResponse;

    @Basic(optional = false)
    @NotNull
    @Column(name = "IndefiniteAndImpairs")
    private Integer indefiniteAndImpairs;

    @Basic(optional = false)
    @NotNull
    @Column(name = "DocumentationOnFile")
    private Integer documentationOnFile;

    @Basic(optional = false)
    @NotNull
    @Column(name = "ReceivingServices")
    private int receivingServices;

    @Column(name = "PATHHowConfirmed")
    private Integer pATHHowConfirmed;

    @Column(name = "PATHSMIInformation")
    private Integer pATHSMIInformation;

    @JsonIgnore
    @JoinColumn(name = "ProjectEntryID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Enrollment projectEntryID;

    public Disabilities() {
    }

    public Disabilities(Integer id) {
        this.id = id;
    }

    public Integer getDisabilityType() {
        return disabilityType;
    }

    @JsonProperty("DisabilityType")
    public void setDisabilityType(Integer disabilityType) {
        this.disabilityType = disabilityType;
    }

    public Integer getDisabilityResponse() {
        return disabilityResponse;
    }

    @JsonProperty("DisabilityResponse")
    public void setDisabilityResponse(Integer disabilityResponse) {
        this.disabilityResponse = disabilityResponse;
    }

    public Integer getIndefiniteAndImpairs() {
        return indefiniteAndImpairs;
    }

    @JsonProperty("IndefiniteAndImpairs")
    public void setIndefiniteAndImpairs(Integer indefiniteAndImpairs) {
        this.indefiniteAndImpairs = indefiniteAndImpairs;
    }

    public Integer getDocumentationOnFile() {
        return documentationOnFile;
    }

    @JsonProperty("DocumentationOnFile")
    public void setDocumentationOnFile(Integer documentationOnFile) {
        this.documentationOnFile = documentationOnFile;
    }

    public int getReceivingServices() {
        return receivingServices;
    }

    @JsonProperty("ReceivingServices")
    public void setReceivingServices(int receivingServices) {
        this.receivingServices = receivingServices;
    }

    public Integer getpATHHowConfirmed() {
        return pATHHowConfirmed;
    }

    @JsonProperty("PATHHowConfirmed")
    public void setpATHHowConfirmed(Integer pATHHowConfirmed) {
        this.pATHHowConfirmed = pATHHowConfirmed;
    }

    public Integer getpATHSMIInformation() {
        return pATHSMIInformation;
    }

    @JsonProperty("PATHSMIInformation")
    public void setpATHSMIInformation(Integer pATHSMIInformation) {
        this.pATHSMIInformation = pATHSMIInformation;
    }

    public Enrollment getProjectEntryID() {
        return projectEntryID;
    }

    @JsonProperty("ProjectEntryID")
    public void setProjectEntryID(Enrollment projectEntryID) {
        this.projectEntryID = projectEntryID;
    }
}
