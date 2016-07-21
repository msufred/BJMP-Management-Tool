package com.gemseeker.edr.fxml;

import com.gemseeker.edr.BJMPManagementToolMain;
import com.gemseeker.edr.FileLoader;
import com.gemseeker.edr.Screens;
import com.gemseeker.edr.screen.ScreenController;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Window;

public class MainScreenController implements Initializable, ScreenController {

    private final HashMap<String, Node> screens = new HashMap<>();
    private final HashMap<String, ScreenController> controllers = new HashMap<>();
    
//    @FXML Label districtLabel;
    @FXML Pane container;
    
    // MENUS
        // Admin Menu
    @FXML MenuItem changePass;
    @FXML MenuItem logout;
    
        // File Menu
    @FXML MenuItem printInmateList;
    @FXML MenuItem printSpecificInmate;
    
    private StackPane stackPane;
    private BJMPManagementToolMain application;
    private FileLoader fileLoader;
    
    @Override
    public void initComponents() {
        stackPane = new StackPane();
        container.getChildren().add(stackPane);
        fileLoader = new FileLoader();
        
        // load all screens
        loadScreen(Screens.LOGIN_FRAME.getName(), Screens.LOGIN_FRAME.getSource());
        loadScreen(Screens.INMATES_FRAME.getName(), Screens.INMATES_FRAME.getSource());
        loadScreen(Screens.ADD_INMATE_FRAME.getName(), Screens.ADD_INMATE_FRAME.getSource());
        loadScreen(Screens.UPDATE_FRAME.getName(), Screens.UPDATE_FRAME.getSource());
        loadScreen(Screens.CHANGE_PASSWORD_FRAME.getName(), Screens.CHANGE_PASSWORD_FRAME.getSource());
        setScreen(Screens.INMATES_FRAME.getName());
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public boolean loadScreen(String name, String source){
        try {
            Loader loader = new Loader(source);
            Parent loadScreen = loader.load();
            screens.put(name, loadScreen);

            ScreenController myScreenController = ((ScreenController) loader.getController());
            myScreenController.setMainFrame(this);
            myScreenController.initComponents();
            controllers.put(name, myScreenController);

            return true;
        } catch (IOException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
    public void setScreen(String name){
        Task task = new Task<Void>(){
            @Override
            protected Void call() throws Exception {
                Platform.runLater(()->{
                    if(screens.get(name) != null){
                        if(!stackPane.getChildren().isEmpty()){
                            stackPane.getChildren().remove(0);
                            stackPane.getChildren().add(0, screens.get(name));
                        }else{
                            stackPane.getChildren().add(screens.get(name));
                        }
                        ScreenController sc = controllers.get(name);
                        sc.update();
                    }
                });
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }
    
    public ScreenController getController(String name){
        return controllers.get(name);
    }

    @Override
    public void update() {
        setScreen(Screens.INMATES_FRAME.getName());
    }

    @Override
    public void setMainFrame(MainScreenController mainFrame) {
    }
    
    public void setApplication(BJMPManagementToolMain application){
        this.application = application;
    }
    
    public FileLoader getFileLoader(){
        return fileLoader;
    }
    
    @FXML
    protected void action(ActionEvent e){
        Object src = e.getSource();
        
        if(src == changePass){
            setScreen(Screens.CHANGE_PASSWORD_FRAME.getName());
        }
        
        else if(src == logout){
            application.showLogin();
        }
    }
    
    public void logout(){
        application.showLogin();
    }
    
}
