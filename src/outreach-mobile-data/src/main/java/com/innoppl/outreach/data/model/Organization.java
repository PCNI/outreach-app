package com.innoppl.outreach.data.model;

import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Jerald Mejarla
 */
@Cacheable
@Entity
@Table(name = "Organization")
public class Organization extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "OrganizationName")
    private String organizationName;

    @Size(max = 45)
    @Column(name = "OrganizationCommonName")
    private String organizationCommonName;

    @JsonIgnore
    @OneToMany(mappedBy = "organizationID")
    private List<Project> projectList;

    public Organization() {
    }

    public Organization(Integer id) {
        this.id = id;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationCommonName() {
        return organizationCommonName;
    }

    public void setOrganizationCommonName(String organizationCommonName) {
        this.organizationCommonName = organizationCommonName;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }
}
