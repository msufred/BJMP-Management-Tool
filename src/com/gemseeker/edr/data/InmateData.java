package com.gemseeker.edr.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Inmate's information.
 * @author msufr_000
 */
public class InmateData implements Serializable {
    
    private String firstName;
    private String middleName;
    private String lastName;
    
    private String courtNo;
    
    private Date committed;
    private int mipYears;
    private int mipMonths;
    private int mipDays;
    private Date edrFormer;
    private Date edr;
    
    private ArrayList<String> cases;
    private ArrayList<TimeAllowanceData> taList;
    
    private String imageSrc;
    
    public InmateData(String firstName, String middleName, String lastName){
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        
        committed = new Date();
        mipYears = 0;
        mipMonths = 0;
        mipDays = 0;
        edrFormer = new Date();
        edr = new Date();
        
        cases = new ArrayList<>();
        taList = new ArrayList<>();
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

    public Date getCommitted() {
        return committed;
    }

    public void setCommitted(Date committed) {
        this.committed = committed;
    }

    public int getMipYears() {
        return mipYears;
    }

    public void setMipYears(int mipYears) {
        this.mipYears = mipYears;
    }

    public int getMipMonths() {
        return mipMonths;
    }

    public void setMipMonths(int mipMonths) {
        this.mipMonths = mipMonths;
    }

    public int getMipDays() {
        return mipDays;
    }

    public void setMipDays(int mipDays) {
        this.mipDays = mipDays;
    }

    public Date getEdrFormer() {
        return edrFormer;
    }

    public void setEdrFormer(Date edrFormer) {
        this.edrFormer = edrFormer;
    }

    
    public Date getEdr() {
        return edr;
    }

    public void setEdr(Date edr) {
        this.edr = edr;
    }

    public ArrayList<String> getCases() {
        return cases;
    }

    public void setCases(ArrayList<String> cases) {
        this.cases = cases;
    }
    
    public ArrayList<TimeAllowanceData> getTaList() {
        return taList;
    }

    public void setTaList(ArrayList<TimeAllowanceData> taList) {
        this.taList = taList;
    }
    
    public void addTimeAllowance(TimeAllowanceData taData){
        if(!taList.contains(taData)){
            taList.add(taData);
        }
    }
    
    public void addCase(String caseStr){
        cases.add(caseStr);
    }

    public String getImageSource() {
        return imageSrc;
    }

    public void setImageSource(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getCourtNo() {
        return courtNo;
    }

    public void setCourtNo(String courtNo) {
        this.courtNo = courtNo;
    }
    
}
