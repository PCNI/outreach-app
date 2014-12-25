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
@Table(name = "VeteranInfo")
public class VeteranInfo extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Column(name = "YearEnteredService")
    private int yearEnteredService;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "WorldWarII")
    private int worldWarII;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "KoreanWar")
    private int koreanWar;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "VietnamWar")
    private int vietnamWar;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "DesertStorm")
    private int desertStorm;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "AfghanistanOEF")
    private int afghanistanOEF;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "IraqOIF")
    private int iraqOIF;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "IraqOND")
    private int iraqOND;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "OtherTheatre")
    private int otherTheatre;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "MilitaryBranch")
    private int militaryBranch;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "DischargeStatus")
    private int dischargeStatus;
    
    @JsonIgnore
    @JoinColumn(name = "PersonalID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Client personalID;

    public VeteranInfo() {
    }

    public VeteranInfo(Integer id) {
        this.id = id;
    }

    public int getYearEnteredService() {
        return yearEnteredService;
    }

    public void setYearEnteredService(int yearEnteredService) {
        this.yearEnteredService = yearEnteredService;
    }

    public int getWorldWarII() {
        return worldWarII;
    }

    public void setWorldWarII(int worldWarII) {
        this.worldWarII = worldWarII;
    }

    public int getKoreanWar() {
        return koreanWar;
    }

    public void setKoreanWar(int koreanWar) {
        this.koreanWar = koreanWar;
    }

    public int getVietnamWar() {
        return vietnamWar;
    }

    public void setVietnamWar(int vietnamWar) {
        this.vietnamWar = vietnamWar;
    }

    public int getDesertStorm() {
        return desertStorm;
    }

    public void setDesertStorm(int desertStorm) {
        this.desertStorm = desertStorm;
    }

    public int getAfghanistanOEF() {
        return afghanistanOEF;
    }

    public void setAfghanistanOEF(int afghanistanOEF) {
        this.afghanistanOEF = afghanistanOEF;
    }

    public int getIraqOIF() {
        return iraqOIF;
    }

    public void setIraqOIF(int iraqOIF) {
        this.iraqOIF = iraqOIF;
    }

    public int getIraqOND() {
        return iraqOND;
    }

    public void setIraqOND(int iraqOND) {
        this.iraqOND = iraqOND;
    }

    public int getOtherTheatre() {
        return otherTheatre;
    }

    public void setOtherTheatre(int otherTheatre) {
        this.otherTheatre = otherTheatre;
    }

    public int getMilitaryBranch() {
        return militaryBranch;
    }

    public void setMilitaryBranch(int militaryBranch) {
        this.militaryBranch = militaryBranch;
    }

    public int getDischargeStatus() {
        return dischargeStatus;
    }

    public void setDischargeStatus(int dischargeStatus) {
        this.dischargeStatus = dischargeStatus;
    }

    public Client getPersonalID() {
        return personalID;
    }

    public void setPersonalID(Client personalID) {
        this.personalID = personalID;
    }
}
