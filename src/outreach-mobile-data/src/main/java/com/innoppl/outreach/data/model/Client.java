package com.innoppl.outreach.data.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
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
@Table(name = "Client")
public class Client extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "FirstName")
    private String firstName;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "MiddleName")
    private String middleName;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "LastName")
    private String lastName;

    @Size(min = 1, max = 45)
    @Column(name = "NameSuffix")
    private String nameSuffix;

    @Basic(optional = false)
    @NotNull
    @Column(name = "NameDataQuality")
    private Integer nameDataQuality;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "SSN")
    private String ssn;

    @Basic(optional = false)
    @NotNull
    @Column(name = "SSNDataQuality")
    private Integer sSNDataQuality;

    @Basic(optional = false)
    @NotNull
    @Column(name = "DOB")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dob;

    @Column(name = "DOBDataQuality")
    private Integer dobDataQuality;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Gender")
    private Integer gender;

    @Size(max = 45)
    @Column(name = "OtherGender")
    private String otherGender;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Ethnicity")
    private Integer ethnicity;

    @Column(name = "Race")
    private Integer race;

    @Size(max = 1024)
    @Column(name = "imageUrl")
    private String imageUrl;

    @Basic(optional = false)
    @NotNull
    @Column(name = "VeteranStatus")
    private Integer veteranStatus;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personalID")
    private List<Enrollment> enrollmentList;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "personalID")
    private VeteranInfo veteranInfo;

    @JsonIgnore
    @Transient
    private Integer age;

    public Client() {
    }

    public Client(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("FirstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    @JsonProperty("MiddleName")
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    @JsonProperty("LastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getNameDataQuality() {
        return nameDataQuality;
    }

    @JsonProperty("NameDataQuality")
    public void setNameDataQuality(Integer nameDataQuality) {
        this.nameDataQuality = nameDataQuality;
    }

    public String getSsn() {
        return ssn;
    }

    @JsonProperty("SSN")
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public Integer getsSNDataQuality() {
        return sSNDataQuality;
    }

    @JsonProperty("SSNDataQuality")
    public void setsSNDataQuality(Integer sSNDataQuality) {
        this.sSNDataQuality = sSNDataQuality;
    }

    public Date getDob() {
        return dob;
    }

    @JsonProperty("DOB")
    public void setDob(Date dob) {
        this.dob = dob;
        Calendar dobCal = Calendar.getInstance();
        dobCal.setTime(dob);
        Calendar today = Calendar.getInstance();
        this.age = today.get(Calendar.YEAR) - dobCal.get(Calendar.YEAR);
        if (today.get(Calendar.MONTH) < dobCal.get(Calendar.MONTH)) {
            this.age--;
        } else if (today.get(Calendar.MONTH) == dobCal.get(Calendar.MONTH)
                && today.get(Calendar.DAY_OF_MONTH) < dobCal.get(Calendar.DAY_OF_MONTH)) {
            this.age--;
        }
    }

    public Integer getGender() {
        return gender;
    }

    @JsonProperty("Gender")
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getOtherGender() {
        return otherGender;
    }

    @JsonProperty("OtherGender")
    public void setOtherGender(String otherGender) {
        this.otherGender = otherGender;
    }

    public Integer getEthnicity() {
        return ethnicity;
    }

    @JsonProperty("Ethnicity")
    public void setEthnicity(Integer ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @JsonProperty("ImageUrl")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getVeteranStatus() {
        return veteranStatus;
    }

    @JsonProperty("VeteranStatus")
    public void setVeteranStatus(Integer veteranStatus) {
        this.veteranStatus = veteranStatus;
    }

    public List<Enrollment> getEnrollmentList() {
        return enrollmentList;
    }

    public void setEnrollmentList(List<Enrollment> enrollmentList) {
        this.enrollmentList = enrollmentList;
    }

    public VeteranInfo getVeteranInfo() {
        return veteranInfo;
    }

    @JsonProperty("VeteranInfo")
    public void setVeteranInfo(VeteranInfo veteranInfo) {
        this.veteranInfo = veteranInfo;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {

    }

    public String getNameSuffix() {
        return nameSuffix;
    }

    @JsonProperty("NameSuffix")
    public void setNameSuffix(String nameSuffix) {
        this.nameSuffix = nameSuffix;
    }

    public Integer getDobDataQuality() {
        return dobDataQuality;
    }

    @JsonProperty("DOBDataQuality")
    public void setDobDataQuality(Integer dobDataQuality) {
        this.dobDataQuality = dobDataQuality;
    }

    public Integer getRace() {
        return race;
    }

    @JsonProperty("Race")
    public void setRace(Integer race) {
        this.race = race;
    }    
}
