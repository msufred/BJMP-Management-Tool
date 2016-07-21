package com.gemseeker.edr.fxml;

import com.gemseeker.edr.Screens;
import com.gemseeker.edr.UIAnimation;
import com.gemseeker.edr.data.PasswordData;
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


public class ChangePasswordScreenController implements Initializable, ScreenController {

    @FXML TextField username;
    @FXML PasswordField oldPassword;
    @FXML PasswordField newPassword;
    @FXML Button confirm;
    @FXML Button back;
    
    @FXML Pane notificationPane;
    @FXML Label notificationLabel;
    
    private MainScreenController mainFrame;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void initComponents() {
    }

    @Override
    public void update() {
    }

    @Override
    public void setMainFrame(MainScreenController mainFrame) {
        this.mainFrame = mainFrame;
    }
    
    @FXML
    protected void action(ActionEvent e){
        Object src = e.getSource();
        if(src == confirm){
            String user = username.getText();
            String oldPass = oldPassword.getText();
            String newPass = newPassword.getText();

            PasswordData passData = mainFrame.getFileLoader().getPasswordData();

            if(oldPass.equals(passData.getPassword())){
                mainFrame.getFileLoader().setPasswordData(user, newPass);
                mainFrame.getFileLoader().saveData();
                
                // TODO: Inform success
                mainFrame.logout();
            }else{
                showNotification("Failed to change password. Try again.");
            }
        }
        
        else if(src == back){
            mainFrame.setScreen(Screens.INMATES_FRAME.getName());
        }
    }
    
    private void showNotification(String message){
        Platform.runLater(()->{
            notificationLabel.setText(message);
            // animate in
            UIAnimation.translateY(notificationPane, 300, 300, false);

            // animate out after 2 seconds
            KeyFrame key = new KeyFrame(Duration.seconds(2), ae -> hideNotification());

            // play animation
            Timeline timeline = new Timeline(key);
            timeline.play();
        });
    }
    
    private void hideNotification(){
        UIAnimation.translateY(notificationPane, -400, 300, false);
    }
}
