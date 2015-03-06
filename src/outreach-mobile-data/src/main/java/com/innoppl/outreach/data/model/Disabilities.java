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

    @Column(name = "DisabilityResponse")
    private Integer disabilityResponse;

    @Column(name = "IndefiniteAndImpairs")
    private Integer indefiniteAndImpairs;

    @Column(name = "DocumentationOnFile")
    private Integer documentationOnFile;

    @Column(name = "ReceivingServices")
    private Integer receivingServices;

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

    public Disabilities(Integer disabilityType, Enrollment projectEntryID) {
        this.disabilityType = disabilityType;
        this.projectEntryID = projectEntryID;
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

    public Integer getReceivingServices() {
        return receivingServices;
    }

    @JsonProperty("ReceivingServices")
    public void setReceivingServices(Integer receivingServices) {
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
