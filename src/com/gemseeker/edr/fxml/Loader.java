package com.gemseeker.edr.fxml;

import com.gemseeker.edr.screen.ScreenController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class Loader {
    
    private final FXMLLoader loader;
    
    public Loader(String source){
        loader = new FXMLLoader(getClass().getResource(source));
    }
    
    public Parent load() throws IOException {
        Parent root = loader.load();
        return root;
    }
    
    public ScreenController getController(){
        return loader.getController();
    }
    
}
