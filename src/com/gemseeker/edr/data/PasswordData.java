package com.gemseeker.edr.data;

import java.io.Serializable;

/**
 *
 * @author msufr_000
 */
public class PasswordData implements Serializable {
    
    private String username;
    private String password;
    
    public PasswordData(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
