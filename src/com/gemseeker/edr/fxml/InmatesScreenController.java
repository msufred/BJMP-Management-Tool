package com.gemseeker.edr.fxml;

import com.gemseeker.edr.Screens;
import com.gemseeker.edr.data.InmateData;
import com.gemseeker.edr.screen.ScreenController;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class InmatesScreenController implements Initializable, ScreenController {

    @FXML Button add;
    @FXML Button edit;
    @FXML Button delete;
    @FXML Button updateTA;
    
    @FXML TableView table;
    @FXML TableColumn<InmateData, String> col1;
    @FXML TableColumn<InmateData, String> col2;
    @FXML TableColumn<InmateData, String> col3;
    @FXML TableColumn<InmateData, String> col4;
    @FXML TableColumn<InmateData, String> col5;
    
    private MainScreenController mainFrame;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void initComponents() {
       col1.setCellValueFactory((TableColumn.CellDataFeatures<InmateData, String> id) -> {
           InmateData i = id.getValue();
           String f = i.getFirstName();
           String m = i.getMiddleName();
           String l = i.getLastName();
           
           StringProperty name = new SimpleStringProperty();
           name.set(f + " " + m + " " + l);
           return name;
       });
       
       col2.setCellValueFactory((TableColumn.CellDataFeatures<InmateData, String> id) -> {
           InmateData i = id.getValue();
           SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
           String dateStr = formatter.format(i.getCommitted());
           StringProperty date = new SimpleStringProperty(dateStr);
           return date;
       });
       
       col3.setCellValueFactory((TableColumn.CellDataFeatures<InmateData, String> id) -> {
           InmateData i = id.getValue();
           ArrayList<String> caseList = i.getCases();
           StringProperty cases = new SimpleStringProperty();
           StringBuilder sb = new StringBuilder();
           for(int x=0; x<caseList.size(); x++){
               sb.append(caseList.get(x));
               if(x < caseList.size()-1){
                   sb.append(", ");
               }
           }
           cases.set(sb.toString());
           return cases;
       });
       
       col4.setCellValueFactory((TableColumn.CellDataFeatures<InmateData, String> id) -> {
           InmateData i = id.getValue();
           StringProperty mip = new SimpleStringProperty();
           mip.set(i.getMipYears() + " yrs. " + i.getMipMonths() + " mos. " + i.getMipDays() + " days");
           return mip;
       });
       
       col5.setCellValueFactory((TableColumn.CellDataFeatures<InmateData, String> id) -> {
           InmateData i = id.getValue();
           SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
           String edrStr = formatter.format(i.getEdr());
           StringProperty edr = new SimpleStringProperty(edrStr);
           return edr;
       });
       
       ArrayList<InmateData> inmates = mainFrame.getFileLoader().getInmatesDataList();
       ObservableList<InmateData> od = FXCollections.observableArrayList(inmates);
       table.setItems(od);
    }

    @Override
    public void update() {
        table.getItems().clear();
        ArrayList<InmateData> inmates = mainFrame.getFileLoader().getInmatesDataList();
        ObservableList<InmateData> od = FXCollections.observableArrayList(inmates);
        table.setItems(od);
    }

    @Override
    public void setMainFrame(MainScreenController mainFrame) {
        this.mainFrame = mainFrame;
    }
    
    @FXML
    public void action(ActionEvent event){
        Object src = event.getSource();
        
        if(src == add){
            AddInmateScreenController controller = (AddInmateScreenController) mainFrame.getController(Screens.ADD_INMATE_FRAME.getName());
            controller.createInmateData();
            mainFrame.setScreen(Screens.ADD_INMATE_FRAME.getName());
        }else if(src == edit){
            AddInmateScreenController controller = (AddInmateScreenController) mainFrame.getController(Screens.ADD_INMATE_FRAME.getName());
            InmateData selected = (InmateData) table.getSelectionModel().getSelectedItem();
            if(selected != null){
                controller.editInmateData(selected);
                mainFrame.setScreen(Screens.ADD_INMATE_FRAME.getName());
            }else{
                // TODO: Inform can't edit
            }
        }else if(src == updateTA){
            InmateData selecteData = (InmateData) table.getSelectionModel().getSelectedItem();
            UpdateScreenController controller = (UpdateScreenController) mainFrame.getController(Screens.UPDATE_FRAME.getName());
            if(selecteData != null){
                controller.updateTA(selecteData);
                mainFrame.setScreen(Screens.UPDATE_FRAME.getName());
            }
            
        }else if(src == delete){
            InmateData id = (InmateData) table.getSelectionModel().getSelectedItem();
            table.getItems().remove(id);
            mainFrame.getFileLoader().deleteInmate(id);
        }
    }
}
