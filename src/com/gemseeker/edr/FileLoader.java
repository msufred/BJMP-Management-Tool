package com.gemseeker.edr;

import com.gemseeker.edr.data.InmateData;
import com.gemseeker.edr.data.PasswordData;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * The class that loads all the data including the login information.
 * 
 * @author msufr_000
 */
public class FileLoader {

    public static final String inmatesFileName = "ifndata.elx";
    public static final String passwordFileName = "impdata.elx";
    
    private ArrayList<InmateData> inmates;
    private PasswordData passwordData;
    
    public FileLoader(){
        inmates = new ArrayList<>();
        loadData();
    }
    
    private void loadData(){
        loadInmatesData();
        loadPasswordData();
    }
    
    private void loadInmatesData(){
        File file = new File(inmatesFileName);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException ex) {
                System.err.println("");
            }
            inmates = new ArrayList<>();
        }
        
        try{
            try (ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(file))) {
                inmates = (ArrayList<InmateData>) objIn.readObject();
            }
        }catch(IOException | ClassNotFoundException e){
        }
    }
    
    private void loadPasswordData(){
        File file = new File(passwordFileName);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException ex) {
            }
            passwordData = new PasswordData("admin", "admin");
        }
        
        try{
            try (ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(file))) {
                passwordData = (PasswordData) objIn.readObject();
            }
        }catch(IOException | ClassNotFoundException e){
            passwordData = new PasswordData("admin", "admin");
        }
    }
    
    public void saveData(){
        saveInmatesData();
        savePassData();
    }
    
    private void saveInmatesData(){
        try {
            ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(inmatesFileName));
            objOut.writeObject(inmates);
        } catch (IOException ex) {
        }
    }
    
    private void savePassData(){
        try {
            ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(passwordFileName));
            objOut.writeObject(passwordData);
        } catch (IOException ex) {
        }
    }
    
    public void addInmate(InmateData newInmate){
        inmates.add(newInmate);
    }
    
    public void deleteInmate(InmateData inmate){
        inmates.remove(inmate);
    }
    
    public void replaceInmate(InmateData old, InmateData newInmate){
        if(inmates.contains(old)){
            int index = inmates.indexOf(old);
            inmates.remove(index);
            inmates.add(index, newInmate);
        }
    }
    
    public ArrayList<InmateData> getInmatesDataList(){
        return inmates;
    }
    
    public void setPasswordData(String username, String password){
        passwordData.setUsername(username);
        passwordData.setPassword(password);
    }
    
    public PasswordData getPasswordData(){
        return passwordData;
    }
}
