package com.innoppl.outreach.data.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author Jerald Mejarla
 */
@Cacheable
@Entity
@Table(name = "Enrollment")
public class Enrollment extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Column(name = "EntryDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date entryDate;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "HouseholdID")
    private String householdID;

    @Basic(optional = false)
    @NotNull
    @Column(name = "RelationshipToHoH")
    private Integer relationshipToHoH;

    @Column(name = "ResidencePrior")
    private Integer residencePrior;

    @Size(max = 45)
    @Column(name = "OtherResidencePrior")
    private String otherResidencePrior;

    @Column(name = "ResidencePriorLengthOfStay")
    private Integer residencePriorLengthOfStay;

    @Column(name = "ContinuouslyHomelessOneYear")
    private Integer continuouslyHomelessOneYear;

    @Column(name = "TimesHomelessPastThreeYears")
    private Integer timesHomelessPastThreeYears;

    @Column(name = "MonthsHomelessPastThreeYears")
    private Integer monthsHomelessPastThreeYears;

    @Column(name = "MonthsHomelessThisTime")
    private Integer monthsHomelessThisTime;

    @Column(name = "YearsHomeless")
    private Integer yearsHomeless;

    @Column(name = "StatusDocumented")
    private Integer statusDocumented;

    @Column(name = "DisablingCondition")
    private Integer disablingCondition;

    @Column(name = "HousingStatus")
    private Integer housingStatus;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectEntryID")
    private List<Services> servicesList;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectEntryID")
    private List<DateOfEngagement> dateOfEngagementList;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectEntryID")
    private List<Disabilities> disabilitiesList;

    @JsonIgnore
    @JoinColumn(name = "PersonalID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Client personalID;

    @JsonIgnore
    @JoinColumn(name = "ProjectID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Project projectID;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectEntryID")
    private List<ReferralSource> referralSourceList;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectEntryID")
    private List<PATHStatus> pATHStatusList;

    public Enrollment() {
    }

    public Enrollment(Integer id) {
        this.id = id;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    @JsonProperty("EntryDate")
    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public String getHouseholdID() {
        return householdID;
    }

    @JsonProperty("HouseholdID")
    public void setHouseholdID(String householdID) {
        this.householdID = householdID;
    }

    public Integer getRelationshipToHoH() {
        return relationshipToHoH;
    }

    @JsonProperty("RelationshipToHoH")
    public void setRelationshipToHoH(Integer relationshipToHoH) {
        this.relationshipToHoH = relationshipToHoH;
    }

    public List<PATHStatus> getpATHStatusList() {
        return pATHStatusList;
    }

    public void setpATHStatusList(List<PATHStatus> pATHStatusList) {
        this.pATHStatusList = pATHStatusList;
    }

    public Integer getResidencePrior() {
        return residencePrior;
    }

    @JsonProperty("ResidencePrior")
    public void setResidencePrior(Integer residencePrior) {
        this.residencePrior = residencePrior;
    }

    public String getOtherResidencePrior() {
        return otherResidencePrior;
    }

    @JsonProperty("OtherResidencePrior")
    public void setOtherResidencePrior(String otherResidencePrior) {
        this.otherResidencePrior = otherResidencePrior;
    }

    public Integer getResidencePriorLengthOfStay() {
        return residencePriorLengthOfStay;
    }

    @JsonProperty("ResidencePriorLengthOfStay")
    public void setResidencePriorLengthOfStay(Integer residencePriorLengthOfStay) {
        this.residencePriorLengthOfStay = residencePriorLengthOfStay;
    }

    public Integer getContinuouslyHomelessOneYear() {
        return continuouslyHomelessOneYear;
    }

    @JsonProperty("ContinuouslyHomelessOneYear")
    public void setContinuouslyHomelessOneYear(Integer continuouslyHomelessOneYear) {
        this.continuouslyHomelessOneYear = continuouslyHomelessOneYear;
    }

    public Integer getTimesHomelessPastThreeYears() {
        return timesHomelessPastThreeYears;
    }

    @JsonProperty("TimesHomelessPastThreeYears")
    public void setTimesHomelessPastThreeYears(Integer timesHomelessPastThreeYears) {
        this.timesHomelessPastThreeYears = timesHomelessPastThreeYears;
    }

    public Integer getMonthsHomelessPastThreeYears() {
        return monthsHomelessPastThreeYears;
    }

    @JsonProperty("MonthsHomelessPastThreeYears")
    public void setMonthsHomelessPastThreeYears(Integer monthsHomelessPastThreeYears) {
        this.monthsHomelessPastThreeYears = monthsHomelessPastThreeYears;
    }

    public Integer getMonthsHomelessThisTime() {
        return monthsHomelessThisTime;
    }

    @JsonProperty("MonthsHomelessThisTime")
    public void setMonthsHomelessThisTime(Integer monthsHomelessThisTime) {
        this.monthsHomelessThisTime = monthsHomelessThisTime;
    }

    public Integer getYearsHomeless() {
        return yearsHomeless;
    }

    @JsonProperty("YearsHomeless")
    public void setYearsHomeless(Integer yearsHomeless) {
        this.yearsHomeless = yearsHomeless;
    }

    public Integer getStatusDocumented() {
        return statusDocumented;
    }

    @JsonProperty("StatusDocumented")
    public void setStatusDocumented(Integer statusDocumented) {
        this.statusDocumented = statusDocumented;
    }

    public Integer getDisablingCondition() {
        return disablingCondition;
    }

    @JsonProperty("DisablingCondition")
    public void setDisablingCondition(Integer disablingCondition) {
        this.disablingCondition = disablingCondition;
    }

    public Integer getHousingStatus() {
        return housingStatus;
    }

    @JsonProperty("HousingStatus")
    public void setHousingStatus(Integer housingStatus) {
        this.housingStatus = housingStatus;
    }

    public List<Services> getServicesList() {
        return servicesList;
    }

    public void setServicesList(List<Services> servicesList) {
        this.servicesList = servicesList;
    }

    public List<DateOfEngagement> getDateOfEngagementList() {
        return dateOfEngagementList;
    }

    public void setDateOfEngagementList(List<DateOfEngagement> dateOfEngagementList) {
        this.dateOfEngagementList = dateOfEngagementList;
    }

    public List<Disabilities> getDisabilitiesList() {
        return disabilitiesList;
    }

    public void setDisabilitiesList(List<Disabilities> disabilitiesList) {
        this.disabilitiesList = disabilitiesList;
    }

    public Client getPersonalID() {
        return personalID;
    }

    @JsonProperty("PersonalID")
    public void setPersonalID(Client personalID) {
        this.personalID = personalID;
    }

    public Project getProjectID() {
        return projectID;
    }

    @JsonProperty("ProjectID")
    public void setProjectID(Project projectID) {
        this.projectID = projectID;
    }

    public List<ReferralSource> getReferralSourceList() {
        return referralSourceList;
    }

    public void setReferralSourceList(List<ReferralSource> referralSourceList) {
        this.referralSourceList = referralSourceList;
    }

    public List<PATHStatus> getPATHStatusList() {
        return pATHStatusList;
    }

    public void setPATHStatusList(List<PATHStatus> pATHStatusList) {
        this.pATHStatusList = pATHStatusList;
    }
}
