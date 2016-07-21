/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author msufr_000
 */
public class Test {
    
    public static void main(String[] args) {
        
        final SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        
//        Date now = new Date(System.currentTimeMillis());
//        String nowAsString = formatter.format(now);
//        
//        System.out.println("Today is " + nowAsString);
//        
//        long x = 20 * 24 * 60 * 60 * 1000; // 24 days ago
//        
//        Date before = new Date(now.getTime() - x);
//        String beforeAsString = formatter.format(before);
//        
//        System.out.println("The date 24 days ago is " + beforeAsString);
        
        // calculating the edr
        Date committed = new Date(System.currentTimeMillis());
        System.out.println("Date Committed: " + formatter.format(committed));
        
        int y = 1;
        int m = 2;
        int d = 15;
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(committed);
        cal.add(Calendar.YEAR, y);
        cal.add(Calendar.MONTH, m);
        cal.add(Calendar.DAY_OF_YEAR, d);
        Date edr = cal.getTime();
        
        System.out.println("Date of Release: " + formatter.format(edr));
    }
    
}
