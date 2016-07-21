package com.gemseeker.edr.data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author msufr_000
 */
public class TimeAllowanceData implements Serializable {
    
    private Date date;
    private int gcta;
    private int tasm;
    private Date edr;
    
    public TimeAllowanceData(){
        this(new Date(), 0, 0, new Date());
    }
    
    public TimeAllowanceData(Date date, int gcta, int tasm, Date edr){
        this.date = date;
        this.gcta = gcta;
        this.tasm = tasm;
        this.edr = edr;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getGcta() {
        return gcta;
    }

    public void setGcta(int gcta) {
        this.gcta = gcta;
    }

    public int getTasm() {
        return tasm;
    }

    public void setTasm(int tasm) {
        this.tasm = tasm;
    }

    public Date getEdr() {
        return edr;
    }

    public void setEdr(Date edr) {
        this.edr = edr;
    }
    
    
}
