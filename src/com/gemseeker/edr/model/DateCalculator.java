package com.gemseeker.edr.model;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author msufr_000
 */
public class DateCalculator {

    public static Date subtractFromDate(Date date, int numOfDays){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, -numOfDays);
        return cal.getTime();
    }
    
}
