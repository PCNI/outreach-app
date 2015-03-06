package com.innoppl.outreach.data.model;

import com.innoppl.outreach.data.utils.EntityUtils;
import com.innoppl.outreach.data.utils.InjectMethods;
import com.innoppl.outreach.data.utils.Method;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author Jerald Mejarla
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @InjectMethods(include = Method.ALL)
    protected Integer id;
    
    @JsonIgnore
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateCreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated = new Date();
    
    @JsonIgnore
    @Column(name = "dateDeleted")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDeleted;
    
    @JsonIgnore
    @Column(name = "dateUpdated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;
    
    @JsonIgnore
    @Basic(optional = false)
    @NotNull
    @Column(name = "userID")
    private Integer userID = 0;
    @Basic(optional = false)
    
    @JsonIgnore
    @NotNull
    @Column(name = "isDeleted")
    private Integer isDeleted = 0;

    public String toJson() throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    public Integer getId() {
        return id;
    }

    @JsonProperty("ID")
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateDeleted() {
        return dateDeleted;
    }

    public void setDateDeleted(Date dateDeleted) {
        this.dateDeleted = dateDeleted;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public boolean equals(final Object obj) {
        return EntityUtils.equals(this, obj);
    }

    @Override
    public int hashCode() {
        return EntityUtils.hashCode(this);
    }

    @Override
    public String toString() {
        return EntityUtils.toString(this);
    }
}
