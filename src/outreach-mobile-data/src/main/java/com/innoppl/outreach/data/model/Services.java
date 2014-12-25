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
    private Integer dateProvided;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "RecordType")
    private Integer recordType;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "OtherTypeProvided")
    private Integer otherTypeProvided;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "SubTypeProvided")
    private Integer subTypeProvided;
    
    @JsonIgnore
    @JoinColumn(name = "ProjectEntryID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Enrollment projectEntryID;

    public Services() {
    }

    public Services(Integer id) {
        this.id = id;
    }

    public Integer getDateProvided() {
        return dateProvided;
    }

    public void setDateProvided(Integer dateProvided) {
        this.dateProvided = dateProvided;
    }

    public Integer getRecordType() {
        return recordType;
    }

    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }

    public Integer getOtherTypeProvided() {
        return otherTypeProvided;
    }

    public void setOtherTypeProvided(Integer otherTypeProvided) {
        this.otherTypeProvided = otherTypeProvided;
    }

    public Integer getSubTypeProvided() {
        return subTypeProvided;
    }

    public void setSubTypeProvided(Integer subTypeProvided) {
        this.subTypeProvided = subTypeProvided;
    }

    public Enrollment getProjectEntryID() {
        return projectEntryID;
    }

    public void setProjectEntryID(Enrollment projectEntryID) {
        this.projectEntryID = projectEntryID;
    }
}
