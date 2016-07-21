package com.gemseeker.edr.fxml;

import com.gemseeker.edr.Screens;
import com.gemseeker.edr.data.InmateData;
import com.gemseeker.edr.screen.ScreenController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
    
public class AddInmateScreenController implements Initializable, ScreenController {

    public static final String DEBUG_NAME = "ADD_INMATE: ";
    
    // MODE
    public static final int ADD = 0;
    public static final int EDIT = 1;
    private int mode;
    
    private static final SimpleDateFormat formatter = new SimpleDateFormat("M/dd/yyyy");
    
    @FXML Button back;
    
    @FXML ImageView imageHolder;
    @FXML Button changePicture;
    private String imageSource;
    
    @FXML TextField firstName;
    @FXML TextField middleName;
    @FXML TextField lastName;
    @FXML TextField court;
    @FXML DatePicker datePicker;
    @FXML TextField year;
    @FXML TextField months;
    @FXML TextField days;
    
    @FXML TextField newCase;
    @FXML ListView caseList;
    @FXML Button addCaseButton;
    @FXML Button removeCaseButton;
    @FXML Button save;
    
    private MainScreenController mainFrame;
    
    private InmateData oldInmateData;
    
    public void createInmateData(){
        clearFields();
        oldInmateData = null;
        mode = ADD;
        imageSource = "resources/person.png";
        try {
            imageHolder.setImage(new Image(new FileInputStream(new File(imageSource))));
        } catch (FileNotFoundException ex) {
        }
    }
    
    public void editInmateData(InmateData oldInmateData){
        this.oldInmateData = oldInmateData;

        Platform.runLater(()->{
            firstName.setText(oldInmateData.getFirstName());
            middleName.setText(oldInmateData.getMiddleName());
            lastName.setText(oldInmateData.getLastName());
            
            court.setText(oldInmateData.getCourtNo());
            
            if(oldInmateData.getImageSource() == null){
                imageSource = "resources/person.png";
            }else{
                imageSource = oldInmateData.getImageSource();
            }
            
            try {
                Image img = new Image(new FileInputStream(new File(imageSource)));
                imageHolder.setImage(img);
            } catch (FileNotFoundException ex) {
            }
            
            datePicker.setValue(LocalDate.parse(formatter.format(oldInmateData
                    .getCommitted()), DateTimeFormatter.ofPattern("M/dd/yyyy")));
            year.setText(String.valueOf(oldInmateData.getMipYears()));
            months.setText(String.valueOf(oldInmateData.getMipMonths()));
            days.setText(String.valueOf(oldInmateData.getMipDays()));
            ObservableList<String> cList = FXCollections.observableArrayList(oldInmateData.getCases());
            caseList.setItems(cList);
        });
        
        mode = EDIT;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @Override
    public void initComponents() {
        // allow integers only for the year, months and days
        String allowedChars = "0123456789";
        
        year.addEventHandler(KeyEvent.KEY_TYPED, (KeyEvent e)->{
            String chr = e.getCharacter();
            if(!allowedChars.contains(chr)){
                e.consume();
            }
        });
        
        months.addEventHandler(KeyEvent.KEY_TYPED, (KeyEvent e)->{
            String chr = e.getCharacter();
            if(!allowedChars.contains(chr)){
                e.consume();
            }
        });

        days.addEventHandler(KeyEvent.KEY_TYPED, (KeyEvent e)->{
            String chr = e.getCharacter();
            if(!allowedChars.contains(chr)){
                e.consume();
            }
        });
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
        if(src == back){
            mainFrame.setScreen(Screens.INMATES_FRAME.getName());
        }
        
        else if(src == changePicture){
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*.png", "*.jpg", "*.jpeg", "*.gif"));
            File file = fc.showOpenDialog(null);
            
            if(file != null){
                try {
                    imageSource = file.getPath();
                    Image image = new Image(new FileInputStream(file));
                    Platform.runLater(()->{
                        imageHolder.setImage(image);
                    });
                } catch (FileNotFoundException ex) {
                }
            }
        }
        
        else if(src == addCaseButton){
            String newCaseStr = newCase.getText();
            if(!newCaseStr.equals("")){
                caseList.getItems().add(newCaseStr);
                newCase.clear();
            }
        }
        
        else if(src == removeCaseButton){
            caseList.getItems().remove(caseList.getSelectionModel().getSelectedIndex());
        }
        
        else if(src == save){
            boolean success = save();
            if(success){
                // TODO: display success
                System.out.println("Success");
                mainFrame.setScreen(Screens.INMATES_FRAME.getName());
            }else{
                System.out.println("Failed");
                // TODO: display failed
            }
        }
    }
    
    private boolean save(){
        String fName = firstName.getText();
        String mName = middleName.getText();
        String lName = lastName.getText();
        
        String courtNo = court.getText();
        
        String date = datePicker.getEditor().getText();
        String y = year.getText();
        String m = months.getText();
        String d = days.getText();
        
        ObservableList<String> cases = caseList.getItems();
        
        // VERIFY
        // First, middle and last name must not be empty.
        // Date and penalty fields must not also be empty.
        if(fName.equals("") || mName.equals("") || lName.equals("") ||
                courtNo.equals("") || date.equals("") || y.equals("") || m.equals("") ||
                d.equals("")){
            System.out.println("Invalid fields");
            return false;
        }else{
            try {
                InmateData newInmate = new InmateData(fName, mName, lName);
                
                // image
                if(imageSource != null || !imageSource.equals("")){
                    newInmate.setImageSource(imageSource);
                }
                
                // court no.
                newInmate.setCourtNo(courtNo);
                
                // date committed
                SimpleDateFormat dateFormat = new SimpleDateFormat("M/dd/yyyy");
                Date dateCommitted = dateFormat.parse(date);
                newInmate.setCommitted(dateCommitted);
                
                // mip
                int noOfYears = Integer.parseInt(y);
                int noOfMonths = Integer.parseInt(m);
                int noOfDays = Integer.parseInt(d);
                
                newInmate.setMipYears(noOfYears);
                newInmate.setMipMonths(noOfMonths);
                newInmate.setMipDays(noOfDays);
                
                // cases
                cases.stream().forEach((c) -> {
                    newInmate.addCase(c);
                });
                
                // edr
                Calendar cal = Calendar.getInstance();
                cal.setTime(dateCommitted);
                cal.add(Calendar.YEAR, noOfYears);
                cal.add(Calendar.MONTH, noOfMonths);
                cal.add(Calendar.DAY_OF_MONTH, noOfDays);
                
                Date edr = cal.getTime();
                newInmate.setEdr(edr);
                newInmate.setEdrFormer(edr);
                
                if(mode == EDIT && oldInmateData != null){
                    mainFrame.getFileLoader().replaceInmate(oldInmateData, newInmate);
                }else if(mode == ADD){
                    mainFrame.getFileLoader().addInmate(newInmate);
                }
                clearFields();
                return true;
            } catch (ParseException ex) {
                System.out.println(DEBUG_NAME + "Error: " + ex);
                return false;
            }
        }
    }
    
    public void clearFields(){
        firstName.clear();
        middleName.clear();
        lastName.clear();
        court.clear();
        year.clear();
        months.clear();
        days.clear();
        caseList.getItems().clear();
    }
   
}
