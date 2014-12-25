package com.innoppl.outreach.data.model;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author Jerald Mejarla
 */
@Cacheable
@Entity
@Table(name = "LOV")
public class LOV extends AbstractEntity {

    @Basic(optional = false)
    @NotNull
    @Column(name = "LOVType")
    private Integer lovType;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "DisplayName")
    private String displayName;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ValueString")
    private String valueString;

    public Integer getLovType() {
        return lovType;
    }

    @JsonProperty("type")
    public void setLovType(Integer lovType) {
        this.lovType = lovType;
    }

    public String getDisplayName() {
        return displayName;
    }

    @JsonProperty("name")
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getValueString() {
        return valueString;
    }

    @JsonProperty("value")
    public void setValueString(String valueString) {
        this.valueString = valueString;
    }
}
