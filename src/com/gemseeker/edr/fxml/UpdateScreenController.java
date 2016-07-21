package com.gemseeker.edr.fxml;

import com.gemseeker.edr.Screens;
import com.gemseeker.edr.UIAnimation;
import com.gemseeker.edr.data.InmateData;
import com.gemseeker.edr.data.TimeAllowanceData;
import com.gemseeker.edr.model.DateCalculator;
import com.gemseeker.edr.screen.ScreenController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class UpdateScreenController implements Initializable, ScreenController {

    final SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
    final SimpleDateFormat formatter2 = new SimpleDateFormat("M/dd/yyyy");
    
    @FXML Button back;
    @FXML Button save;
    @FXML Button add;
    @FXML Button print;
    @FXML Button remove;
    @FXML Button clearTA;
    @FXML Label name;
    @FXML Label court;
    @FXML Label dateCommitted;
    @FXML Label cases;
    @FXML Label mip;
    @FXML Label edrFormer;
    @FXML Label edr;
    @FXML ImageView imageHolder;
    
    @FXML TableView table;
    @FXML TableColumn<TimeAllowanceData, String> col1; // date
    @FXML TableColumn<TimeAllowanceData, String> col2; // gcta
    @FXML TableColumn<TimeAllowanceData, String> col3; // tasm
    @FXML TableColumn<TimeAllowanceData, String> col4; // total
    @FXML TableColumn<TimeAllowanceData, String> col5; // edr
    
    // Time Allowance Panel
    @FXML Pane taPanel;
    @FXML Button done;
    @FXML Button cancel;
    @FXML DatePicker datePicker;
    @FXML TextField gcta;
    @FXML TextField tasm;
    
    private MainScreenController mainFrame;
    private InmateData inmateData;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    @Override
    public void initComponents() {
        col1.setCellValueFactory((TableColumn.CellDataFeatures<TimeAllowanceData, String> ta) -> {
            TimeAllowanceData t = ta.getValue();
            StringProperty date = new SimpleStringProperty();
            date.setValue(formatter.format(t.getDate()));
            return date;
        });
        
        col2.setCellValueFactory((TableColumn.CellDataFeatures<TimeAllowanceData, String> ta) -> {
            TimeAllowanceData t = ta.getValue();
            StringProperty s = new SimpleStringProperty();
            s.setValue(String.valueOf(t.getGcta()));
            return s;
        });
        
        col3.setCellValueFactory((TableColumn.CellDataFeatures<TimeAllowanceData, String> ta) -> {
            TimeAllowanceData t = ta.getValue();
            StringProperty s = new SimpleStringProperty();
            s.setValue(String.valueOf(t.getTasm()));
            return s;
        });
        
        col4.setCellValueFactory((TableColumn.CellDataFeatures<TimeAllowanceData, String> ta) -> {
            TimeAllowanceData t = ta.getValue();
            StringProperty s = new SimpleStringProperty();
            s.setValue(String.valueOf(t.getGcta() + t.getTasm()));
            return s;
        });
        
        col5.setCellValueFactory((TableColumn.CellDataFeatures<TimeAllowanceData, String> ta) -> {
            TimeAllowanceData t = ta.getValue();
            StringProperty s = new SimpleStringProperty();
            s.setValue(String.valueOf(formatter.format(t.getEdr())));
            return s;
        });
    }

    @Override
    public void update() {
    }

    @Override
    public void setMainFrame(MainScreenController mainFrame) {
        this.mainFrame = mainFrame;
    }
    
    public void updateTA(InmateData inmateData){
        // NAME
        name.setText(inmateData.getFirstName() + " " + inmateData.getMiddleName()
                + " " + inmateData.getLastName());
        
        // COURT NO.
        court.setText(inmateData.getCourtNo());
        
        // DATE COMMITTED
        dateCommitted.setText(formatter.format(inmateData.getCommitted()));
        
        // CASES
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<inmateData.getCases().size(); i++){
            sb.append(inmateData.getCases().get(i));
            if(i < inmateData.getCases().size() - 1){
                sb.append(", ");
            }
        }
        cases.setText(sb.toString());
        
        // MAX IMPOSABLE PENALTY
        mip.setText(inmateData.getMipYears() + " years " + inmateData.getMipMonths()
            + " months " + inmateData.getMipDays() + " days");
        
        edrFormer.setText(formatter.format(inmateData.getEdrFormer()));
        
        edr.setText(formatter.format(inmateData.getEdr()));
        
        // IMAGE
        String imageSource;
        if(inmateData.getImageSource() == null){
            imageSource = "resources/person.png";
        }else{
            imageSource = inmateData.getImageSource();
        }
        
        try {
            imageHolder.setImage(new Image(new FileInputStream(new File(imageSource))));
        } catch (FileNotFoundException ex) {
        }
        
        // Table items
        table.setItems(FXCollections.observableArrayList(inmateData.getTaList()));
        
        this.inmateData = inmateData;
    }
    
    @FXML
    protected void action(ActionEvent e){
        Object src = e.getSource();
        if(src == back){
            mainFrame.setScreen(Screens.INMATES_FRAME.getName());
        }
        
        else if(src == save){
            save();
            mainFrame.setScreen(Screens.INMATES_FRAME.getName());
        }
        
        else if(src == add){
            showTimeAllowancePanel();
        }
        
        else if(src == cancel){
            hideTimeAllowancePanel();
        }
        
        else if(src == done){
            String dateStr = datePicker.getEditor().getText();
            String gctaStr = gcta.getText().equals("") ? "0" : gcta.getText();
            String tasmStr = tasm.getText().equals("") ? "0" : tasm.getText();
            
            if(dateStr.equals("")){
                // TODO: Show invalid input
                hideTimeAllowancePanel();
                System.out.println("Invalid Input");
            }else{
                try {
                    // create new TimeAllowanceData
                    TimeAllowanceData newTa = new TimeAllowanceData();
                    
                    // set date of update
                    Date newDate = formatter2.parse(dateStr);
                    newTa.setDate(newDate);
                    
                    // gcta
                    int newGCTA = Integer.parseInt(gctaStr);
                    newTa.setGcta(newGCTA);
                    
                    // tasm
                    int newTASM = Integer.parseInt(tasmStr);
                    newTa.setTasm(newTASM);
                    
                    // calculate new EDR
                    int total = Math.abs(newGCTA + newTASM);
                    Date newEDR = DateCalculator.subtractFromDate(inmateData.getEdr(), total);
                    newTa.setEdr(newEDR);
                    
                    // update in-mate's edr and edr label text as well
                    inmateData.setEdr(newEDR);
                    edr.setText(formatter.format(newEDR));
                    
                    // add new EDR to the table
                    table.getItems().add(newTa);
                    hideTimeAllowancePanel();
                } catch (ParseException | NumberFormatException ex) {
                    System.err.println(ex);
                    hideTimeAllowancePanel();
                }
            }
        }
        
        else if(src == clearTA){
            table.getItems().clear();
        }
        
        else if(src == print){
            PrinterJob job = PrinterJob.createPrinterJob();
            
            boolean doPrint = job.showPageSetupDialog(null);
        }
        
        else if(src == remove){
            table.getItems().remove(table.getItems().size()-1);
        }
    }
    
    private void showTimeAllowancePanel(){
        // Clear fields first
        datePicker.getEditor().clear();
        gcta.clear();
        tasm.clear();
        
        UIAnimation.translateY(taPanel, 450, 400, false);
    }
    
    private void hideTimeAllowancePanel(){
        UIAnimation.translateY(taPanel, -400, 400, false);
    }
    
    private void save(){
        ObservableList<TimeAllowanceData> tas = table.getItems();
        tas.stream().forEach((t) -> {
            inmateData.addTimeAllowance(t);
        });
        clear();
    }
    
    private void clear(){
        name.setText("");
        court.setText("");
        cases.setText("");
        dateCommitted.setText("");
        mip.setText("");
        edrFormer.setText("");
        edr.setText("");
        table.getItems().clear();
    }
}
