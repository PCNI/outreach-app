package com.innoppl.outreach.data.model;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Jerald Mejarla
 */
@Cacheable
@Entity
@Table(name = "Services")
public class Services extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Column(name = "DateProvided")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private Date dateProvided;

    @Basic(optional = false)
    @NotNull
    @Column(name = "RecordType")
    private Integer recordType;
    
    @Basic(optional = false)
    @Column(name = "TypeProvided")
    private Integer typeProvided;

    @Size(min = 0, max = 256)
    @Column(name = "OtherTypeProvided")
    private String otherTypeProvided;

    @Column(name = "SubTypeProvided")
    private Integer subTypeProvided;

    @Column(name = "FAAmount")
    private Float fAAmount;

    @Column(name = "ReferralOutcome")
    private Integer referralOutcome;

    @JsonIgnore
    @JoinColumn(name = "ProjectEntryID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Enrollment projectEntryID;

    public Services() {
    }

    public Services(Integer id) {
        this.id = id;
    }

    public Date getDateProvided() {
        return dateProvided;
    }

    @JsonProperty("DateProvided")
    public void setDateProvided(Date dateProvided) {
        this.dateProvided = dateProvided;
    }

    public Integer getRecordType() {
        return recordType;
    }

    @JsonProperty("RecordType")
    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }

    public Integer getTypeProvided() {
        return typeProvided;
    }

    public void setTypeProvided(Integer typeProvided) {
        this.typeProvided = typeProvided;
    }

    public String getOtherTypeProvided() {
        return otherTypeProvided;
    }

    @JsonProperty("DateProvided")
    public void setOtherTypeProvided(String otherTypeProvided) {
        this.otherTypeProvided = otherTypeProvided;
    }

    public Integer getSubTypeProvided() {
        return subTypeProvided;
    }

    @JsonProperty("DateProvided")
    public void setSubTypeProvided(Integer subTypeProvided) {
        this.subTypeProvided = subTypeProvided;
    }

    public Float getfAAmount() {
        return fAAmount;
    }

    @JsonProperty("DateProvided")
    public void setfAAmount(Float fAAmount) {
        this.fAAmount = fAAmount;
    }

    public Integer getReferralOutcome() {
        return referralOutcome;
    }

    @JsonProperty("ReferralOutcome")
    public void setReferralOutcome(Integer referralOutcome) {
        this.referralOutcome = referralOutcome;
    }
    
    public Enrollment getProjectEntryID() {
        return projectEntryID;
    }

    @JsonProperty("DateProvided")
    public void setProjectEntryID(Enrollment projectEntryID) {
        this.projectEntryID = projectEntryID;
    }
}
