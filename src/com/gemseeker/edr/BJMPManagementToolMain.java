package com.gemseeker.edr;

import com.gemseeker.edr.fxml.MainScreenController;
import com.gemseeker.edr.fxml.Loader;
import com.gemseeker.edr.fxml.LogInScreenController;
import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

/**
 * Main class that launch the application.
 * 
 * @author msufr_000
 */
public class BJMPManagementToolMain extends Application {

    public static final String DEBUG_NAME = "MAIN";
    
    private Stage window;
    // StackPane is used to switch between the MainFrame and LogInFrame
    private StackPane stackPane;
    
    // Frames/Panes
    private Node mainScreen;
    private Node loginScreen;
    
    // Controllers for mainScreen and loginScree
    private MainScreenController mainController;
    private LogInScreenController loginController;

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        try{
            System.out.println(DEBUG_NAME + ": initStage()");
            initStage(stage);
        }catch(IOException e){
            System.err.println(DEBUG_NAME + ": " + e);
        }
    }
    
    /**
     * Initializes the stage/window, creates the main screen and login screen,
     * loads all the screens for the main screen.
     * 
     * @param window The UI window.
     * @throws IOException 
     */
    private void initStage(Stage window) throws IOException{
        Loader mainScreenLoader = new Loader(Screens.MAIN_FRAME.getSource());
        mainScreen = mainScreenLoader.load();
        mainController = (MainScreenController) mainScreenLoader.getController();
        mainController.initComponents();
        mainController.setApplication(this);

        Loader loginLoader = new Loader(Screens.LOGIN_FRAME.getSource());
        loginScreen = loginLoader.load();
        loginController = (LogInScreenController) loginLoader.getController();
        loginController.initComponents();
        loginController.setApplication(this);

        stackPane = new StackPane();
        stackPane.setAlignment(Pos.TOP_LEFT);
        showLogin();
        
        Scene scene = new Scene(stackPane, 1024, 600);
        window.setScene(scene);
        window.setTitle("BJMP Management Tool");
        window.setResizable(false);
        
        window.setOnCloseRequest((WindowEvent evt)->{
            // do things on close here...
            mainController.getFileLoader().saveData();
        });
        
        this.window = window;
        window.show();
    }
    
    /**
     * Shows the login screen.
     */
    public void showLogin(){
        Task task = new Task<Void>(){
            @Override
            protected Void call() throws Exception {
                Platform.runLater(()->{
                    if(!stackPane.getChildren().isEmpty()){
                        stackPane.getChildren().remove(0);
                        stackPane.getChildren().add(0, loginScreen);
                    }else{
                        stackPane.getChildren().add(loginScreen);
                    }
                    loginController.update();
                });
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }
    
    /**
     * Shows the mainScreen.
     */
    public void showMainScene(){
        Task task = new Task<Void>(){
            @Override
            protected Void call() throws Exception {
                Platform.runLater(()->{
                    if(!stackPane.getChildren().isEmpty()){
                        stackPane.getChildren().remove(0);
                        stackPane.getChildren().add(0, mainScreen);
                    }else{
                        stackPane.getChildren().add(mainScreen);
                    }
                    mainController.update();
                });
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }
    
    public Window getWindow(){
        return window;
    }
}
