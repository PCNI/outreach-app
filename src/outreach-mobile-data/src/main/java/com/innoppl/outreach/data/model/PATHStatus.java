package com.innoppl.outreach.data.model;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jerald Mejarla
 */
@Cacheable
@Entity
@Table(name = "PATHStatus")
public class PATHStatus extends AbstractEntity {

    private static final long serialVersionUID = 1L;
   
    @Basic(optional = false)
    @NotNull
    @Column(name = "DateOfStatus")
    private Integer dateOfStatus;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ClientEnrolledInPATH")
    private Integer clientEnrolledInPATH;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ReasonNotEnrolled")
    private Integer reasonNotEnrolled;
    
    @JoinColumn(name = "ProjectEntryID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Enrollment projectEntryID;

    public PATHStatus() {
    }

    public PATHStatus(Integer id) {
        this.id = id;
    }

    public Integer getDateOfStatus() {
        return dateOfStatus;
    }

    public void setDateOfStatus(Integer dateOfStatus) {
        this.dateOfStatus = dateOfStatus;
    }

    public Integer getClientEnrolledInPATH() {
        return clientEnrolledInPATH;
    }

    public void setClientEnrolledInPATH(Integer clientEnrolledInPATH) {
        this.clientEnrolledInPATH = clientEnrolledInPATH;
    }

    public Integer getReasonNotEnrolled() {
        return reasonNotEnrolled;
    }

    public void setReasonNotEnrolled(Integer reasonNotEnrolled) {
        this.reasonNotEnrolled = reasonNotEnrolled;
    }

    public Enrollment getProjectEntryID() {
        return projectEntryID;
    }

    public void setProjectEntryID(Enrollment projectEntryID) {
        this.projectEntryID = projectEntryID;
    }
}
