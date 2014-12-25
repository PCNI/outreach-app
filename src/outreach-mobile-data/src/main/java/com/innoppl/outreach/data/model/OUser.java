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
import javax.validation.constraints.Size;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Jerald Mejarla
 */
@Cacheable
@Entity
@Table(name = "OUser")
public class OUser extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "email")
    private String email;

    @JsonIgnore
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "password")
    private String password;

    @Size(max = 256)
    @Column(name = "token")
    private String token;

    @Column(name = "tokenExpiry")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tokenExpiry;

    @JsonIgnore
    @Size(max = 256)
    @Column(name = "firstName")
    private String firstName;

    @JsonIgnore
    @Size(max = 256)
    @Column(name = "middleName")
    private String middleName;

    @JsonIgnore
    @Size(max = 256)
    @Column(name = "lastName")
    private String lastName;

    @JsonIgnore
    @JoinColumn(name = "role", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ORole role;

    @JsonIgnore
    @JoinColumn(name = "ProjectID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Project projectID;

    public OUser() {
    }

    public OUser(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getTokenExpiry() {
        return tokenExpiry;
    }

    public void setTokenExpiry(Date tokenExpiry) {
        this.tokenExpiry = tokenExpiry;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ORole getRole() {
        return role;
    }

    public void setRole(ORole role) {
        this.role = role;
    }

    public Project getProjectID() {
        return projectID;
    }

    public void setProjectID(Project projectID) {
        this.projectID = projectID;
    }
}
