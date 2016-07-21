package com.gemseeker.edr.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages the access to the database.
 *
 * @author RAFIS-FRED
 */
public class DBManager {

    public static final String DB_DRIVER = "jdbc:ucanaccess://";
    public static final String DB_PATH = "resources/reg_inmates.accdb";
    
    private Connection connection;
    
    public DBManager(){
        connection = null;
    }
    
    public final boolean connect(){
        try{
            connection = DriverManager.getConnection(DB_DRIVER + DB_PATH);
            return true;
        }catch(Exception e){
            System.err.println("Failed to connect to the database.");
            return false;
        }
    }
    
    public final boolean disconnect(){
        if(connection != null){
            try {
                connection.close();
                return true;
            } catch (SQLException ex) {
                System.err.println("Failed to close connection. Terminating Application. " + ex);
                System.exit(1);
            }
        }
        return false;
    }
    
    public ResultSet query(String queryStr){
        if(connect()){
            try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(queryStr);
                return rs;
            } catch (SQLException ex) {
                System.err.println("Failed to run query. " + ex);
            } finally{
                disconnect();
            }
        }
        return null;
    }
    
    public ResultSet query(PreparedStatement query){
        if(connect()){
            try {
                ResultSet rs = query.executeQuery();
                return rs;
            } catch (SQLException ex) {
                System.err.println("Failed to run query. " + ex);
            } finally{
                disconnect();
            }
        }
        return null;
    }
    
    static class Constants {
        // tables
        public static final String TABLE_INMATES = "Inmates";
        public static final String TABLE_CASES = "Cases";
        public static final String TABLE_CASE_RECORDS = "Case Records";
        public static final String TABLE_COURTS = "Courts";
        // inmate
        public static final String ID = "ID";
        public static final String FIRST_NAME = "First Name";
        public static final String MIDDLE_NAME = "Middle Name";
        public static final String LAST_NAME = "Last Name";
        public static final String DATE_COMMITTED = "Date Detained";
        public static final String MIP_YEARS = "MIP Years";
        public static final String MIP_MONTHS = "MIP Months";
        public static final String MIP_DAYS = "MIP Days";
        public static final String EDR_ACTUAL = "Actual EDR";
        public static final String EDR_COMPUTED = "Computed EDR";
        
        // case
        public static final String CASE_NUM = "Case Number";
        public static final String CASE_CODE = "Case Code";
        public static final String CASE_NAME = "Case Name";
        public static final String CASE_DESC = "Case Description";
        
        // court
        public static final String COURT_NUM = "Court Number";
        public static final String COURT_NAME = "Court Name";
        public static final String COURT_ADDRESS = "Court Address";
    }
}
