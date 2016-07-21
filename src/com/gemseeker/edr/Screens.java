package com.gemseeker.edr;

/**
 * Enumeration of screens.
 * 
 * @author Gem Seeker
 */
public enum Screens {
    
    MAIN_FRAME("MainScreen.fxml", "mainFrame"),
    LOGIN_FRAME("LogInScreen.fxml", "loginFrame"),
    INMATES_FRAME("InmatesScreen.fxml", "inmatesFrame"),
    ADD_INMATE_FRAME("AddInmateScreen.fxml", "addInmate"),
    UPDATE_FRAME("UpdateScreen.fxml", "updateFrame"),
    CHANGE_PASSWORD_FRAME("ChangePasswordScreen.fxml", "changePassword");

    private final String src;
    private final String name;

    Screens(String src, String name){
        this.src = src;
        this.name = name;
    }

    public String getSource(){
        return src;
    }

    public String getName(){
        return name;
    }
}
