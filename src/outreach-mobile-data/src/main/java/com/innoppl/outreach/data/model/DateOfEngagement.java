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
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author Jerald Mejarla
 */
@Cacheable
@Entity
@Table(name = "DateOfEngagement")
public class DateOfEngagement extends AbstractEntity {
    private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "DateOfEngagement")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfEngagement;
    
    @JsonIgnore
    @JoinColumn(name = "ProjectEntryID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Enrollment projectEntryID;

    public DateOfEngagement() {
    }

    public DateOfEngagement(Integer id) {
        this.id = id;
    }

    public Date getDateOfEngagement() {
        return dateOfEngagement;
    }

    @JsonProperty("DateOfEngagement")
    public void setDateOfEngagement(Date dateOfEngagement) {
        this.dateOfEngagement = dateOfEngagement;
    }

    public Enrollment getProjectEntryID() {
        return projectEntryID;
    }

    @JsonProperty("ProjectEntryID")
    public void setProjectEntryID(Enrollment projectEntryID) {
        this.projectEntryID = projectEntryID;
    }
}
