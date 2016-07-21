package com.gemseeker.edr.fxml;

import com.gemseeker.edr.BJMPManagementToolMain;
import com.gemseeker.edr.FileLoader;
import com.gemseeker.edr.UIAnimation;
import com.gemseeker.edr.screen.ScreenController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class LogInScreenController implements Initializable, ScreenController {

    @FXML Button login;
    
    @FXML TextField username;
    @FXML PasswordField password;

    @FXML Pane notificationPane;
    @FXML Label notificationLabel;
    
    private BJMPManagementToolMain application;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @Override
    public void initComponents() {
    }

    @Override
    public void update() {
        username.clear();
        password.clear();
    }
    
    @FXML
    protected void action(ActionEvent e){
        Object src = e.getSource();
        if(src == login){
            // TODO: Verify login
            String usernameStr = username.getText();
            String passwordStr = password.getText();
            
            FileLoader fileLoader = new FileLoader();
            
            if(usernameStr.equals(fileLoader.getPasswordData().getUsername()) 
                    && passwordStr.equals(fileLoader.getPasswordData().getPassword())){
                application.showMainScene();
            }else{
                showNotification("Login Failed");
            }
        }
    }

    @Override
    public void setMainFrame(MainScreenController mainFrame) {
    }
    
    public void setApplication(BJMPManagementToolMain application){
        this.application = application;
    }
    
    private void showNotification(String message){
        Platform.runLater(()->{
            notificationLabel.setText(message);
            // animate in
            UIAnimation.translateY(notificationPane, 130, 300, false);

            // animate out after 2 seconds
            KeyFrame key = new KeyFrame(Duration.seconds(2), ae -> hideNotification());

            // play animation
            Timeline timeline = new Timeline(key);
            timeline.play();
        });
    }
    
    private void hideNotification(){
        UIAnimation.translateY(notificationPane, -115, 300, false);
    }
}
